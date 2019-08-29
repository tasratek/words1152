package io.github.tasracy.words1152.parts.status;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

public abstract class StatusLabel<V> extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;

	public StatusLabel(String initial) {
		addMouseListener(this);
		setText(initial);
		setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
	}

	public abstract boolean setValue(String value);

	public abstract String getValueString();

	public abstract V getValue();

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this && SwingUtilities.isLeftMouseButton(e)) {
			String result;
			do {
				result = JOptionPane.showInputDialog(null, "Enter a value",
						getValueString());
				if (StringUtils.isEmpty(result)) {
					break;
				}
			} while (!setValue(result));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
