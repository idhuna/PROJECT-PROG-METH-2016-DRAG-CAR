package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class SliderRGB extends BorderPane {
	Color[] presetColors = new Color[] { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
			Color.CYAN, Color.LIGHTGREY, Color.DARKGRAY, Color.BROWN };

	Color targetColor;	
	ColorAdjust colorAdjust;
	Slider redSlider;
	Slider greenSlider;
	Slider blueSlider;
	ComboBox<Color> colorComboBox;

	public SliderRGB() {

		// colorAdjust effect
		colorAdjust = new ColorAdjust();

		// toolbar
		// -------------------------
		GridPane toolbar = new GridPane();

		// presets: show colors as rectangles in the combobox list, as hex color
		// in the combobox selection
		colorComboBox = new ComboBox<>();
		colorComboBox.getItems().addAll(presetColors);

		colorComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				updateSliders();

			}

		});
		colorComboBox.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
			@Override
			public ListCell<Color> call(ListView<Color> p) {
				return new ListCell<Color>() {
					private final Rectangle rectangle;
					{
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						rectangle = new Rectangle(100, 10);
					}

					@Override
					protected void updateItem(Color item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setGraphic(null);
						} else {
							rectangle.setFill(item);
							setGraphic(rectangle);
						}
					}
				};
			}
		});

		// sliders, value is initialized later
		redSlider = createSlider(0, 255, 255);
		greenSlider = createSlider(0, 255, 255);
		blueSlider = createSlider(0, 255, 255);

		// add nodes to gridpane
		toolbar.addRow(0, new Label("Preset"), colorComboBox);
		toolbar.addRow(1, new Label("Red"), redSlider);
		toolbar.addRow(2, new Label("Green"), greenSlider);
		toolbar.addRow(3, new Label("Blue"), blueSlider);
		
		// margin for all gridpane nodes
		for (Node node : toolbar.getChildren()) {
			GridPane.setMargin(node, new Insets(1));
		}

		// layout
		this.setTop(toolbar);

		// select 1st color and implicitly initialize the sliders and colors
		colorComboBox.getSelectionModel().selectFirst();

	}

	public void updateColor() {

		// create target color
		targetColor = Color.rgb((int) redSlider.getValue(), (int) greenSlider.getValue(), (int) blueSlider.getValue());

		// update colorAdjust
		double hue = map((targetColor.getHue() + 180) % 360, 0, 360, -1, 1);
		colorAdjust.setHue(hue);

		// use saturation as it is
		double saturation = targetColor.getSaturation();
		colorAdjust.setSaturation(saturation);

		// we use WHITE inverse brightness
		double brightness = map(targetColor.getBrightness(), 0, 1, -1, 0);
		colorAdjust.setBrightness(brightness);


	}

	private void updateSliders() {

		Color referenceColor = colorComboBox.getValue();

		redSlider.setValue(map(referenceColor.getRed(), 0, 1, 0, 255));
		greenSlider.setValue(map(referenceColor.getGreen(), 0, 1, 0, 255));
		blueSlider.setValue(map(referenceColor.getBlue(), 0, 1, 0, 255));

	}

	private Slider createSlider(double min, double max, double value) {

		Slider slider = new Slider(min, max, value);
		slider.setPrefWidth(330);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setOnMouseEntered(e -> slider.setCursor(Cursor.HAND));
		slider.setOnMouseExited(e -> slider.setCursor(Cursor.DEFAULT));
		slider.setOnMouseDragged(e -> slider.setCursor(Cursor.CLOSED_HAND));

		return slider;
	}

	// Snapshot imageChenge
	public Image createImageChanged(ImageView node) {
		node.setEffect(colorAdjust);
		SnapshotParameters parameters = new SnapshotParameters();
		parameters.setFill(Color.TRANSPARENT);
		
		Image iv = node.snapshot(parameters, null);
		return iv;
		

	}

	public static double map(double value, double start, double stop, double targetStart, double targetStop) {
		return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
	}

}
