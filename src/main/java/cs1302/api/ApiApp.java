package cs1302.api;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import java.net.http.HttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
    public static final String BREWERY_API = "https://api.openbrewerydb.org/v1/breweries?bycity=";

    TopComponent searchCity;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */

    public ApiApp() {
        root = new VBox();
        searchCity = new TopComponent();
        root.setFillWidth(true);
    } // ApiApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        Image bannerImage = new Image("file:resources/Banner.png", 640, 100, false, false);
        ImageView banner = new ImageView(bannerImage);
        banner.setPreserveRatio(true);
        banner.setFitWidth(640);
        root.getChildren().addAll(banner, searchCity);

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

    private void jsonResponse() {
        try {
            // form URI
            String city  = URLEncoder.encode(query.getText(), StandardCharsets.UTF_8);
            String breweryType = URLEncoder.encode(type.getValue(), StandardCharsets.UTF_8);
            String limit = URLEncoder.encode("200", StandardCharsets.UTF_8);
            String searchQuery = String.format("?term=%s&media=%s&limit=%s", term, media, limit);
            uri = ITUNES_API + query;
            // build request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            // send request / receive response in the form of a String
            response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());
            // ensure the request is okay
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if
            // get request body (the content we requested)
            String jsonString = response.body();
            ItunesResponse itunesResponse = GSON
                .fromJson(jsonString, ItunesResponse.class);
            this.createUrlList(itunesResponse); // create the URL array list from the
                                                // itunesResponse class
        } catch (IOException | InterruptedException e) {
            Platform.runLater(() -> alertError(e));
        }
    } // jsonResponse

} // ApiApp
