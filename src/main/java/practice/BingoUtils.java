package main.java.practice;

/**
 * ビンゴの処理で使う便利なクラス
 */
public class BingoUtils {

	/**
	 * Stringからintに変換可能か判定する
	 * @param str 文字列
	 * @return 変換可能ならtrue、不可ならfalse
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException exception) {
			return false;
		}
	}

	/**
	 * 文字間隔を揃えるための処理
	 * @param str 文字列
	 * @return 文字列
	 */
	public static String printingCenter(String str) {
		// 文字数が2文字の場合は前後にスペースを入れた文字列を返す
		if (str.length() == 2) {
			return " " + str + " ";
		} else {
			return str;
		}
	}
}
