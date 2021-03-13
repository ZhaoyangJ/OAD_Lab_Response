import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class FontSelectorV1 extends Application
{
	public void start(Stage stage)
	{
		build(stage);
		stage.setTitle(getClass().getName());
		stage.show();
	}

	String fontFamily = "SANSSERIF";		// SERIF, MONOSPACED
	String fontSize = "20px";				// The default is 14 pixel
	String fontWeight = "NORMAL"; 		// BOLD (and others we don't use)
	String fontStyle = "NORMAL";			// ITALIC

	public void build(Stage stage)
	{
		// Define label for message
		Label messageLB = new Label("Welcome to FX");

		// System.out.println(getFontCSSRule(fontFamily, fontSize, fontWeight, fontStyle));
		messageLB.setStyle(getFontCSSRule(fontFamily, fontSize, fontWeight, fontStyle));

		// Define radio buttons for font names
		//
		RadioButton sansSerifRB = new RadioButton("Sans Serif");
		RadioButton serifRB = new RadioButton("Serif");
		RadioButton monospacedRB = new RadioButton("Monospaced");

		ToggleGroup fontTG = new ToggleGroup();
		serifRB.setToggleGroup(fontTG);
		sansSerifRB.setToggleGroup(fontTG);
		monospacedRB.setToggleGroup(fontTG);
		sansSerifRB.setSelected(true);

		HBox fontHB = new HBox();
		fontHB.getChildren().addAll(sansSerifRB, serifRB, monospacedRB);
		fontHB.setStyle("-fx-spacing: 20; -fx-alignment: center");

		//System.out.println(messageLB.getFont().getSize());
		System.out.println(messageLB.getFont());

		// Define Bold check box for weight
		CheckBox boldCB = new CheckBox("Bold");
		boldCB.setSelected(false);

		// Define italic checkbox for posture
		CheckBox italicCB = new CheckBox("Italic");

		// Define choic box for font size
		ChoiceBox<String> sizeChoiceBox = new ChoiceBox<String>();
		sizeChoiceBox.getItems().addAll("10", "20", "30", "40", "50");
		sizeChoiceBox.setValue("20");	// Must have a value to parse

		// Define button to display message in chosen font
		Button displayBT = new Button("Display");
		displayBT.setOnAction((e) ->
			{
				// Get font family
				RadioButton selectedRB = (RadioButton) fontTG.getSelectedToggle();
				if(selectedRB == sansSerifRB)
				{
					fontFamily = "SANSSERIF";
				}
				else if (selectedRB == serifRB)
				{
					fontFamily = "SERIF";
				}
				else
				{
					fontFamily = "MONOSPACED";
				}

				// Get weight
				fontWeight = boldCB.isSelected()? "BOLD" : "NORMAL";

				// Get font style
				fontStyle = italicCB.isSelected()? "ITALIC" : "NORMAL";

				// Get font size
				fontSize = sizeChoiceBox.getValue().trim();

				messageLB.setStyle(
					getFontCSSRule(fontFamily, fontSize, fontWeight, fontStyle));
			});

		VBox root = new VBox();
		root.getChildren().addAll(fontHB, boldCB, italicCB, sizeChoiceBox,
			displayBT, messageLB);


		root.setStyle("-fx-alignment: center; -fx-spacing: 20px");


		//	Set scene and stage
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
	}

	private String getFontCSSRule(String fontFamily, String fontSize, String fontWeigth, String fontStyle)
	{
		return	"-fx-font-family: " + fontFamily + ";" +
					"-fx-font-size: " + fontSize + ";" +
					"-fx-font-weight: " + fontWeight + ";" +
					"-fx-font-style: " + fontStyle + ";";
	}

}

/*
From FX CSS Reference:

<font>

JavaFX CSS supports the ability to specify fonts using separate family, size, style, and weight properties, as well as the ability to specify a font using a single shorthand property. There are four value types related to fonts plus a shorthand property that encompasses all four properties. The font-related types are as follows.

<font-family>The string name of the font family. An actual font family name available on the system can be used, or one of the following generic family names can be used:

    'serif' (e.g., Times)
    'sans-serif' (e.g., Helvetica)
    'cursive' (e.g., Zapf-Chancery)
    'fantasy' (e.g., Western)
    'monospace' (e.g., Courier)

<font-size> The size of the font, using the <size> syntax.

<font-style> The font's style, using the following syntax:
[ normal | italic | oblique ]

<font-weight> The font's weight, using the following syntax:
[ normal | bold | bolder | lighter | 100 | 200 | 300 | 400 | 500 | 600 | 700 | 800 | 900 ]

<font> This font shorthand property can be used in place of the above properties. It uses the following syntax:
[[ <font-style> || <font-weight> ]? <font-size> <font-family> ]
*/
