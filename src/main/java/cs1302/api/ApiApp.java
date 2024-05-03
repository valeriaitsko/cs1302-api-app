package cs1302.api;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

import java.net.http.HttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Map;
import static java.util.Map.entry;

import cs1302.api.TopComponent;
import cs1302.api.BreweryResponse;
import cs1302.api.CountryResponse;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {
    Stage stage;
    Scene scene;
    VBox root;

    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object
    public static final String BREWERY_API = "https://api.openbrewerydb.org/v1/breweries";
    public static final String COUNTRIES_API = "https://restcountries.com/v3.1/name/";
    public static final Map<String, String> BREWERIES_TO_COUNTRIES = Map.ofEntries(
         entry("England", "United Kingdom"),
         entry("Scotland", "United Kingdom"),
         entry("United States", "United States"),
         entry("Portugal", "Portugal"),
         entry("Singapore", "Singapore"),
         entry("South Korea", "South Korea"),
         entry("Poland", "Poland"),
         entry("Austria", "Austria"),
         entry("Isle Of Man", "Isle of Man"),
         entry("Ireland", "Ireland"),
         entry("France", "France"));

    TopComponent searchCity;
    String uri;
    String breweryCountry;
    Button learnMoreButton;
    Background bg;
    Image bgImg;
    BackgroundImage background;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */

    public ApiApp() {
        root = new VBox();
        searchCity = new TopComponent();
        learnMoreButton = new Button("Learn More!");
        learnMoreButton.setDisable(true);
        bgImg = new Image("file:resources/Background.jpg");
        background = new BackgroundImage(bgImg, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bg = new Background(background);
        root.setBackground(bg);
        root.setFillWidth(true);
    } // ApiApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        Image bannerImage = new Image("file:resources/Pub-Crawl-Expedition-Logo.png");
        ImageView banner = new ImageView(bannerImage);

        banner.setPreserveRatio(true);
        root.getChildren().addAll(banner, searchCity, learnMoreButton);

        searchCity.goButton.setOnAction(event -> {
            learnMoreButton.setDisable(true);
            this.userJsonResponse();
            learnMoreButton.setDisable(false);
        });
        learnMoreButton.setOnAction(event -> this.apiJsonResponse());

    }

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;

        // demonstrate how to load local asset using "file:resources/"


        // some labels to display information
//        Label notice = new Label("Modify the starter code to suit your needs.");

        // setup scene

        scene = new Scene(root);

        // setup stage
        stage.setTitle("ApiApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

    } // start

    private void userJsonResponse() {
        try {
            // form URI
            String city  = URLEncoder.encode(searchCity.query.getText(), StandardCharsets.UTF_8);
            String breweryType = URLEncoder.encode(searchCity.type.getValue(),
                                 StandardCharsets.UTF_8);
            String limit = URLEncoder.encode("200", StandardCharsets.UTF_8);
            String searchQuery = String.format("?by_city=%s&by_type=%s&limit=%s",
                       city, breweryType, limit);
            uri = BREWERY_API + searchQuery;
            System.out.println(uri);
            // build request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            // send request / receive response in the form of a String
            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());
            // ensure the request is okay
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if
            // get request body (the content we requested)
            String jsonString = response.body();

            BreweryResponse[] breweryResponse = GSON
                .fromJson(jsonString, BreweryResponse[].class);

            if (breweryResponse.length > 0) {
                for (int i = 0; i < breweryResponse.length; i++) {
                    if (!breweryResponse[i].country.equals(searchCity.country.getValue())) {
                        breweryResponse[i] = null;
                    } else {
                        breweryCountry = BREWERIES_TO_COUNTRIES.get(breweryResponse[i].country);
                        this.createNecessaryArrays(breweryResponse);
                    } // if
                } // for
                int countNulls = 0;
                for (BreweryResponse br: breweryResponse) {
                    if (br == null) {
                        countNulls ++;
                    } // if
                } // for
                if (countNulls == breweryResponse.length) {
                    System.out.println("Sorry there are no breweries of that type in " +
                           searchCity.query.getText() + ", " + searchCity.country.getValue());
                } // if
            } else {
                System.out.println("Sorry we could not find any breweries of that type");

            } // if
        } catch (IOException | InterruptedException e) {
            Platform.runLater(() -> alertError(e));
        }
    } // jsonResponse

    private void apiJsonResponse() {
        try {
            // form URI
            String country = URLEncoder.encode(breweryCountry, StandardCharsets.UTF_8);
            country = country.replaceAll("\\+", "%20");
            uri = COUNTRIES_API + country;
            System.out.println(uri);
            // build request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            // send request / receive response in the form of a String
            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());
            // ensure the request is okay
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if
            // get request body (the content we requested)
            String jsonString = response.body();

            CountryResponse[] countryResponse = GSON
                .fromJson(jsonString, CountryResponse[].class);
            countryResponse = this.filterArray(countryResponse);
            this.createNecessaryArrays(countryResponse); // create the URL array list from the
                                                // countryResponse class
        } catch (IOException | InterruptedException e) {
            Platform.runLater(() -> alertError(e));
        }
    } // jsonResponse

    private  void alertError(Throwable cause) {
        TextArea text = new TextArea("URI: " + uri + "\nException: " + cause.toString());
        text.setEditable(false);
        Alert alert = new Alert(AlertType.ERROR);
        alert.getDialogPane().setContent(text);
        alert.setResizable(true);
        alert.showAndWait();
    } // alertError

    private CountryResponse[] filterArray(CountryResponse[] countryResponse) {
        for (int i = 0; i < countryResponse.length; i ++) {
            if (!countryResponse[i].name.common.equals(breweryCountry)) {
                countryResponse[i] = null;
            } // if
        } // for
        return countryResponse;

    }

    private void createNecessaryArrays(Object response) {
        System.out.println();
        System.out.println("********** PRETTY JSON STRING: **********");
        System.out.println(GSON.toJson(response));
        System.out.println();
//        System.out.println("********** PARSED RESULTS: **********");
        // System.out.printf("resultCount = %s\n", itunesResponse.resultCount);
        // for (int i = 0; i < itunesResponse.results.length; i++) {
        //     System.out.printf("itunesResponse.results[%d]:\n", i);
        //     ItunesResult result = itunesResponse.results[i];
        //     System.out.printf(" - wrapperType = %s\n", result.wrapperType);
        //     System.out.printf(" - kind = %s\n", result.kind);
        //     System.out.printf(" - artworkUrl100 = %s\n", result.artworkUrl100);
        // } // for
    } // parseItunesResponse


} // ApiApp
