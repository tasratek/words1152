package io.github.tasracy.words1152.process;

public class CalcTask implements Runnable {

	private IDetails details;

	public void setDetails(IDetails details) {
		this.details = details;
	}

	@Override
	public void run() {
		if(details == null) {
			throw new IllegalArgumentException("入力値が設定されていません");
		}
	}
}
