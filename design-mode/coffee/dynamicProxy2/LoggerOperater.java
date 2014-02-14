package coffee.dynamicProxy2;

import java.lang.reflect.Method;

public class LoggerOperater implements IOperater {

	@Override
	public void before(Method method) {
		System.out.println("打印日志:方法前的操作 - " + method.getName()+" START.... ");
	}

	@Override
	public void after(Method method) {
		System.out.println("方法-" + method.getName()+ " 执行结束.");
	}

}
