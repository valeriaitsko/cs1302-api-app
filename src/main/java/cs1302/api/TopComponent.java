package cs1302.api;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class TopComponent extends HBox {
    Text search;
    TextField query;
    ComboBox<String> type;
    ComboBox<String> country;
    Button goButton;

    public TopComponent() {
        super(8);
        search = new Text("Search City:");
        query = new TextField("Enter a city to plan your pub crawl!");
        type = new ComboBox<String>();
        type.getItems().addAll("micro", "nano", "regional", "brewpub", "bar");
        type.setValue("Choose the type of brewery you're looking for");
        country = new ComboBox<String>();
        country.getItems().addAll(ApiApp.BREWERIES_TO_COUNTRIES.keySet());
        country.setValue("Choose a country");
        goButton = new Button("Go!");
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setHgrow(query, Priority.ALWAYS);
        query.setMaxWidth(Double.MAX_VALUE);
        this.setHgrow(search, Priority.ALWAYS);
        goButton.setMaxWidth(Double.MAX_VALUE);
        this.setHgrow(goButton, Priority.ALWAYS);
        type.setMaxWidth(Double.MAX_VALUE);
        this.setHgrow(type, Priority.ALWAYS);
        this.getChildren().addAll(search, query, type, country, goButton);
    }
}
