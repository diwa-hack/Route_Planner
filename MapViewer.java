// import javafx.application.Application;
// import javafx.concurrent.Worker;
// import javafx.scene.Scene;
// import javafx.scene.layout.StackPane;
// import javafx.scene.web.WebEngine;
// import javafx.scene.web.WebView;
// import javafx.stage.Stage;

// public class MapViewer extends Application {

//     private static final String HTML_CONTENT = "<!DOCTYPE html>\n" +
//             "<html>\n" +
//             "<head>\n" +
//             "    <title>Shortest path from Italy to Kazakhstan</title>\n" +
//             "    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet/dist/leaflet.css\" />\n" +
//             "    <script src=\"https://unpkg.com/leaflet/dist/leaflet.js\"></script>\n" +
//             "    <style>\n" +
//             "        #map {\n" +
//             "            width: 100%;\n" +
//             "            height: 100%;\n" +
//             "        }\n" +
//             "    </style>\n" +
//             "</head>\n" +
//             "<body>\n" +
//             "    <div id=\"map\"></div>\n" +
//             "    <script>\n" +
//             "        var map = L.map('map').setView([51.505, -0.09], 2); // Initialize map with a view\n" +
//             "\n" +
//             "        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {\n" +
//             "            maxZoom: 19,\n" +
//             "        }).addTo(map);\n" +
//             "\n" +
//             "        // Define markers and paths\n" +
//             "        var points = [\n" +
//             "            {lat: 38.7, lng: -9.16667, title: 'Lisbon, Portugal', info: 'Lisbon, Portugal --> Madrid, Spain (plane - 26.25 hours - $656)'},\n" +
//             "            {lat: 40.4167, lng: -3.75, title: 'Madrid, Spain', info: 'Madrid, Spain --> Andorra la Vella, Andorra (train - 2.13 hours - $45)'},\n" +
//             "            {lat: 42.5167, lng: 1.53333, title: 'Andorra la Vella, Andorra', info: 'Andorra la Vella, Andorra --> Paris, France (bus - 12 hours - $146)'},\n" +
//             "            {lat: 48.8333, lng: 2.33333, title: 'Paris, France', info: 'Paris, France --> Brussels, Belgium (train - 20 hours - $80)'},\n" +
//             "            {lat: 50.85, lng: 4.35, title: 'Brussels, Belgium', info: 'Brussels, Belgium --> Amsterdam, Netherlands (train - 2.78 hours - $46.56)'},\n" +
//             "            {lat: 52.3833, lng: 4.9, title: 'Amsterdam, Netherlands', info: 'Amsterdam, Netherlands --> Berlin, Germany (train - 6.3 hours - $185)'},\n" +
//             "            {lat: 52.5, lng: 13.4167, title: 'Berlin, Germany', info: 'Berlin, Germany --> Copenhagen, Denmark (train - 6.35 hours - $185)'},\n" +
//             "            {lat: 55.6833, lng: 12.5667, title: 'Copenhagen, Denmark', info: 'Copenhagen, Denmark --> Torshavn, Faroe Islands (train - 6.47 hours - $221)'},\n" +
//             "            {lat: 62.0833, lng: -6.93333, title: 'Torshavn, Faroe Islands', info: 'Torshavn, Faroe Islands --> Reykjavik, Iceland (plane - 31 hours - $2535)'},\n" +
//             "            {lat: 64.1667, lng: -21.95, title: 'Reykjavik, Iceland', info: ''}\n" +
//             "        ];\n" +
//             "\n" +
//             "        // Add markers to map\n" +
//             "        for (var i = 0; i < points.length; i++) {\n" +
//             "            var marker = L.marker([points[i].lat, points[i].lng]).addTo(map);\n" +
//             "            marker.bindPopup(points[i].info);\n" +
//             "        }\n" +
//             "\n" +
//             "        // Add polylines to map\n" +
//             "        for (var i = 0; i < points.length - 1; i++) {\n" +
//             "            var latlngs = [\n" +
//             "                [points[i].lat, points[i].lng],\n" +
//             "                [points[i + 1].lat, points[i + 1].lng]\n" +
//             "            ];\n" +
//             "            var polyline = L.polyline(latlngs, {color: 'blue'}).addTo(map);\n" +
//             "        }\n" +
//             "    </script>\n" +
//             "</body>\n" +
//             "</html>";

//     @Override
//     public void start(Stage primaryStage) {
//         WebView webView = new WebView();
//         WebEngine webEngine = webView.getEngine();
//         webEngine.loadContent(HTML_CONTENT);

//         StackPane root = new StackPane();
//         root.getChildren().add(webView);

//         Scene scene = new Scene(root, 800, 600);

//         primaryStage.setTitle("Map Viewer");
//         primaryStage.setScene(scene);
//         primaryStage.show();

