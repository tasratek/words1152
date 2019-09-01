package io.github.tasracy.words1152;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LanguageUtils {

	private static final String FILE_PATH = "resourses/ja_JP.lang";
	private static final Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();
		try {
			PROPERTIES.load(ClassLoader.getSystemResourceAsStream(FILE_PATH));
		} catch (IOException e) {
			log.info("Could not load {}", FILE_PATH, e);
		}
	}

	/**
	 * プロパティ値を取得する
	 *
	 * @param key キー
	 * @return 値
	 */
	public static String getProperty(final String key) {
		return getProperty(key, "");
	}

	/**
	 * プロパティ値を取得する
	 *
	 * @param key キー
	 * @param defaultValue デフォルト値
	 * @return キーが存在しない場合、デフォルト値
	 *          存在する場合、値
	 */
	public static String getProperty(final String key, final String defaultValue) {
		return PROPERTIES.getProperty(key, defaultValue);
	}
}
