package io.github.tasracy.words1152.parts.status;

public final class RepetitionStatus extends StatusLabel<Boolean> {

	private static final long serialVersionUID = 1L;

	private boolean isRepeat;

	public RepetitionStatus() {
		super("Repeat: true");
		isRepeat = true;
	}

	@Override
	public boolean setValue(String value) {
		boolean result;
		if (value.equalsIgnoreCase("true")) {
			result = true;
		} else if (value.equalsIgnoreCase("false")) {
			result = false;
		} else {
			return false;
		}
		isRepeat = result;
		setText(String.format("Repeat: %b", isRepeat));
		return true;
	}

	@Override
	public String getValueString() {
		return String.valueOf(isRepeat);
	}

	@Override
	public Boolean getValue() {
		return isRepeat;
	}
}
