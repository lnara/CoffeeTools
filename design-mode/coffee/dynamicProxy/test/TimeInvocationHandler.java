package coffee.dynamicProxy.test;

import java.lang.reflect.Method;

import coffee.dynamicProxy.InvocationHandler;


public class TimeInvocationHandler implements InvocationHandler {  
    private Object target;  
    public TimeInvocationHandler(Object target) {  
        super();  
        this.target = target;  
    }  

	@Override
	public void invoke(Object o, Method m) {
		 long time1 = System.currentTimeMillis();  
	        System.out.println("\n开始时间="+time1);  
	     try {  
	            m.invoke(target);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        long time2 = System.currentTimeMillis();  
	        System.out.println("\n结束时间="+time2);  
	        System.out.println("共用时 :"+(time2-time1));  
	}  

}  