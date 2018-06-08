package clientapp;

import clientapp.models.Exhibit;
import clientapp.models.File;
import clientapp.models.Token;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

public class Main extends Application {

    private HBox taskbar;
    private StackPane view;
    private MediaPlayer mediaPlayer;
    private ClientAPI clientAPI;
    private String token = "";

    @Override
    public void init() throws Exception {
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("resources/v.mp4").toString()));
        clientAPI = new ClientAPI();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("ArtGallery");

        // здесь мы создаём сцену, которая является содержимым окна и layout manager для неё
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 720, 550, Color.LIGHTGRAY);
        stage.setScene(scene);

        view = new StackPane();
        view.setAlignment(Pos.CENTER);
        root.setCenter(view);

        taskbar = new HBox(10);
        taskbar.setPadding(new Insets(10, 50, 50, 50));
        taskbar.setPrefHeight(150);
        taskbar.setAlignment(Pos.CENTER);
        root.setBottom(taskbar);
        changeView(setAuthForm());
        taskbar.getChildren().add(createButton("resources/icon-0.png", () -> {
            changeView(new MediaView(mediaPlayer));
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        }));

        taskbar.getChildren().add(createButton("resources/icon-1.png", () -> {
            changeView(setAccordion());
        }));

        taskbar.getChildren().add(createButton("resources/icon-2.png", () -> {
            setEditForm(new HashMap<>());
        }));

        taskbar.getChildren().add(createButton("resources/icon-3.png", () -> {
            changeView(setAuthForm());

        }));

        taskbar.getChildren().add(createButton("resources/icon-4.png", () -> {
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load(Constant.HOST);
            changeView(browser);
        }));

        stage.show();
    }

    private Node setAuthForm() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(100));
        Label l = new Label("Пожалуйста авторизуйтесь");
        Label l1 = new Label("login");
        Label l2 = new Label("password");
        Label result = new Label();
        TextField login = new TextField();
        login.setMaxWidth(250);
        PasswordField password = new PasswordField();
        password.setMaxWidth(250);

        Button button = new Button("ok");
        button.setOnMouseClicked(event -> {
            Map<String, Object> map = new HashMap<>();
            map.put("login", login.getText());
            map.put("password", password.getText());
            String answer = clientAPI.post("/jwt/login", map);
            Token t = (new Gson()).fromJson(answer, Token.class);
            this.token = t.getValue();
            result.setText(answer);
        });
        box.getChildren().addAll(l, l1, login, l2, password, button, result);
        return box;
    }

    private Node setAccordion() {
        Accordion accordion = new Accordion();

        List<Exhibit> list = null;
        try {
            URL url = new URL(Constant.HOST + "api/exhibits");
            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Exhibit>>() {
            }.getType();
            list = gson.fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null) {
            setItems(accordion, list);
        }
        return accordion;
    }

    private void setItems(Accordion accordion, List<Exhibit> list) {
        for (Exhibit ex : list) {
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            Button delete = new Button("delete");
            Label response = new Label();
            delete.setOnMouseClicked(event -> {
                String answer = clientAPI.post("api/exhibits/" + ex.getId() + "/delete", new HashMap<>());
                response.setText(answer);
            });
            Button edit = new Button("edit");
            edit.setOnMouseClicked(event -> {
                Map<String, Object> exhibit = new HashMap<>();
                exhibit.put("id", ex.getId() + "");
                exhibit.put("name", ex.getName());
                exhibit.put("content", ex.getContent());
                exhibit.put("userId", "7");
                setEditForm(exhibit);
            });
            List<Node> items = new ArrayList<>();
            items.add(new Label("Name: " + ex.getName()));
            if (!ex.getPictureFile().contains("null")) {
                ImageView image = new ImageView(ex.getPictureFile());
                image.setFitHeight(300);
                image.setFitWidth(300);
                items.add(image);
            }
            items.add(new Label("Description: " + ex.getContent()));
            items.add(new Label("Date: " + new Date(ex.getTs()).toString()));
            items.add(delete);
            items.add(edit);
            items.add(response);

            List<File> audios = ex.getImages().stream().filter(file ->
                    "audio".equals(file.getContentType())).collect(Collectors.toList());
            List<File> videos = ex.getImages().stream().filter(file ->
                    "video".equals(file.getContentType())).collect(Collectors.toList());
            items.add(new Label("videos:"));
            setMediaTrigger(videos, items);
            items.add(new Label("audios:"));
            setMediaTrigger(audios, items);

            box.getChildren().addAll(items);
            accordion.getPanes().add(new TitledPane(ex.getName(), box));
        }
    }

    private void setEditForm(Map<String, Object> exhibit) {
        VBox vbox = new VBox();
        Label l1 = new Label("name");
        Label l2 = new Label("HTML content");
        Label l3 = new Label("user id");
        TextField name = new TextField((String) exhibit.getOrDefault("name", ""));
        TextArea content = new TextArea((String) exhibit.getOrDefault("content", ""));
        TextField userId = new TextField((String) exhibit.getOrDefault("userId", ""));
        Button save = new Button("save");
        Label response = new Label();
        vbox.getChildren().addAll(l1, name, l2, content, l3, userId, save, response);
        save.setOnMouseClicked(event -> {
            Map<String, Object> map = new HashMap<>();
            System.out.println(exhibit.get("id"));
            map.put("id", exhibit.get("id"));
            map.put("name", name.getText());
            map.put("content", content.getText());
            map.put("userId", userId.getText());
            String answer = clientAPI.post(exhibit.get("id") == null ?
                    "api/exhibits/add" : "api/exhibits/" + exhibit.get("id") + "/edit", map);
            response.setText(answer);
        });
        changeView(vbox);
    }

    private void setMediaTrigger(List<File> list, List<Node> items) {
        list.forEach(elem -> {
            Label label = new Label(elem.getName());
            label.setOnMouseClicked(event -> {
                mediaPlayer = new MediaPlayer(new Media(elem.getFullPath()));
                changeView(new MediaView(mediaPlayer));
                mediaPlayer.play();
            });
            items.add(label);
        });
    }


    private boolean notEmpty(String s) {
        return s != null && !"".equals(s);
    }

    private Node createButton(String iconName, final Runnable action) {
        final double SCALE = 1.2;
        final double DURATION = 150;
        final ImageView node = new ImageView(new Image(getClass().getResource(iconName).toString(), 100, 100, false, false));

        final ScaleTransition animationGrow = new ScaleTransition(Duration.millis(DURATION), node);
        animationGrow.setToX(SCALE);
        animationGrow.setToY(SCALE);

        final ScaleTransition animationShrink = new ScaleTransition(Duration.millis(DURATION), node);
        animationShrink.setToX(1);
        animationShrink.setToY(1);

        final Reflection effect = new Reflection();
        node.setEffect(effect);

        node.setOnMouseClicked(event -> action.run());


        node.setOnMouseEntered(event -> {
            animationShrink.stop();
            animationGrow.playFromStart();
        });
        node.setOnMouseExited(event -> {
            animationGrow.stop();
            animationShrink.playFromStart();
        });
        return node;
    }

    private void changeView(Node node) {
        view.getChildren().clear();
        mediaPlayer.stop();
        view.getChildren().add(node);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
