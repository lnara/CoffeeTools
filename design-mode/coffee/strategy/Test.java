package coffee.strategy;

import java.io.IOException;

public class Test {
	
	
	public static void main(String[] args) throws IOException {
		Context c = new Context("B");
		
		c.contextInterFace("第三个参数");
		
	}
}


class FactStrategyA extends Strategy{
	@Override
	public void method(String param) {
		System.out.println("第一个实际的策略方法执行: " + param);
	}
}
class FactStrategyB extends Strategy{
	@Override
	public void method(String param) {
		System.out.println("第二个实际的策略方法执行: " + param);
	}
}
class FactStrategyC extends Strategy{
	@Override
	public void method(String param) {
		System.out.println("第三个实际的策略方法执行: " + param);
	}
}
class FactStrategyD extends Strategy{
	@Override
	public void method(String param) {
		System.out.println("第四个实际的策略方法执行: " + param);
	}
}