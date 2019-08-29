package io.github.tasracy.words1152.parts.progress;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class ProgressLabel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel valueLabel;

	private static final Font font1 = new Font(Font.DIALOG, Font.PLAIN, 16);

	public ProgressLabel(String title) {
		setLayout(new BorderLayout());
		valueLabel = new JLabel();
		valueLabel.setFont(font1);
		valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel titleLabel = new JLabel();
		titleLabel.setPreferredSize(new Dimension(80, 11111));
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setText(title + ": ");
		titleLabel.setFont(font1);
		add(titleLabel, BorderLayout.WEST);
		add(valueLabel, BorderLayout.CENTER);
	}

	public void setLabel(String str) {
		valueLabel.setText(str);
	}
}
