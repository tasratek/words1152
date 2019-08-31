package io.github.tasracy.words1152.input;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang3.StringUtils;

import io.github.tasracy.words1152.MainFrame;
import io.github.tasracy.words1152.WordsUtils;

public final class InputPane extends JPanel implements DocumentListener, IStartData {

	private static final long serialVersionUID = 1L;
	private final JButton startButton;
	private final JTextField textField;
	private final DelaySelector delaySelector;
	private final RepeatSelector repeatSelector;
	private final SeedSelector seedSelector;

	public InputPane(MainFrame instance) {
		textField = new JTextField();
		textField.getDocument().addDocumentListener(this);
		delaySelector = new DelaySelector();
		repeatSelector = new RepeatSelector();
		seedSelector = new SeedSelector();
		startButton = new JButton();
		startButton.setText("Start");
		startButton.setEnabled(false);
		instance.getController();
		startButton.addActionListener(e -> {
			instance.getController().start(instance);
			WordsUtils.setEnabled(instance.getExecutePane(), true);
			WordsUtils.setEnabled(instance.getInputPanel(), false);
		});
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(textField, BorderLayout.CENTER);
		mainPanel.add(startButton, BorderLayout.EAST);
		JPanel subPanel = new JPanel();
		subPanel.setBorder(new EmptyBorder(0, 10, 24, 10));
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		subPanel.add(delaySelector);
		subPanel.add(repeatSelector);
		subPanel.add(seedSelector);

		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.NORTH);
		add(subPanel, BorderLayout.CENTER);
		add(new JSeparator(), BorderLayout.SOUTH);
	}

	@Override
	public String getReqest() {
		return textField.getText();
	}

	@Override
	public short getDelay() {
		return delaySelector.getValue();
	}

	@Override
	public boolean isRepeat() {
		return repeatSelector.getValue();
	}

	@Override
	public long getSeed() {
		return seedSelector.getValue();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		textFieldUpdate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textFieldUpdate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		textFieldUpdate();
	}

	private void textFieldUpdate() {
		String text = textField.getText();
		startButton.setEnabled(text.length() > 1 && text.length() <= Byte.MAX_VALUE && !StringUtils.isEmpty(text));
	}
}
