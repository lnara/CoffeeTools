package coffee.dynamicProxy2.test;

import coffee.dynamicProxy2.BookFacadeProxy;
import coffee.dynamicProxy2.LoggerOperater;

public class TestPeople {

	public static void main(String[] args) {
		
		BookFacadeProxy proxy = new BookFacadeProxy();
		People bookProxy = (People) proxy.bind( new PeopleImpl("张三"),new LoggerOperater());
		System.out.println(bookProxy.shit());
	}
}
