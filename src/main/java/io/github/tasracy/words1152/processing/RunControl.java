package io.github.tasracy.words1152.processing;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import io.github.tasracy.words1152.MainFrame;
import io.github.tasracy.words1152.execute.IDataSet;
import io.github.tasracy.words1152.input.IStartData;

public class RunControl implements IRunControl {

	private CountTimer countTimer;
	private IDataSet dataSet;
	private IStartData startData;
	private boolean isExit;
	private Random random;
	private List<Character> words;

	private long start;
	private long last;
	private boolean isStarting;
	private BigInteger count;
	private long success;
	private String calcProbability;
	private Thread calcThread;
	private Timer timer;

	public IDataSet getDataSet() {
		return dataSet;
	}

	public IStartData getStartData() {
		return startData;
	}

	public long getStart() {
		return start;
	}

	public boolean isStarting() {
		return isStarting;
	}

	public void start(MainFrame instance) {
		countTimer = new CountTimer(this);
		dataSet = instance.getExecutePane();
		startData = instance.getInputPanel();
		random = new Random(startData.getSeed());
		words = new ArrayList<>();
		count = BigInteger.valueOf(0L);
		for(char c : startData.getReqest().toCharArray()) {
			if(!words.contains(c)) {
				words.add(c);
			}
		}
		dataSet.setInfo((byte)words.size(), words);
		timer = new Timer();
		calcThread = new Thread(this::calc, "CalcThread");

		timer.schedule(countTimer, 0, 10);
		calcThread.start();
	}

	public void calc() {
		start = System.currentTimeMillis();
		last = start;
		calcProbability = BigDecimal.ONE.divide(BigDecimal.valueOf(Math.pow(words.size(), startData.getReqest().length())), 8, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString();
		while(!isExit) {

			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < startData.getReqest().length(); i++) {
				char c = words.get(random.nextInt(words.size()));
				builder.append(c);
			}
			String result = builder.toString();

			dataSet.setDelay(startData.getDelay(), (short) (System.currentTimeMillis() - last));
			last = System.currentTimeMillis();
			//計算速度: 100000回/s
			count = count.add(BigInteger.valueOf(1L));
			dataSet.setClacCount(count);
			if(result.equals(startData.getReqest())) {
				success++;
				dataSet.setSuccessCount(success);
			}
			if(success != 0) {
				dataSet.setProbability(calcProbability, BigDecimal.valueOf(success * 100).divide(new BigDecimal(count), 20, BigDecimal.ROUND_HALF_UP));
			}
			dataSet.setResult(result);
			try {
				Thread.sleep(startData.getDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BigInteger getCount() {
		return count;
	}

	@Override
	public void stop() {
		timer.cancel();
		isStarting = false;//flag
		System.out.println("stop");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void toggleWait() {
		System.out.println("wait");
		if(isStarting) {	//停止する
			timer.cancel();
			calcThread.stop();
		}else {				//開始する
			calcThread.start();
			timer.schedule(countTimer, 0, 10);
		}
		isStarting = !isStarting;
	}
}
