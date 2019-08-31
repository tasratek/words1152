package io.github.tasracy.words1152.execute;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import io.github.tasracy.words1152.WordsUtils;
import io.github.tasracy.words1152.processing.IRunControl;

public final class ExecutePane extends JPanel implements IDataSet {

	private static final long serialVersionUID = 1L;
	private final JLabel titleLabel;
	private final DataPane infoPane;
	private final DataPane delayPane;
	private final DataPane speedPane;
	private final DataPane calcCountPane;
	private final DataPane successPane;
	private final DataPane probabilityPane;
	private final DataPane resultPane;
	private final ControlPane controlPane;

	public ExecutePane(IRunControl control) {
		JPanel dataPanes = new JPanel();
		BoxLayout boxLayout = new BoxLayout(dataPanes, BoxLayout.Y_AXIS);
		dataPanes.setLayout(boxLayout);
//		dataPanes.setBorder(new LineBorder(WordsUtils.getRandomColor(), 20));///////////////////
		dataPanes.setBorder(new EmptyBorder(0, 20, 20, 20));
		infoPane = new DataPane("解析情報", "使用文字数%d文字%s", 0, Collections.emptyList().toString());
		delayPane = new DataPane("要求/有効遅延", "%dms/%dms", 0, 0);
		speedPane = new DataPane("計算速度", "%d回/秒", 0);
		calcCountPane = new DataPane("計算回数", "%s回", "0");
		successPane = new DataPane("成功回数", "%d回", 0);
		probabilityPane = new DataPane("予想/測定確率", "%s%%/%s%%", 0f, 0f);
		resultPane = new DataPane("出力", "%s", "");
		dataPanes.add(infoPane);
		dataPanes.add(delayPane);
		dataPanes.add(speedPane);
		dataPanes.add(calcCountPane);
		dataPanes.add(successPane);
		dataPanes.add(probabilityPane);
		dataPanes.add(resultPane);
		titleLabel = new JLabel();
		titleLabel.setBorder(new EmptyBorder(12, 12, 12, 12));
		controlPane = new ControlPane(control);

		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		add(titleLabel, BorderLayout.NORTH);
		add(dataPanes, BorderLayout.CENTER);
		add(controlPane, BorderLayout.SOUTH);

		WordsUtils.setEnabled(this, false);
	}

	@Override
	public void setTitle(String reqest, boolean isRepeat, long sec) {
		String repeat = isRepeat ? "有効" : "無効";
		double dSec = sec / 1000f;
		String text = String.format("%s 繰り返し: %s 経過時間: %fs", reqest, repeat, dSec);
		titleLabel.setText(text);
	}

	@Override
	public void setInfo(byte count, List<Character> words) {
		infoPane.setLabel(count, words.toString());
	}

	@Override
	public void setDelay(short reqest, short effective) {
		delayPane.setLabel(reqest, effective);
	}

	@Override
	public void setSpeed(int count) {
		speedPane.setLabel(count);
	}

	@Override
	public void setClacCount(BigInteger count) {
		calcCountPane.setLabel(count.toString());
	}

	@Override
	public void setSuccessCount(long count) {
		successPane.setLabel(count);
	}

	@Override
	public void setProbability(String expected, BigDecimal calc) {
//		String val = expected.multiply(BigDecimal.valueOf(100)).toPlainString();
//		char[] charSet = val.toCharArray();
//		for(int i = charSet.length - 1; i > 0; i--) {
//			if(charSet[i] == 0) {
//				charSet[i] = 'f';
//			}else {
//				break;
//			}
//		}
		probabilityPane.setLabel(expected, calc);
	}

	@Override
	public void setResult(String result) {
		resultPane.setLabel(result);
	}
}
