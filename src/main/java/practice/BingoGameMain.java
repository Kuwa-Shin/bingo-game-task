package main.java.practice;

import java.util.ArrayList;
import java.util.Collections;

public class BingoGameMain {

	public static void main(String[] args) {

		// ビンゴボールの取り出し
		ArrayList<Integer> ballNumbers = generateBingoNumber();

		// ビンゴカードの作成
		String[][] bingoCard = generateBingoCard();

		for (int number : ballNumbers) {

			// ビンゴボールの数字を表示する
			printingNumber(number);

			// カードに穴あけする
			punchingCard(bingoCard, number);

			// カードの状態を表示する
			printingCard(bingoCard);

			// リーチ数とビンゴ数を表示する
			printingReach(bingoCard);
		}

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

	/**
	 * ビンゴカードを作成します
	 * @return 5*5の数値を含む二次元配列
	 */
	public static String[][] generateBingoCard() {

		String[][] card = new String[5][5];

		for (int col = 0; col < 5; col++) {
			ArrayList<Integer> colNumber = new ArrayList<>();
			// 各列に設定しうる値をリストに格納
			for (int i = 1; i <= 15; i++) {
				colNumber.add(i + (col * 15));
			}
			// リスト内をシャッフルする
			Collections.shuffle(colNumber);

			for (int row = 0; row < 5; row++) {
				// シャッフルしたcolNumberリストから値を0埋め2桁で設定する
				card[row][col] = String.format("%02d", colNumber.get(row));
			}
			// カードの真ん中にFREEを設定する
			card[2][2] = "FREE";
		}

		return card;
	}

	/**
	 * 取り出されたビンゴボールを表示します
	 * @param number ビンゴボールの値
	 */
	public static void printingNumber(int number) {
		System.out.println("ball[" + number + "]：" + number);
	}

	/**
	 * ビンゴカードにビンゴボールの値が存在した場合、穴をあける
	 * @param card ビンゴカード
	 * @param number ビンゴボールの値
	 */
	public static void punchingCard(String[][] card, int number) {

		int col;
		// ビンゴボールの数字の大きさによって列を絞る
		if (number <= 15) {
			col = 0;
		} else if (number <= 30) {
			col = 1;
		} else if (number <= 45) {
			col = 2;
		} else if (number <= 60) {
			col = 3;
		} else {
			col = 4;
		}

		for (int row = 0; row < 5; row++) {
			if (card[row][col].equals("FREE")) {
				continue;
			}

			// int型に変換可能か判定する
			if (!isInteger(card[row][col])) {
				continue;
			}

			if (number == Integer.parseInt(card[row][col])) {
				card[row][col] = "(" + card[row][col] + ")";
			}
		}

	}

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
	 * ビンゴカードの状態を表示する
	 * @param card ビンゴカード
	 */
	public static void printingCard(String card[][]) {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				System.out.printf("%-6s", card[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * リーチ数とビンゴ数を算出し、表示する
	 * @param card ビンゴカード
	 */
	public static void printingReach(String card[][]) {
		int reachCount = 0;
		int bingoCount = 0;
		int rowPuchingCount = 0;

		// 縦列の確認
		for (int col = 0; col < 5; col++) {
			for (int row = 0; row < 5; row++) {
				if (card[row][col].startsWith("(") || card[row][col].equals("FREE")) {
					rowPuchingCount++;
				}
			}
			if (rowPuchingCount == 4) {
				reachCount++;
			}
			if (rowPuchingCount == 5) {
				bingoCount++;
			}
			rowPuchingCount = 0;
		}

		// 横列の確認
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (card[row][col].startsWith("(") || card[row][col].equals("FREE")) {
					rowPuchingCount++;
				}
			}
			if (rowPuchingCount == 4) {
				reachCount++;
			}
			if (rowPuchingCount == 5) {
				bingoCount++;
			}
			rowPuchingCount = 0;
		}

		// 左上から右下の斜めの確認
		for (int i = 0; i < 5; i++) {
			if (card[i][i].startsWith("(") || card[i][i].equals("FREE")) {
				rowPuchingCount++;
			}
		}
		if (rowPuchingCount == 4) {
			reachCount++;
		}
		if (rowPuchingCount == 5) {
			bingoCount++;
		}

		rowPuchingCount = 0;

		// 右上から左下の斜めの確認
		for (int i = 0; i < 5; i++) {
			if (card[i][4 - i].startsWith("(") || card[i][4 - i].equals("FREE")) {
				rowPuchingCount++;
			}
		}
		if (rowPuchingCount == 4) {
			reachCount++;
		}
		if (rowPuchingCount == 5) {
			bingoCount++;
		}

		System.out.println("reach:" + reachCount);
		System.out.println("bingo:" + bingoCount);
		System.out.println("----------------------------");
	}
}
