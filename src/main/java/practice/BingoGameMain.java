package main.java.practice;

import java.util.ArrayList;

/**
 * ビンゴゲームのメインクラス
 */
public class BingoGameMain {

	public static void main(String[] args) {

		// ビンゴボールの取り出し
		ArrayList<Integer> ballNumbers = BingoBall.generateBingoNumber();

		// ビンゴカードの作成
		String[][] bingoCard = BingoCard.generateBingoCard();
		
		int listNumber = 1;

		for (int number : ballNumbers) {

			// ビンゴボールの数字を表示する
			BingoBall.printingNumber(number, listNumber);

			// カードに一致する数字があるか確認して、あったら穴を開ける
			BingoCard.punchingCard(bingoCard, number);

			// カードの状態を表示する
			BingoCard.printingCard(bingoCard);

			// リーチ数とビンゴ数を表示する
			BingoCard.printingReachAndBingo(bingoCard);
			
			listNumber++;
		}
	}
}
