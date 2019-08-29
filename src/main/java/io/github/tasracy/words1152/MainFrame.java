package io.github.tasracy.words1152;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import io.github.tasracy.words1152.input.InputPanel;
import io.github.tasracy.words1152.parts.StatusPanel;

public final class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private InputPanel inputPanel;
	private StatusPanel statusPanel;

	public MainFrame(String title) {
		super(title);

		statusPanel = new StatusPanel();
		add(statusPanel, BorderLayout.SOUTH);
		inputPanel = new InputPanel(this);
		add(inputPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		MainFrame frame = new MainFrame("Words1152");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480, 120);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public InputPanel getInputPanel() {
		return inputPanel;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}
}
