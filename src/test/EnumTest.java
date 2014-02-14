package test;

public class EnumTest {
	
	public static enum IccOpLogType{
		AA(1,"模板定义"),BB(2,"单据填写");	
		
		 // 成员变量 
		private int index;  
	    private String name;  
	    
	    // 构造方法  
	    private IccOpLogType(int index , String name) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    
	    
	    public String getName(){
	    	return this.name;
	    }
	}
	
	public static void main(String[] args) {
		
		System.out.println(IccOpLogType.AA.getName());
		
		
	}
}
