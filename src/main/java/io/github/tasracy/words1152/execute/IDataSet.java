package io.github.tasracy.words1152.execute;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface IDataSet {

	void setTitle(String reqest, boolean isRepeat, long sec);
	void setInfo(byte count, List<Character> words);
	void setDelay(short reqest, short effective);
	void setSpeed(int count);
	void setClacCount(BigInteger count);
	void setSuccessCount(long count);
	void setProbability(String expected, BigDecimal calc);
	void setResult(String result);
}
