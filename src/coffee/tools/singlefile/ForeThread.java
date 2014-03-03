package coffee.tools.singlefile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ForeThread {
	
	public static int j = 0;
	
	public synchronized static void  add(){ 
		j++; 
		System.out.println(Thread.currentThread().getName()+" add j=" + j);
	}

	public synchronized static void sub() {
		j--;
		System.out.println(Thread.currentThread().getName()+" sub j=" + j);
	}
	
	public static ExecutorService getThreadPool() {
		return  Executors.newFixedThreadPool(10);
	}
	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService es = getThreadPool();
		MyThread t1 = new MyThread("add"); // +
		MyThread t2 = new MyThread("add"); // +
		MyThread t3 = new MyThread("sub"); // -
		MyThread t4 = new MyThread("sub"); // -
		
		es.execute(t1);
		es.execute(t2);
		es.execute(t3);
		es.execute(t4);
		
		Thread.sleep(5000);
		System.out.println("最后 J = " + j);
	}
}

class MyThread implements Runnable{
	private String type;
	
	public MyThread(String type){
		this.type = type;
	}
	
	public void run() {
		for(int i=0;i<3;i++) {
			if("add".equals(type)){
				ForeThread.add(); // +
			} else {
				ForeThread.sub(); // -
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("异常");
				e.printStackTrace();
			}
		}
		
	}

}


