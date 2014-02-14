package coffee.dynamicProxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理代理类 
 */
public class BookFacadeProxy implements InvocationHandler {
	
	private Object target;
	private IOperater proxy;
	
    /** 
     * 绑定委托对象并返回一个代理类 
     * @param target 
     * @return 
     */  
    public Object bind(Object target,IOperater operater) {  
        this.target = target;  
        this.proxy = operater;
        
        //取得代理对象  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)  
    }  

    /** 调用方法  */  
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
        Object result=null;  

        Method before = this.proxy.getClass().getDeclaredMethod("before", new Class[]{Method.class});
        before.invoke(this.proxy, method);

        //执行方法  
        result=method.invoke(target, args);  

        Method after = this.proxy.getClass().getDeclaredMethod("after", new Class[]{Method.class});
        after.invoke(this.proxy, method);

        return result;  
    }  
	

}
