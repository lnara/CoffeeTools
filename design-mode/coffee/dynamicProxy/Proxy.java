package coffee.dynamicProxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import coffee.dynamicProxy.test.Moveable;

/**
 * 生成代理主题角色的类
 * 
 * http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html
 * http://weibo.proxyaaa.com/sina.com.php?u=8C0nvUMcVSD5OkY%2B20WTY4O6RiEsj4niNOgYJVJqmiqwjts28aLZEpSxR%2FivKV5Z6jlLYMM9ojU8MH7lGg%3D%3D&b=3
 * http://www.blogjava.net/DoubleJ/archive/2008/03/04/183796.html
 * http://blog.csdn.net/wsh622827/article/details/4708329
 */
public class Proxy {

	public static <T>T newProxyInstance(Class<T> interFace,InvocationHandler h) throws Exception {

		// 调用私有方法,返回 .java 的文件内容 
		String javaFileString = getJAVAString(interFace);
		// 创建目录
		MakeFileUtil.createFile("D:/coffee/dynamicProxy");
		// 生成java文件
		String fileName = "D:/coffee/dynamicProxy/$Proxy.java";
		File file = new File(fileName);
		FileWriter fWriter = new FileWriter(file);
		fWriter.write(javaFileString);
		fWriter.flush();
		fWriter.close();

		// 生成class文件,jdk6提供的工具类 ,注意,需要把JDK/lib/tools.jar复制到 jre/lib下,否则报空指针
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(fileName);
		
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, units);
		task.call();
		fileManager.close();

		// 装载到内存,生成新对象
		URLClassLoader loader = new URLClassLoader(new URL[] { new URL("file:/" + "D:/") });
		
		Class<?> c = loader.loadClass("coffee.dynamicProxy.$Proxy");

		// 通过有参的构造器反射生成代理类的实例
		Constructor<?> ctr = c.getConstructor(InvocationHandler.class);

		@SuppressWarnings("unchecked")
		T obj = (T) ctr.newInstance(h);

		return obj;
	}

	private static <T> String getJAVAString(Class<T> interFace) {
		// 行分隔符 
		String br = System.getProperty("line.separator");
		// 方法字符串
		StringBuffer methodString = new StringBuffer();
		// pagkage,import,构造方法等
		StringBuffer othersString = new StringBuffer();
		// 接口的所有方法
		Method[] methods = interFace.getMethods();

		// 生成方法 字符串
		for (Method m : methods) {
			methodString.append("   @Override");
			methodString.append(br);
			methodString.append("   public void ");
			methodString.append(m.getName());
			methodString.append("() {");
			methodString.append(br);
			methodString.append("       try {");
			methodString.append(br);
			methodString.append("           Method md =");
			methodString.append(interFace.getName());
			methodString.append(".class.getMethod(\"");
			methodString.append(m.getName());
			methodString.append("\");");
			methodString.append(br);
			methodString.append("           h.invoke(this,md);");
			methodString.append(br);
			methodString.append("       }catch (Exception e){ ");
			methodString.append(br);
			methodString.append("           e.printStackTrace();");
			methodString.append(br);
			methodString.append("       }");
			methodString.append(br);
			methodString.append("   }");
			methodString.append(br);
		}
		// 生成包,导包,构造方法 + 方法字符串
		othersString.append("package coffee.dynamicProxy;" + br);
		othersString.append("import java.lang.reflect.Method;" + br);
		othersString.append("public class $Proxy implements "
				+ interFace.getName() + "{" + br);
		othersString
				.append("   private coffee.dynamicProxy.InvocationHandler h;"
						+ br);
		othersString.append("   public $Proxy(InvocationHandler h) {" + br);
		othersString.append("       super();" + br);
		othersString.append("       this.h = h;" + br);
		othersString.append("   }" + br);
		othersString.append(methodString.toString() + br);
		othersString.append("}" + br);

		return othersString.toString();
	}

	public static void main(String[] args) throws Exception {
		Proxy.newProxyInstance(Moveable.class, null);

	}
	
	
	
}
