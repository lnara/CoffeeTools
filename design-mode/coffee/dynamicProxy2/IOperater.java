package coffee.dynamicProxy2;

import java.lang.reflect.Method;

public interface IOperater {

	/**
	 * 执行 被 代理对象之前 执行的方法
	 * @param method
	 * @author coffee  2013-2-7  上午9:55:15
	 */
	void before(Method method);
	
	/**
	 * 执行 被 代理对象之后   执行的方法
	 * @param method
	 * @author coffee  2013-2-7  上午9:55:17
	 */
	void after(Method method);
}
