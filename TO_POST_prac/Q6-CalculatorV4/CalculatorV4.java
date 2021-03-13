//	Version 4 - Catches and displays unchecked exceptions
//
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CalculatorV4 extends Application
{
	private enum Operation {ADD, SUBTRACT};

	private Operation operation;	// we need these because lambda
	private int operand1;			// expressions require final or
	private int operand2;			// effectively final local variables

	public void start(Stage stage) throws Exception
	{
		addContents(stage);
		stage.setTitle(getClass().getName());
		stage.show();
	}

	public void addContents(Stage stage) throws Exception
	{
		//	Use a VBox as the root of the scene graph
		VBox root = new VBox();
		root.setStyle("-fx-alignment: center; -fx-spacing: 20");

		// Banner
		Label bannerLB = new Label("Calculator");
		bannerLB.setStyle("-fx-font: 40 ARIAL");
		root.getChildren().add(bannerLB);

		// Text field to hold operand and result
		TextField numberTF = new TextField();
		numberTF.setMinWidth(150);
		numberTF.setMaxWidth(150);
		numberTF.setEditable(false);

		root.getChildren().add(numberTF);

		TextField recordTF = new TextField();
		recordTF.setEditable(false);

		// Button to represent digits
		//
		HBox digitHBox= new HBox();
		digitHBox.setStyle("-fx-alignment: center");
		Button[] digitBTs = new Button[10];
		for(int i = 0; i <= 9; i++)
		{
			Integer iWrapper = new Integer(i);
				// cannot use value i directly in lambda expression
			digitBTs[i] = new Button(new String() + i);
			digitHBox.getChildren().add(digitBTs[i]);
			digitBTs[i].setOnAction((e) ->
				{
					numberTF.appendText(iWrapper.toString());
					recordTF.appendText(iWrapper.toString());

				});
		}

		root.getChildren().add(digitHBox);


		//	ADD button
		Button addBT = new Button("+");
		addBT.setOnAction((e) ->
			{
				recordTF.appendText("+");
				operation = Operation.ADD;
				operand1 = Integer.parseInt(numberTF.getText());
				numberTF.clear();
				// recordTF.appendText("+");
			});

		// SUBTRACT button
		Button subtractBT = new Button("-");
		subtractBT.setOnAction((e) ->
			{
				recordTF.appendText("-");
				operation = Operation.SUBTRACT;
				operand1 = Integer.parseInt(numberTF.getText());
				numberTF.clear();
				// recordTF.appendText("-");
			});

		// EQUAL button
		Button equalBT = new Button("=");
		equalBT.setOnAction((e) ->
			{
				recordTF.appendText("=");

				operand2 = Integer.parseInt(numberTF.getText());
				int result = 0;
				if(operation == Operation.ADD)
				{
					result = operand1 + operand2;
				}
				else if(operation == Operation.SUBTRACT)
				{
					result = operand1 - operand2;
				}
				else
				{
					// perhaps do nothing
				}

				numberTF.setText(new String() + result);
				recordTF.appendText("" + result);
			});

		//	CLEAR button
		Button clearBT = new Button("C");
		clearBT.setOnAction((e) ->
			{
				numberTF.clear();
				recordTF.clear();
			});

		HBox operatorHBox = new HBox();
		operatorHBox.getChildren().addAll(addBT, subtractBT, equalBT, clearBT);
		operatorHBox.setStyle("-fx-alignment: center");
		root.getChildren().add(operatorHBox);

		// ADD a label at the bottom by adding 'spacer region'
		Region spacer = new Region();
		root.setVgrow(spacer, Priority.ALWAYS);
		root.getChildren().addAll(spacer, recordTF);

		//	Set scene and stage
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);

		// Catch exceptions
		// Thread.currentThread is the application thread
		//
		Thread.currentThread().setUncaughtExceptionHandler((thread, exception) ->
			{
				System.out.println("ERROR: " + exception);
				recordTF.appendText("ERROR");
        	});

	}
}