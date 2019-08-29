package io.github.tasracy.words1152.parts;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import io.github.tasracy.words1152.parts.progress.ProgressLabel;

public final class ProgressPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JProgressBar bar;

	//タイトル [Delay: 10ms]
	private ProgressLabel titleProgress;
	//入力された文字についての解析
	private ProgressLabel infoProgress;
	//予想確率
	private ProgressLabel expectedProgress;
	//試行回数
	private ProgressLabel countProgress;
	//経過時間
	private ProgressLabel timeProgress;
	//Delay
	private ProgressLabel delayProgress;
	//出力された文字
	private ProgressLabel resultProgress;
	//完成した回数
	private ProgressLabel trueProgress;
	//確率
	private ProgressLabel probabilityProgress;

	public ProgressLabel getTitleProgress() {
		return titleProgress;
	}

	public ProgressLabel getInfoProgress() {
		return infoProgress;
	}

	public ProgressLabel getExpectedProgress() {
		return expectedProgress;
	}

	public ProgressLabel getCountProgress() {
		return countProgress;
	}

	public ProgressLabel getDelayProgress() {
		return delayProgress;
	}

	public ProgressLabel getTimeProgress() {
		return timeProgress;
	}

	public ProgressLabel getResultProgress() {
		return resultProgress;
	}

	public ProgressLabel getTrueProgress() {
		return trueProgress;
	}

	public ProgressLabel getProbabilityProgress() {
		return probabilityProgress;
	}

	public ProgressPanel() {
		Border border = new EmptyBorder(10, 50, 10, 50);
		setBorder(border);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		titleProgress = new ProgressLabel("request");
		add(titleProgress);
		infoProgress = new ProgressLabel("info");
		add(infoProgress);
		expectedProgress = new ProgressLabel("expected");
		add(expectedProgress);
		timeProgress = new ProgressLabel("time");
		add(timeProgress);
		delayProgress = new ProgressLabel("delay");
		add(delayProgress);
		countProgress = new ProgressLabel("count");
		add(countProgress);
		resultProgress = new ProgressLabel("result");
		add(resultProgress);
		trueProgress = new ProgressLabel("success");
		add(trueProgress);
		probabilityProgress = new ProgressLabel("probability");
		add(probabilityProgress);
		bar = new JProgressBar();
		bar.setIndeterminate(true);
		add(bar);
	}

	public void setStop() {
		bar.setIndeterminate(false);
		bar.setValue(100);
	}
}
