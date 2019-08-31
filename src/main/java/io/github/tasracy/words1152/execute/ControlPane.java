package io.github.tasracy.words1152.execute;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import io.github.tasracy.words1152.processing.IRunControl;

public class ControlPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JButton stopButton;
	private final JButton waitButton;

	public ControlPane(IRunControl control) {
		stopButton = new JButton();
		waitButton = new JButton();
		stopButton.setText("停止");
		stopButton.addActionListener(e -> control.stop());
		waitButton.setText("一時停止");
		waitButton.addActionListener(e -> {
			control.toggleWait();
			waitButton.setEnabled(!waitButton.isEnabled());
		});

		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(stopButton);
		add(waitButton);
	}
}
