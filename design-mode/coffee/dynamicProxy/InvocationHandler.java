package coffee.dynamicProxy;

import java.lang.reflect.Method;

/**  代理对象的操作接口   */
public interface InvocationHandler {

	void invoke(Object o,Method m);
	
}
