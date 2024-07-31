package main.java.practice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ビンゴカードを処理するクラス
 */
public class BingoCard {

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
		}
		// カードの真ん中にFREEを設定する
		card[2][2] = "FREE";

		return card;
	}

	/**
	 * ビンゴカードにビンゴボールの値が存在した場合、穴を開ける
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

			// int型に変換可能か判定する(穴が開いているか)
			if (!BingoUtils.isInteger(card[row][col])) {
				// 空いていたら次に進む
				continue;
			}

			// 一致したら穴を開ける
			if (number == Integer.parseInt(card[row][col])) {
				card[row][col] = "(" + card[row][col] + ")";
			}
		}
	}

	/**
	 * ビンゴカードの状態を表示する
	 * @param card ビンゴカード
	 */
	public static void printingCard(String card[][]) {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				System.out.print(BingoUtils.printingCenter(card[row][col]));
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * リーチ数とビンゴ数を算出し、表示する
	 * @param card ビンゴカード
	 */
	public static void printingReachAndBingo(String card[][]) {
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

		System.out.println("REACH:" + reachCount);
		System.out.println("BINGO:" + bingoCount);
		System.out.println("--------------------");
	}

}
