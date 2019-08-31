package io.github.tasracy.words1152.processing;

import java.math.BigInteger;
import java.util.TimerTask;

public class CountTimer extends TimerTask {

	private final RunControl runControl;
	private BigInteger i;
	private BigInteger lastCount;

	public CountTimer(RunControl runControl) {
		this.runControl = runControl;
		i = BigInteger.ZERO;
		lastCount = BigInteger.ZERO;
	}

	@Override
	public void run() {
		runControl.getDataSet().setTitle(runControl.getStartData().getReqest(), runControl.getStartData().isRepeat(), System.currentTimeMillis() - runControl.getStart());
		i = i.add(BigInteger.ONE);
		if(i.remainder(BigInteger.valueOf(100L)).compareTo(BigInteger.ZERO) == 0) {
			BigInteger nowCount = runControl.getCount();
			runControl.getDataSet().setSpeed(nowCount.subtract(lastCount).intValue());
			lastCount = runControl.getCount();
		}
	}
}
