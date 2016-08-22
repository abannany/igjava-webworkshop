package org.worklog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.worklog.rest.RestApplication;

public class WorkLogJavaFxApp extends Application {
    
    private RestApplication restApplication;
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    private void initializeWeld() {
        WeldContainer weld = (new Weld()).initialize();
        restApplication = weld.instance().select(RestApplication.class).get();
        restApplication.init();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeWeld();
        
        WebView myWebView = new WebView();
        WebEngine webEngine = myWebView.getEngine();
        webEngine.load("http://localhost:12345/");
        
        VBox root = new VBox();
        root.getChildren().addAll(myWebView);
        
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

}
