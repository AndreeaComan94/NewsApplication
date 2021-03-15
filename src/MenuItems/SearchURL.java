package MenuItems;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.scene.web.WebEngine;
import javafx.event.ActionEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import model.News;
import model.NewsQuery;
import model.User;
import RSS.Feed;
import RSS.RSSFeedParser;
import InputFieldsAndMore.AddSiteText;
import InputFieldsAndMore.RSSMessageBox;
import InputFieldsAndMore.ViewRSSExamples;
import InputFieldsAndMoreControllers.ViewRSSExamplesController;
import UserAndPassControllers.LoginController;

public class SearchURL extends Application implements Initializable {

	@FXML
	private TextField TxtURL;
	String input = null;
	private static String adress;
	private static String description;
	private static WebView browser;
	private static WebEngine engine;

	public static boolean url_from_web_is_pressed = false;

	NewsQuery nq = new NewsQuery();
	User u = new User();
	ViewRSSExamplesController vrec = new ViewRSSExamplesController();

	private static String news_title;
	private static int news_id;

	public static String getAdress() {
		return adress;
	}

	public static String getDescription() {
		return description;
	}

	public static int getNewsId() {
		return news_id;
	}

	public static String getNewsTitle() {
		return news_title;
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) {
		try {

			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("SearchURL.fxml"));

			Scene scene = new Scene(root, 790, 640);

			stage.setScene(scene);

			if (vrec.rss_example_is_pressed == true)
				adress = vrec.rss_example;
			else
				adress = "https://www.google.ro";

			browser = new WebView();
			engine = browser.getEngine();

			engine.load(adress);
			root.getChildren().add(browser);

			stage.setTitle("Navigare web!");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void CloseCurrentStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage current_stage = (Stage) source.getScene().getWindow();
		current_stage.close();
	}

	@FXML
	public void RSSAlert(ActionEvent event) {
		Stage stage = new Stage();
		RSSMessageBox m = new RSSMessageBox();
		m.start(stage);
	}

	@SuppressWarnings("static-access")
	@FXML
	private void BtnAddToRepoPressed(ActionEvent event) throws Exception {
		URL rss_url = new URL(engine.getLocation());

		adress = rss_url.toString();

		u.setId(LoginController.getCurrentId());
		u.setNume(LoginController.getCurrentUsername());

		URL url = new URL(adress);

		// EXEMPLU DE RSS VALID: http://feeds.bbci.co.uk/news/technology/rss.xml
		if (adress.contains("rss") || adress.contains("RSS")
				|| adress.contains("Rss") || adress.contains("feed")
				|| adress.contains("feeds")) {
			RSSFeedParser parser = new RSSFeedParser(adress);
			Feed feed = parser.readFeed();

			System.out.println(feed);

			News news = null;
			news = new News(feed.getLink(), feed.getDescription(),
					handleDate(feed.getPubDate()), feed.getTitle(), u);

			description = feed.getDescription();
			news_id = news.getId();
			news_title = news.getTitlu();
			
			nq.em.persist(news);
			nq.em.getTransaction().commit();

			url_from_web_is_pressed = true;

			Stage stage = new Stage();
			AddSiteText ast = new AddSiteText();
			ast.start(stage);

		} else
			RSSAlert(event);

	}

	@FXML
	private void BtnViewURLPressed(ActionEvent event) {
		TxtURL.setText(engine.getLocation());
	}

	@FXML
	private void BtnViewRSSPressed(ActionEvent event) {
		Stage stage = new Stage();
		ViewRSSExamples vre = new ViewRSSExamples();
		vre.start(stage);

		CloseCurrentStage(event);
	}

	@FXML
	private void BtnMenuPressed(ActionEvent event) {
		Stage stage = new Stage();
		Menu m = new Menu();
		m.start(stage);

		Node source = (Node) event.getSource();
		Stage current_stage = (Stage) source.getScene().getWindow();
		current_stage.close();
	}

	public void initialize(URL url, ResourceBundle rb) {

	}

	private Date handleDate(String tagContent) {
		Date res;
		String[] tmp = tagContent.split(", ")[1].split(" ");
		String strDate = tmp[0].trim() + "-" + tmp[1].trim() + "-"
				+ tmp[2].trim();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd-MMM-yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(strDate, formatter);
		Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault())
				.toInstant();
		res = Date.from(instant);

		return res;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
