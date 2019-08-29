package io.github.tasracy.words1152;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import io.github.tasracy.words1152.parts.ProgressPanel;

public final class ExecutionDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private RunTask thread;
	private JButton stopButton;
	private ProgressPanel progressPanel;

	public ExecutionDialog(MainFrame mainFrame) {
		progressPanel = new ProgressPanel();
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		stopButton = new JButton();
		stopButton.setText("Stop");
		stopButton.setPreferredSize(new Dimension(80, 30));
		stopButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		stopButton.addActionListener(this);
		stopButton.setActionCommand("stop");
		statusPanel.add(stopButton);
		add(statusPanel, BorderLayout.SOUTH);
		add(progressPanel, BorderLayout.CENTER);

		thread = new RunTask(progressPanel, mainFrame.getInputPanel().getInputText(), mainFrame.getStatusPanel().delayStatus.getValue(), mainFrame.getStatusPanel().repetitionStatus.getValue());
		thread.start();
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("stop")) {
			if(thread.isAlive()) {
				thread.setRunning(false);
				stopButton.setText("Exit");
				progressPanel.setStop();
			}else {
				setVisible(false);
			}
		}
	}
}
