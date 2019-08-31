package io.github.tasracy.words1152.execute;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DataPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTextField valueLabel;
	private final String format;

	public DataPane(String title, String format, Object... objects) {
		this.format = format;
		JLabel titleLabel = new JLabel();
//		titleLabel.setOpaque(true);/////////////////////////////////
//		titleLabel.setBackground(WordsUtils.getRandomColor());/////////////////////////////////////
		titleLabel.setPreferredSize(new Dimension(120,
				(int) titleLabel.getPreferredSize().getHeight()));
		titleLabel.setText(title.concat("： "));
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		valueLabel = new JTextField();
		valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valueLabel.setEditable(false);
		valueLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		add(titleLabel, BorderLayout.WEST);
		add(valueLabel, BorderLayout.CENTER);

		setLabel(objects);
	}

	public void setLabel(Object... objects) {
		valueLabel.setText(String.format(format, objects));
	}
}
