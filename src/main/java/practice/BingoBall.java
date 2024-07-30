package main.java.practice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ビンゴボールを処理するクラス
 */
public class BingoBall {

	/**
	 * 取り出されたビンゴボールを表示します
	 * @param number ビンゴボールの値
	 */
	public static void printingNumber(int number) {
		System.out.println("ball[" + number + "]：" + number);
	}

	/**
	 * ビンゴボールの数字をシャッフルしたリストを作成します。
	 * @return 1～75をシャッフルした数値のリスト
	 */
	public static ArrayList<Integer> generateBingoNumber() {
		ArrayList<Integer> numbers = new ArrayList<>();

		// 1～75を昇順に追加
		for (int i = 1; i <= 75; i++) {
			numbers.add(i);
		}

		// リスト内の数字をシャッフルする
		Collections.shuffle(numbers);

		return numbers;
	}

}
