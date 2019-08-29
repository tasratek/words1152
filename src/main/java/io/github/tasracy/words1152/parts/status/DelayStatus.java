package io.github.tasracy.words1152.parts.status;

public final class DelayStatus extends StatusLabel<Integer> {

	private static final long serialVersionUID = 1L;

	private int delay = 0;

	public DelayStatus() {
		super("Delay: 0ms");
	}

	@Override
	public boolean setValue(String value) {
		int result;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		if (result < 0) {
			return false;
		}
		delay = result;
		setText(String.format("Delay: %dms", delay));
		return true;
	}

	@Override
	public String getValueString() {
		return String.valueOf(delay);
	}

	@Override
	public Integer getValue() {
		return delay;
	}
}
