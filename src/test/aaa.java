package test;

public class aaa {

	/**
	 * Description : 打几个字会屎啊 \r\n 
	 * @param args
	 * @Author coffee
	 * @Create 2014-2-19
	 */

	public static void main(String[] args) {
		String[] a1 = new String[] {"0","1","2","3","4","5","6","7","8","9"};
		String[] a2 = new String[] {"0","1","2","3","4","5","6","7","8","9"};
		
		String[] b1 = new String[] {"a","b","c","d","e","f"};
		String[] b2 = new String[] {"a","b","c","d","e","f"};
		
		for(String aa : a1){
			for(String aaa : a2){
				for(String bb : b1){
					for(String bbb : b2){
						System.out.print(aa+aaa+bb+bbb + "  ");
					}
				}
			}
		}
		System.out.println();
		for(String aa : a1){
			for(String aaa : b1){
				for(String bb : a2){
					for(String bbb : b2){
						System.out.print(aa+aaa+bb+bbb + "  ");
					}
				}
			}
		}
		System.out.println();
		for(String aa : a1){
			for(String aaa : b1){
				for(String bb : b2){
					for(String bbb : a2){
						System.out.print(aa+aaa+bb+bbb + "  ");
					}
				}
			}
		}
		
		System.out.println();
		for(String aa : b1){
			for(String aaa : b2){
				for(String bb : a1){
					for(String bbb : a2){
						System.out.print(aa+aaa+bb+bbb + "  ");
					}
				}
			}
		}
		System.out.println();
		for(String aa : b1){
			for(String aaa : a1){
				for(String bb : b2){
					for(String bbb : a2){
						System.out.print(aa+aaa+bb+bbb + "  ");
					}
				}
			}
		}
		
		System.out.println();
		for(String aa : b1){
			for(String aaa : a1){
				for(String bb : a2){
					for(String bbb : b2){
						System.out.print(aa+aaa+bb+bbb + "  ");
					}
				}
			}
		}
		
		System.out.println();
	}

}
