package io.github.tasracy.words1152.input;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;

public final class DelaySelector extends AbstractSubButton<Short> {

	private static final long serialVersionUID = 1L;

	public DelaySelector() {
		super("遅延: %dms", (short) 10);
	}

	@Override
	public Short inputDialog(Short nowValue) {
		String defaultVal = nowValue.toString();
		String result;
		boolean b;
		do {
			result = JOptionPane.showInputDialog("遅延: [MAX: 9999]", defaultVal);
			if (result == null) {
				return nowValue;
			}
			if (!NumberUtils.isParsable(result)) {
				b = true;
				defaultVal = result;
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "0~9999を入力してください", "警告",
						JOptionPane.WARNING_MESSAGE);
			} else {
				b = false;
			}
		} while (b);
		return Short.parseShort(result);
	}
}
