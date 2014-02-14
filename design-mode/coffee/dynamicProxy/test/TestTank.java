package coffee.dynamicProxy.test;

import coffee.dynamicProxy.Proxy;

public class TestTank {

	public static void main(String[] args) throws Exception {
		// 直接执行
		Moveable t = new Tank();
		t.move();
		t.back();
		System.out.println(System.getProperty("line.separator"));
		
		// 调用代理执行,添加 运行时间功能 
		Moveable moveable = Proxy.newProxyInstance(Moveable.class, new TimeInvocationHandler(t));
		moveable.move();
		moveable.back();
		
	}
}
