//@@author Raghav-B
package eggventory.ui;

import eggventory.Eggventory;
import eggventory.StockList;
import eggventory.items.Stock;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is a controller class used to control the Gui.fxml from the entry point for
 * our application, the Eggventory class. Inherits most of its functionality from Cli
 * to ensure backwards compatibility with testing and if we choose to fall back to a
 * Cli implementation. Overrides some Cli functionality to interface with the Gui instead
 * of Cli.
 */
public class Gui extends Application implements Ui  {
    @FXML
    private TextField inputField;
    @FXML
    private TextArea outputField;
    @FXML
    private TableView outputTable;
    @FXML
    private ScrollPane outputTableScroll;

    private Eggventory.MainLoop runMethod;

    /**
     * Takes in references to some of the nodes in the JavaFX Gui, so that they can be
     * controlled by Command.execute() functions and changes can be represented in the Gui
     * instead of Cli.
     */
    public Gui() {
    }

    public void initialize(Runnable runMethod) {
        this.runMethod = runMethod;
        Application.launch(this.getClass());
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Gui.fxml"));
            fxmlLoader.setController(this);
            stage = fxmlLoader.load();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(runMethod.dick);

        // Event handler for pressing ENTER
        inputField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                //runMethod.run();
                System.out.println("hmm");
            }
        });

        // Event handler for pressing TAB
        inputField.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->  {
            if (keyEvent.getCode() == KeyCode.TAB) {
                inputField.appendText("Tab has been pressed! ");
                keyEvent.consume();
            }
        });

        printIntro();
    }

    public String read() {
        String userInput = inputField.getText();

        if (!userInput.equals("")) { // Check for blank input
            inputField.setText("");
            outputField.appendText("\n" + userInput);
        } else {

        }

        return userInput;
    }

    /**
     * Prints text output in the outputField TextArea.
     * @param printString The raw String to be printed out, after some extra formatting.
     */
    public String print(String printString) {
        String output = Ui.super.printFormatter(printString);
        outputField.appendText("\n" + output);

        return output;
    }

    /**
     * Can be called to redraw the table output as and when needed. Takes in a StockList object
     * that it uses to redraw the entire table.
     * @param stockList Input StockList object to be used to draw the table.
     */
    public void drawTable(StockList stockList) {
        TableColumn<String, Stock> stockTypeCol = new TableColumn<>("Stock Type");
        stockTypeCol.setCellValueFactory(new PropertyValueFactory<>("stockType"));

        TableColumn<String, Stock> stockCodeCol = new TableColumn<>("Stock Code");
        stockCodeCol.setCellValueFactory(new PropertyValueFactory<>("stockCode"));

        TableColumn<Integer, Stock> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<String, Stock> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        /*for (int i = 0; i < stockList.getStockQuantity(); i++) {
            for (int i = 0; i < stockList.getStockType().getQuantity(); i++) {
                outputTable.getItems().add(stockList.getStockType())
            }
        }
        // TODO: Can add more columns for loaned, lost, and minimum later...
        */

        outputTable.getColumns().removeAll();
        outputTable.getColumns().addAll(stockTypeCol, stockCodeCol, quantityCol, descriptionCol);
    }
}
