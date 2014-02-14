package coffee.dynamicProxy2.test;

public class PeopleImpl implements People {
	private String name;
	
	public PeopleImpl(String name){
		this.name = name;
	}

	@Override
	public String shit() {
		return "you are a pice of shit !" + name;
	}

	@Override
	public void fuck() {
		System.out.println("fuck you - " + name+ "  fucking off my house !!");
	}

	
}
