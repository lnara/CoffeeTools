package test;

import java.util.LinkedList;
import java.util.Queue;

class A {
	static{ System.out.println(" A 的静态块"); }
	{ System.out.println(" A 的 非 静态块"); }
	public A(){ System.out.println(" A 的构造方法"); }
	public static void Astatic(){ System.out.println(" A 的静态方法"); }
}

class B extends A{
	static{ System.out.println(" B 的静态块"); }
	{ System.out.println(" B 的 非 静态块"); }
	public B(){ System.out.println(" B 的构造方法"); }
	public static void Astatic(){ System.out.println(" B 的静态方法"); }
}

public class T {
	public static void main(String[] args) {
		System.out.println("````````` A ab = new B();```````````````");
		A ab = new B();
		System.out.println("````````` B b = new B();```````````````");
		B b = new B();
		System.out.println("````````` A a = new A();```````````````");
		A a = new A();
		
		String id="1001010";
		String name = "name";
		
		String reStr = "<tr class='budgetFormTR' onclick='rightBudgetFormSelected('"
			+id
			+"'); id='"
			+id
			+"'><td style='padding-left:5px'>"
			+name
			+"</td>";
		System.out.println(reStr);
		
		
		int n = 53;
		String s = "";
		while (n > 0) {
			int m = n % 26;
			if (m == 0)
				m = 26;
			s = (char) (m + 64) + s;
			n = (n - m) / 26;
		}
		System.out.println(s);
	}
}