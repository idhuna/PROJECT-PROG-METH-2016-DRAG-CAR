package lib;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {

	private static int mouseX, mouseY;
	private static boolean mouseLeftDown, mouseRightDown, mouseOnScreen;
	private static boolean mouseLeftLastDown, mouseRightLastDown;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		/* fill code */
		InputUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		/* fill code */
		InputUtility.mouseY = mouseY;
	}

	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}

	public static void setMouseLeftDown(boolean mouseLeftDown) {
		/* fill code */
		InputUtility.mouseLeftDown = mouseLeftDown;
	}

	public static boolean isMouseRightDown() {
		return mouseRightDown;
	}

	public static void setMouseRightDown(boolean mouseRightDown) {
		/* fill code */
		InputUtility.mouseRightDown = mouseRightDown;
	}

	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		/* fill code */
		InputUtility.mouseOnScreen = mouseOnScreen;
	}

	public static boolean isMouseLeftClicked() {
		return mouseLeftLastDown;
	}

	public static void setMouseLeftLastDown(boolean v) {
		/* fill code */
		InputUtility.mouseLeftLastDown = v;
	}

	public static boolean isMouseRightClicked() {
		return mouseRightLastDown;
	}

	public static void setMouseRightLastDown(boolean v) {
		/* fill code */
		InputUtility.mouseRightLastDown = v;
	}

	public synchronized static boolean getKeyPressed(KeyCode keycode) {
		if(!InputUtility.keyPressed.contains(keycode)){
			return false;
		}
		return true;
	}

	public synchronized static void setKeyPressed(KeyCode keycode, boolean pressed) {
		/* fill code */
		if(!InputUtility.keyPressed.contains(keycode)){
			InputUtility.keyPressed.add(keycode);
		}
		else if(!pressed && InputUtility.keyPressed.contains(keycode)){
			InputUtility.keyPressed.remove(keycode);
		}
	}

	public synchronized static boolean getKeyTriggered(KeyCode keycode) {
		if(!InputUtility.keyTriggered.contains(keycode)){
			return false;
		}
		return true;
	}

	public synchronized static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		/* fill code */
		if(!InputUtility.keyTriggered.contains(keycode)){
			InputUtility.keyTriggered.add(keycode);
		}
		else if(!pressed && InputUtility.keyTriggered.contains(keycode)){
			InputUtility.keyTriggered.remove(keycode);
		}
	}
	
	public static String checkKeyPressed(){
		String s = "";
		for (KeyCode k : keyPressed){
			s += k.getName();
		}
		if (!(s == "")) return s;
		return null;
	}

	public static void postUpdate() {
		/* fill code */
		setMouseLeftLastDown(false);
		setMouseRightLastDown(false);
		keyTriggered.clear();
	}
}
