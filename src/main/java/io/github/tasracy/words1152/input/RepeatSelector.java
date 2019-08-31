package io.github.tasracy.words1152.input;

import javax.swing.JOptionPane;

public final class RepeatSelector extends AbstractSubButton<Boolean> {

	private static final long serialVersionUID = 1L;
	private static final String[] choices = {"有効", "無効"};

	public RepeatSelector() {
		super("繰り返し: %b", true);
	}

	@Override
	public Boolean inputDialog(Boolean nowValue) {
		int result = JOptionPane.showOptionDialog(null, "繰り返し", "設定の変更", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, nowValue);
		if(result == JOptionPane.CLOSED_OPTION) {
			return nowValue;
		}
		return result == 0;
	}

	@Override
	public void setLabel(Boolean objects) {
		setText(String.format("繰り返し: %s", objects ? "有効" : "無効"));
	}
}
