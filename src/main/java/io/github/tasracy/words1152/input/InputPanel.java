package io.github.tasracy.words1152.input;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang3.StringUtils;

import io.github.tasracy.words1152.ExecutionDialog;
import io.github.tasracy.words1152.MainFrame;

public final class InputPanel extends JPanel
		implements
			ActionListener,
			DocumentListener {

	private static final long serialVersionUID = 1L;

	private JTextField field;
	private JButton button;

	private final MainFrame mainFrame;

	public InputPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		field = new JTextField();
		field.setPreferredSize(new Dimension(0, 30));
		field.setColumns(20);
		field.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		field.getDocument().addDocumentListener(this);
		field.addActionListener(this);
		button = new JButton();
		button.setText("Start");
		button.setPreferredSize(new Dimension(80, 30));
		button.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		button.addActionListener(this);
		button.setEnabled(false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(field);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			ExecutionDialog dialog = new ExecutionDialog(mainFrame);
			dialog.setTitle("Running");
			dialog.setDefaultCloseOperation(
					WindowConstants.DO_NOTHING_ON_CLOSE);
			dialog.setSize(1280, 360);
			dialog.setLocationRelativeTo(null);
			dialog.setModal(true);
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
//			dialog.setResizable(false);
			dialog.setVisible(true);
		}else if(e.getSource() == field) {
			button.doClick();
		}
	}

	public String getInputText() {
		return field.getText();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		textUpdate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textUpdate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		textUpdate();
	}

	private void textUpdate() {
		if (field.getText().length() > 1 && !StringUtils.isEmpty(field.getText())) {
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}
	}
}
