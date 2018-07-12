package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Window extends Application {

	private TextField input;
	private Label output, hexLabel, inputLabel;
	private CheckBox deleteInputBox;
	private static String hexGlobal = "";
	private boolean deleteInput = false;


	public static String calculateOutput(double input) {
		double expo = FloatConverter.calculateExponent(input) - 127;

		double fractionIn = input / Math.pow(2, expo);

		String binary32 = FloatConverter.calculateSign(input)
				+ FloatConverter.intToBinary(FloatConverter.calculateExponent(input), 8)
				+ FloatConverter.fractionalDecimalToBinary(fractionIn);
		// System.out.println(binary32);
		
		String hex = FloatConverter.binary32ToHex(binary32);
		
//		System.out.println(binary32);
		
		hexGlobal = hex;
		// System.out.println(hex);
		return FloatConverter.hexToInt(hex.substring(0, 4)) + "\t" + FloatConverter.hexToInt(hex.substring(4, 8));
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		output = new Label("0");
		output.setAlignment(Pos.CENTER);
		hexLabel = new Label("0");
		// hexLabel.setAlignment(Pos.CENTER);
		inputLabel = new Label("You Entered: ");
		input = new TextField();
		
		
		
		deleteInputBox = new CheckBox("Delete input after pressing Enter");
	
		input.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// System.out.println("AA");
					output.setText(calculateOutput(Double.parseDouble(input.getText())));
					hexLabel.setText(hexGlobal);
					inputLabel.setText("You Entered: " + input.getText());
					if (deleteInput)
						input.clear();
				}
				if (event.getCode() == KeyCode.DELETE) {
					input.clear();
				}
			}
		});
		
		deleteInputBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				input.requestFocus();
				if(deleteInput)
					deleteInput = false;
				else
					deleteInput = true;
			}
			
		});
		
		HBox box = new HBox();
		HBox box2 = new HBox();
		HBox box3 = new HBox();
		box.setSpacing(20);
		box2.setSpacing(20);
		box3.setSpacing(20);
		
//		box.setAlignment(Pos.TOP_CENTER);
//		box3.setAlignment(Pos.CENTER);
//		
//		box2.setAlignment(Pos.BOTTOM_CENTER);
		
		box3.getChildren().add(inputLabel);
		box3.getChildren().add(deleteInputBox);

		box.getChildren().add(input);
		box.getChildren().add(output);
		box2.getChildren().add(hexLabel);
//		box.setMouseTransparent(false);
		Group layout = new Group();
//		StackPane layout = new StackPane();
//		layout.setMouseTransparent(true);
		
		box.setLayoutX(0);
		box.setLayoutY(0);
		
//		box2.setLayoutX();
//		box2.setLayoutY(60);
		
		box3.setLayoutX(0);
		box3.setLayoutY(30);
		
		layout.getChildren().add(box);
		layout.getChildren().add(box2);
		layout.getChildren().add(box3);

		Scene scene = new Scene(layout, 400, 100);

		box.setLayoutX(0);
		box.setLayoutY(0);
		
		box2.setLayoutX(scene.getWidth()/2-box2.getWidth()/2);
		box2.setLayoutY(60);
		
		box3.setLayoutX(0);
		box3.setLayoutY(35);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
