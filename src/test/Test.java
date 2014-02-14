package test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import coffee.tools.CoffeeFileTools;

public class Test {
	
	
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
				
				Class z = fields[i].getType();
				
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
	

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, IntrospectionException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

//	
//			Class cls = Class.forName("test.MyBean");
//			Field fieldlist[] = cls.getDeclaredFields();
//			for (int i = 0; i < fieldlist.length; i++) {
//				Field fld = fieldlist[i];
//				System.out.println("name = " + fld.getName());
//				System.out.println("decl class = " + fld.getDeclaringClass());
//				System.out.println("type = " + fld.getType());
//				int mod = fld.getModifiers();
//				System.out.println("modifiers = " + Modifier.toString(mod) + "  " + mod);
//				System.out.println("-----");
//			}
//			MyBean bean = (MyBean) cls.newInstance();
//			PropertyDescriptor pd = new PropertyDescriptor("field1", cls);
//			Method wM = pd.getWriteMethod();// 获得写方法
//			wM.invoke(bean, "fu");// 因为知道是int类型的属性,所以转换成integer就是了。。也可以不转换直接打印
//			System.out.println("~~~~~~~~~~~~~~~ " + bean.getField1());
		
//		MyBean b = TestImage.createBean(MyBean.class, "fu",2,"324","768");
//		System.out.println(b.getField1());
//		System.out.println(b.getField2());
//		System.out.println(b.getField3());
//		System.out.println(b.getField4());
		
		File root = new File("E:\\afterZip");
		String zipRoot = "D:\\afterDelete\\";
		
		if(!(new File(zipRoot).exists())){
			CoffeeFileTools.createFolder(zipRoot);
		}
		
		File[] folders = root.listFiles();
		
		for(File folder : folders){
			File[] images = folder.listFiles();
			List<File> temp = new ArrayList<File>();
			List<File> temp2 = new ArrayList<File>();
			
			for(File f : images)
				temp.add(f);
			
			int size = temp.size();
			int a = size/10;
			if(a > 0)
				for(int i = 0;i<size;i = i + a){
					temp2.add(temp.get(i));
					if(temp2.size()>=10)
						break;
				}
				
			else 
				temp2 = temp;
			
			String folderName = folder.getName();
			folderName = folderName.replace("[Be]", "");
			folderName = folderName.replace("[Beautyleg]", "");
			folderName = folderName.replace("[Beautyleg8.com]", "");
			String nee = zipRoot + folderName;
			File newFolder = new File(nee);
			CoffeeFileTools.createFolder(newFolder);
			
			for(File f : temp2){
				CoffeeFileTools.copyFile(f.getAbsolutePath(), nee + "\\" + f.getName());
			}
		}
	}
}
