package io.github.tasracy.words1152.input;

import javax.swing.JButton;

public abstract class AbstractSubButton<V> extends JButton {

	private static final long serialVersionUID = 1L;
	private final String format;
	private transient V value;

	public AbstractSubButton(String format, V defaultValue) {
		this.format = format;
		this.value = defaultValue;
		setLabel(defaultValue);
		addActionListener(e -> {
			value = inputDialog(value);
			setLabel(value);
		});
	}

	public V getValue() {
		return value;
	}

	public void setLabel(V objects) {
		setText(String.format(format, objects));
	}

	public abstract V inputDialog(V nowValue);
}
