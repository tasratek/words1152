package io.github.tasracy.words1152;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Initial extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private Random rand;
	private JLabel resultLabel;
	private JLabel differenceLabel;
	private JLabel numLabel;

	@SuppressWarnings("deprecation")
	public Initial() {
		resultLabel = new JLabel("1");
		differenceLabel = new JLabel("2");
		numLabel = new JLabel("3");
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(3, 1));
		jPanel.add(resultLabel);
		jPanel.add(differenceLabel);
		jPanel.add(numLabel);
		add(jPanel);

		Thread thread = new Thread(this);
		thread.start();
		new Thread(() -> {
			Scanner scan = new Scanner(System.in);
			while (true) {
				String val = scan.next();
				if ("stop".equals(val)) {
					thread.stop();
					System.exit(0);
					break;
				} else if ("wait".equals(val)) {
					wait1(thread);
				}
			}
			scan.close();
		}).start();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Initial::init);
	}

	public static void init() {
		Initial frame = new Initial();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(360, 480);
		frame.setLocation(100, 100);
		frame.setVisible(true);
	}

	public synchronized void wait1(Thread thread) {
		if (thread.isAlive()) {
			try {
				thread.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			thread.notify();
		}
	}

	@Override
	public synchronized void run() {
		rand = new Random(System.currentTimeMillis());
		String input = "craft";
		BigDecimal count = BigDecimal.ZERO;
		long success = 0;
		char[] charSet = input.toCharArray();
		BigDecimal calcVal = BigDecimal.ONE
				.divide(BigDecimal.valueOf(input.length()), Byte.MAX_VALUE,
						BigDecimal.ROUND_HALF_UP)
				.pow(input.length()).stripTrailingZeros();
		while (success < Long.MAX_VALUE) {
			count = count.add(BigDecimal.ONE);
			char[] result = new char[charSet.length];
			for (int i = 0; i < charSet.length; i++) {
				result[i] = charSet[rand.nextInt(charSet.length)];
			}
			String resultString = new String(result);
			if (input.equals(resultString)) {
				success++;
			}
			BigDecimal percent = BigDecimal.valueOf(success).divide(count, 16,
					BigDecimal.ROUND_HALF_UP);
			resultLabel.setText(resultString);
			differenceLabel.setText(percent.subtract(calcVal).toPlainString());
			numLabel.setText(new StringBuilder(percent.toPlainString())
					.append("/").append(calcVal.toPlainString()).toString());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
