package singlefile;

import javax.swing.JOptionPane;

/**
 * 弹出一个框 输入 文字
 * 
 */
public class OptionMessage {

	public static void main(String[] args) {

		String userstr = JOptionPane.showInputDialog(null, "请输入一个数字或字母");
		String mypattern = "[0-9a-zA-Z]{1}";
		while (!userstr.matches(mypattern)) {
			JOptionPane.showConfirmDialog(null, "请重新输入", "提示",
					JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
			userstr = JOptionPane.showInputDialog(null, "请出入数字", "");
		}
		char userstrchar[] = userstr.toCharArray();
		System.out.println((int) userstrchar[0]);
	}
}
