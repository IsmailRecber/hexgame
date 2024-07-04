package com.example.demo3;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameApplication {
    private GameModel gameModel;
    private BoardView boardView;
    private GameController gameController;
    private BorderPane root;
    private Label turnLabel;
    private Label redMovesLabel;
    private Label blueMovesLabel;

    public void startGame() {
        turnLabel = new Label("Turn: RED");
        redMovesLabel = new Label("RED: 0");
        blueMovesLabel = new Label("BLUE: 0");
        Button resetButton = new Button("Reset Game");
        resetButton.setOnAction(event -> gameController.handleResetButtonClick());
        // Tahta boyutu seçimi için radyo düğmeleri
        ToggleGroup sizeGroup = new ToggleGroup();
        RadioButton rb5x5 = new RadioButton("5x5");
        rb5x5.setToggleGroup(sizeGroup);
        rb5x5.setSelected(true);
        // Varsayılan seçim
        RadioButton rb11x11 = new RadioButton("11x11");
        rb11x11.setToggleGroup(sizeGroup);

        RadioButton rb17x17 = new RadioButton("17x17");
        rb17x17.setToggleGroup(sizeGroup);

        // Radyo düğmesi seçimi işleme
        rb5x5.setOnAction(e -> createGame(5));
        rb11x11.setOnAction(e -> createGame(11));
        rb17x17.setOnAction(e -> createGame(17));

        // Düzen
        HBox sizeOptions = new HBox(7, rb5x5, rb11x11, rb17x17,resetButton, turnLabel, redMovesLabel, blueMovesLabel);
        sizeOptions.setPadding(new Insets(10));
        root = new BorderPane();
        root.setBottom(sizeOptions);

        //  İlk oyun oluşturma (varsayılan olarak 5x5)
        createGame(5);
        root.setCenter(boardView);


        Stage newStage = new Stage();
        newStage.setTitle("HexGame");
        Scene scene = new Scene(root, 1500, 800);
        newStage.setScene(scene);
        newStage.show();
    }

    public void createGame(int boardSize) {
        gameModel = new GameModel(boardSize);
        boardView = new BoardView(gameModel);
        gameController = new GameController(gameModel, boardView,turnLabel, redMovesLabel, blueMovesLabel);

        // Yeni boardView'ı ekleme veya güncelleme
        if (root.getCenter() == null) {
            root.setCenter(boardView);
        } else {
            root.setCenter(null); // Önce eski boardView'ı kaldır
            root.setCenter(boardView); // Sonra yeni boardView'ı ekle// }
        }
    }
}

