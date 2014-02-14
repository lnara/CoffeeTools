package coffee.dynamicProxy2;

public class TestProxy {

	/**
	 * @param args
	 * @author coffee 2013-2-6 下午5:07:01
	 */
	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl(),new LoggerOperater());
		bookProxy.addBook();
		
	}

}
