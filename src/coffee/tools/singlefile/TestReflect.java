package coffee.tools.singlefile;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {
	
	public static <T> T createBean(Class<T> beanClass, Object... objects) {
		T temp = null;
		try {
			temp = beanClass.newInstance();
			Field[] fields = beanClass.getDeclaredFields();
			Method wM = null;
			PropertyDescriptor pd = null;
			for(int i=0;i<objects.length;i++){
				pd = new PropertyDescriptor(fields[i].getName(), beanClass);
				wM = pd.getWriteMethod();// 获得写方法
				
				//Class z = fields[i].getType();
				
				wM.invoke(temp, objects[i]);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return temp;
	}
}
