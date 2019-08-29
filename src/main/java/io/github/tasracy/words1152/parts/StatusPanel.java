package io.github.tasracy.words1152.parts;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import io.github.tasracy.words1152.parts.status.DelayStatus;
import io.github.tasracy.words1152.parts.status.RepetitionStatus;

public final class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DelayStatus delayStatus;
	public RepetitionStatus repetitionStatus;

	public StatusPanel() {
		delayStatus = new DelayStatus();
		repetitionStatus = new RepetitionStatus();

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		setBackground(new Color(0xe7e7e7));
		add(delayStatus);
		add(repetitionStatus);
	}
}
