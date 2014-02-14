package coffee.strategy;

public class Context {
	Strategy stragegy ;
	
	public Context(String m) {
		if ("A".equals(m)) {
			stragegy = new FactStrategyA();
		} else if ("B".equals(m)) {
			stragegy = new FactStrategyB();
		} else if ("C".equals(m)) {
			stragegy = new FactStrategyC();
		} else if ("D".equals(m)) {
			stragegy = new FactStrategyD();
		}
	}

	public void contextInterFace(String param){
		stragegy.method(param);
	}

}
