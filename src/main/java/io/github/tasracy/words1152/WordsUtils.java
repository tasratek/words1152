package io.github.tasracy.words1152;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Random;

import javax.swing.JSeparator;

public final class WordsUtils {

	private static final Random RANDOM = new Random(0L);

	public static void setEnabled(Component c, boolean b) {
		c.setEnabled(b);
		if (c instanceof Container) {
			for (Component comp : ((Container) c).getComponents()) {
				if(!(comp instanceof JSeparator)) {
					setEnabled(comp, b);
				}
			}
		}
	}

	public static Color getRandomColor() {
		int r = RANDOM.nextInt(255);
		int g = RANDOM.nextInt(255);
		int b = RANDOM.nextInt(255);
		return new Color(r, g, b);
	}
}
