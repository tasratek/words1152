package io.github.tasracy.words1152;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import io.github.tasracy.words1152.execute.ExecutePane;
import io.github.tasracy.words1152.input.InputPane;
import io.github.tasracy.words1152.processing.RunControl;

public final class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF,
			Font.PLAIN, 16);
	private final InputPane inputPanel;
	private final ExecutePane executePane;
	public InputPane getInputPanel() {
		return inputPanel;
	}

	public ExecutePane getExecutePane() {
		return executePane;
	}

	private transient RunControl runControl;

	public MainFrame() {
		super("Words1152");

		runControl = new RunControl();
		inputPanel = new InputPane(this);
		executePane = new ExecutePane(runControl);

		JRootPane jrp = getRootPane();
		// jrp.setBorder(new EmptyBorder(24, 24, 24, 24));
		jrp.setBorder(new EmptyBorder(24, 24, 24, 24));
//		jrp.setBorder(new LineBorder(WordsUtils.getRandomColor(), 24));///////////////////////////////////////////
		jrp.setLayout(new BorderLayout(10, 10));
		jrp.add(inputPanel, BorderLayout.NORTH);
		jrp.add(executePane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainFrame::init);
	}

	private static void init() {
		// メインフレームの作成
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480, 640);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// ルック・アンド・フィールをOSに合わせて変更
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// すべてのコンポーネントのフォントを統一
		FontUIResource fontUIResource = new FontUIResource(DEFAULT_FONT);
		for (Map.Entry<?, ?> entry : UIManager.getDefaults().entrySet()) {
			if (entry.getKey().toString().toLowerCase().endsWith("font")) {
				UIManager.put(entry.getKey(), fontUIResource);
			}
		}
		SwingUtilities.updateComponentTreeUI(frame);
	}

	public RunControl getController() {
		return runControl;
	}
}