//         webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
//             if (newState == Worker.State.SUCCEEDED) {
//                 System.out.println("Map loaded successfully.");
//             }
//         });
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MapViewer extends Application {

    private static final String HTML_CONTENT = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>Shortest path from Italy to Kazakhstan</title>\n" +
            "    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet/dist/leaflet.css\" />\n" +
            "    <script src=\"https://unpkg.com/leaflet/dist/leaflet.js\"></script>\n" +
            "    <style>\n" +
            "        #map {\n" +
            "            width: 100%;\n" +
            "            height: 100%;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div id=\"map\"></div>\n" +
            "    <script>\n" +
            "        var map = L.map('map').setView([51.505, -0.09], 2); // Initialize map with a view\n" +
            "\n" +
            "        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {\n" +
            "            maxZoom: 19,\n" +
            "        }).addTo(map);\n" +
            "\n" +
            "        // Define markers and paths for the fastest path\n" +
            "        var fastestPoints = [\n" +
            "            {lat: 38.7, lng: -9.16667, title: 'Lisbon, Portugal', info: 'Lisbon, Portugal --> Madrid, Spain (plane - 1 hour - $100)'},\n" +
            "            {lat: 40.4167, lng: -3.75, title: 'Madrid, Spain', info: 'Madrid, Spain --> Paris, France (plane - 2 hours - $150)'},\n" +
            "            {lat: 48.8333, lng: 2.33333, title: 'Paris, France', info: 'Paris, France --> Berlin, Germany (plane - 1.5 hours - $120)'},\n" +
            "            {lat: 52.5, lng: 13.4167, title: 'Berlin, Germany', info: 'Berlin, Germany --> Copenhagen, Denmark (plane - 1 hour - $80)'},\n" +
            "            {lat: 55.6833, lng: 12.5667, title: 'Copenhagen, Denmark', info: 'Copenhagen, Denmark --> Torshavn, Faroe Islands (plane - 2 hours - $200)'},\n" +
            "            {lat: 62.0833, lng: -6.93333, title: 'Torshavn, Faroe Islands', info: 'Torshavn, Faroe Islands --> Reykjavik, Iceland (plane - 1.5 hours - $180)'},\n" +
            "            {lat: 64.1667, lng: -21.95, title: 'Reykjavik, Iceland', info: ''}\n" +
            "        ];\n" +
            "\n" +
            "        // Add markers and polylines for the fastest path\n" +
            "        for (var i = 0; i < fastestPoints.length; i++) {\n" +
            "            var marker = L.marker([fastestPoints[i].lat, fastestPoints[i].lng]).addTo(map);\n" +
            "            marker.bindPopup(fastestPoints[i].info);\n" +
            "        }\n" +
            "\n" +
            "        for (var i = 0; i < fastestPoints.length - 1; i++) {\n" +
            "            var latlngs = [\n" +
            "                [fastestPoints[i].lat, fastestPoints[i].lng],\n" +
            "                [fastestPoints[i + 1].lat, fastestPoints[i + 1].lng]\n" +
            "            ];\n" +
            "            var polyline = L.polyline(latlngs, {color: 'blue'}).addTo(map);\n" +
            "        }\n" +
            "\n" +
            "        // Define markers and paths for the cheapest path\n" +
            "        var cheapestPoints = [\n" +
            "            {lat: 38.7, lng: -9.16667, title: 'Lisbon, Portugal', info: 'Lisbon, Portugal --> Madrid, Spain (bus - 8 hours - $50)'},\n" +
            "            {lat: 40.4167, lng: -3.75, title: 'Madrid, Spain', info: 'Madrid, Spain --> Paris, France (train - 10 hours - $80)'},\n" +
            "            {lat: 48.8333, lng: 2.33333, title: 'Paris, France', info: 'Paris, France --> Berlin, Germany (bus - 12 hours - $60)'},\n" +
            "            {lat: 52.5, lng: 13.4167, title: 'Berlin, Germany', info: 'Berlin, Germany --> Copenhagen, Denmark (bus - 8 hours - $40)'},\n" +
            "            {lat: 55.6833, lng: 12.5667, title: 'Copenhagen, Denmark', info: 'Copenhagen, Denmark --> Torshavn, Faroe Islands (ferry - 24 hours - $100)'},\n" +
            "            {lat: 62.0833, lng: -6.93333, title: 'Torshavn, Faroe Islands', info: 'Torshavn, Faroe Islands --> Reykjavik, Iceland (ferry - 36 hours - $150)'},\n" +
            "            {lat: 64.1667, lng: -21.95, title: 'Reykjavik, Iceland', info: ''}\n" +
            "        ];\n" +
            "\n" +
            "        // Add markers and polylines for the cheapest path\n" +
            "        for (var i = 0; i < cheapestPoints.length; i++) {\n" +
            "            var marker = L.marker([cheapestPoints[i].lat, cheapestPoints[i].lng]).addTo(map);\n" +
            "            marker.bindPopup(cheapestPoints[i].info);\n" +
            "        }\n" +
            "\n" +
            "        for (var i = 0; i < cheapestPoints.length - 1; i++) {\n" +
            "            var latlngs = [\n" +
            "                [cheapestPoints[i].lat, cheapestPoints[i].lng],\n" +
            "                [cheapestPoints[i + 1].lat, cheapestPoints[i + 1].lng]\n" +
            "            ];\n" +
            "            var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);\n" +
            "        }\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>";

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(HTML_CONTENT);

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Map Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                System.out.println("Map loaded successfully.");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
