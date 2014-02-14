package singlefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 获取 键盘的输入 的两个方法
 */
public class CatchKeyBoardInput {

	public static String getKeyBoardInput1() {
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		return s;
	}

	public static String getKeyBoardInput2() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line == null ? "" : line;
	}
	
	
	public static void main(String[] args) {
		System.out.println("1:" + CatchKeyBoardInput.getKeyBoardInput1());
		System.out.println("2:" + CatchKeyBoardInput.getKeyBoardInput2());
	}
}
