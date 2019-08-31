package io.github.tasracy.words1152.input;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;

public class SeedSelector extends AbstractSubButton<Long> {

	private static final long serialVersionUID = 1L;

	public SeedSelector() {
		super("シード値: %d", System.currentTimeMillis());
	}

	@Override
	public Long inputDialog(Long nowValue) {
		String defaultVal = nowValue.toString();
		String result;
		boolean b;
		do {
			result = JOptionPane.showInputDialog("シード値: [数値]", defaultVal);
			if(result == null) {
				return nowValue;
			}
			if(!NumberUtils.isParsable(result)) {
				b = true;
				defaultVal = result;
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "数値を入力してください", "警告", JOptionPane.WARNING_MESSAGE);
			}else {
				b = false;
			}
		}while(b);
		return Long.parseLong(result);
	}
}
