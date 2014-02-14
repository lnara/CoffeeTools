package coffee.dynamicProxy2;

public class BookFacadeImpl implements BookFacade {

	@Override
	public void addBook() {
		System.out.println("添加图书方法...");
	}

}
