package coffee.dynamicProxy.test;



public class Tank implements Moveable {

	@Override
	public void move() {
		
		System.out.print("坦克前进中 ");
		for(int i=0;i<10;i++){
			
			System.out.print( ">");	
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void back() {
		System.out.print("\n坦克后退中 ");
		for(int i=0;i<10;i++){
			
			System.out.print( "<");	
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
