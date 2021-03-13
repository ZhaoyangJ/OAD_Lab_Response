// Version 1
//
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CalculatorV1 extends Application
{
	private enum Operation {ADD, SUBTRACT};

	private Operation operation;	// we need these because lambda
	private int operand1;			// expressions require final or
	private int operand2;			// effectively final local variables

	public void start(Stage stage)
	{
		addContents(stage);
		stage.setTitle(getClass().getName());
		stage.show();
	}

	public void addContents(Stage stage)
	{
		FlowPane pane = new FlowPane();

		// Text field to hod a number
		TextField numberTF = new TextField();
		pane.getChildren().add(numberTF);

		// Button to represent digits
		Button[] digitBTs = new Button[10];
		for(int i = 0; i <= 9; i++)
		{
			Integer iWrapper = new Integer(i);
			digitBTs[i] = new Button(new String() + i);
			pane.getChildren().add(digitBTs[i]);
			digitBTs[i].setOnAction((e) ->
				{
					numberTF.appendText(iWrapper.toString());
					// cannot use value i directly in lambda expression
				});
		}

		//	ADD button
		Button addBT = new Button("+");
		addBT.setOnAction((e) ->
			{
				operation = Operation.ADD;
				operand1 = Integer.parseInt(numberTF.getText());
				numberTF.clear();
				// System.out.println(operation + " " + operand1);
			});

		// SUBTRACT button
		Button subtractBT = new Button("-");
		subtractBT.setOnAction((e) ->
			{
				operation = Operation.SUBTRACT;
				operand1 = Integer.parseInt(numberTF.getText());
				numberTF.clear();
			});

		Button equalBT = new Button("=");
		equalBT.setOnAction((e) ->
			{
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
					// Do nothing (one option)
				}

				numberTF.setText(new String() + result);
			});

		Button clearBT = new Button("C");
		clearBT.setOnAction((e) ->
			{
				numberTF.clear();
			});

		pane.getChildren().addAll(addBT, subtractBT, equalBT, clearBT);


		//	Set scene and stage
		Scene scene = new Scene(pane, 400, 300);
		stage.setScene(scene);
	}
}

// Note that unchecked exceptions are ignored (uncaught) by the application
