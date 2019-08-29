package io.github.tasracy.words1152;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Timer;

import org.apache.commons.lang3.RandomUtils;

import io.github.tasracy.words1152.parts.ProgressPanel;

public class RunTask extends Thread implements ActionListener {

	private ProgressPanel progressPanel;
	private final String string;
	private final int delay;
	private final boolean isRepeat;
	private Timer timer;
	private long timeDelay;
	private long startTime;
	private long count;
	private int success;

	private boolean isRunning;

	// 使われている文字セット
	private final ArrayList<Character> set;

	public RunTask(ProgressPanel progressPanel, String string, int delay,
			boolean isRepeat) {
		this.progressPanel = progressPanel;
		this.string = string;
		this.delay = delay;
		this.isRepeat = isRepeat;
		success = 0;

		isRunning = true;
		startTime = System.currentTimeMillis();
		count = 0;

		set = new ArrayList<>();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if(!set.contains(c)) {
				set.add(c);
			}
		}

		progressPanel.getTrueProgress().setLabel("0");
		progressPanel.getProbabilityProgress().setLabel("0.00%");
		progressPanel.getTitleProgress().setLabel(string + "[delay: " + delay
				+ "ms, " + "repeat: " + isRepeat + "]");
		progressPanel.getInfoProgress()
				.setLabel("word count: " + set.size() + set.toString());
		double expected = 1 / Math.pow(set.size(), string.length());
		progressPanel.getExpectedProgress()
				.setLabel(BigDecimal.valueOf(expected * 100).toPlainString() + "%");
		progressPanel.getDelayProgress().setLabel(delay + "ms");

		timer = new Timer(10, this);
		timer.start();
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {

		try {
			sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (isRunning) {

			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < string.length(); i++) {
				builder.append(
						set.get(RandomUtils.nextInt(0, set.size())));
			}
			String createString = builder.toString();

			if (success != 0 && count != 0) {
				String probability = new DecimalFormat("##0.0000000000%")
						.format((double) success / count);
				progressPanel.getProbabilityProgress().setLabel(probability);
			}

			long nowDelay = System.currentTimeMillis();
			progressPanel.getDelayProgress()
					.setLabel(nowDelay - timeDelay + "ms");
			timeDelay = nowDelay;
			count++;
			progressPanel.getResultProgress().setLabel(createString);
			progressPanel.getCountProgress().setLabel(count + "");

			if (createString.equals(string)) {
				success++;
				progressPanel.getTrueProgress().setLabel(success + "");
				if (!isRepeat) {
					timer.stop();
					break;
				}
			}

			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			DecimalFormat decimalFormat = new DecimalFormat("0.000");
			double de = (double) (System.currentTimeMillis() - startTime)
					/ 1000;
			progressPanel.getTimeProgress()
					.setLabel(decimalFormat.format(de) + "s");
		}
	}

	public static String format(double d) {
		if (d == (long) d) {
			return String.format("%d", (int) d);
		} else {
			return String.format("%s", d);
		}
	}
}
