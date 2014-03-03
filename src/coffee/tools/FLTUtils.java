package coffee.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FLTUtils{
	
	// 样例 字符串的分割符
	public static final String SEPARATOR = "\\|";
	
	// example = "123-.-abc-.-456-.-defgh-.-7890";
	public List<String[]> getFLTText(File FLTTextFile, String example) {
		// 验证参数正确性
		validate(FLTTextFile, example);

		String[] tempArr = example.split(SEPARATOR);
		String[] point = new String[tempArr.length];

		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < point.length; i++) {
			startIndex = endIndex + 1;
			endIndex += tempArr[i].length();
			point[i] = startIndex + "," + endIndex;
		}
		return getFLTText(FLTTextFile, point);
	}

	public List<String[]> getFLTText(File FLTTextFile,String[] point){
		// 验证参数正确性
		validate(FLTTextFile,point); 
		// 读取文件内容 每一行为一个 String
		List<String> lineStringList = CoffeeFileTools.readFileByLine(FLTTextFile);
		// 返回结果 
		List<String[]> result = new ArrayList<String[]>();
		// 开始位置
		int startIndex = 0;
		// 结束位置
		int endIndex = 0;
		for(String lineString : lineStringList){
			String[] bean = new String[point.length]; 
			for(int i = 0;i<point.length;i++){
				startIndex = Integer.valueOf(point[i].split(",")[0]);
				endIndex = Integer.valueOf(point[i].split(",")[1]);
				bean[i] = lineString.substring(startIndex-1,endIndex);
			}
			result.add(bean);
		}
		return result;
	}
	
	
	/* 验证部分,验证参数是否正确   ----  STAET*/
	private void validate(File FLTTextFile,String[] point){
		System.out.println("~~~~ 暂不验证");
	}
	private void validate(File FLTTextFile,String example){
		System.out.println("~~~~ 暂不验证");
	}
	/* 验证部分,验证参数是否正确   ----  END  */
	
	
	
	
	 ///////////////////////////////////////////////////
	///   测试 一下 ///////////////////////////////////////
	public static void main(String[] args) {
		FLTUtils t = new FLTUtils();
		
		List<String[]> list = new ArrayList<String[]>();
		
//		list = t.getFLTText(new File("D:/test.txt"),
//				new String[] { "1,3", 
//						"4,6",   
//						"7,9", "10,14", "15,18" }); 

		list = t.getFLTText(new File("C:/Users/Administrator/Desktop/S24_AUTH20120329-auth表测试数据.txt"), 
				"472427|00|6304|6226370000090219   |20130315|16463273|000000024214|0450000001     |156|00063040450|3001156D0000003222943002156C000001852294                    |                                                            |                         |0000|000000000000|0000000100000|+|0000000100000|000| | |20130314|12311680|          |                    |20120329|  |            |苏宁易购                                |C|77777777|       |00000000|156|3|   |00063040000|295426|00|1505|00063040000|156| | |00000000|000000|                          |6013|RL/MCG-O N/0 RL/MCG-R N/0                                   |0210|00 被批准-交易被接受                                        |000|   |0200|000000000000|000007    |00|00|0|2|00000000|30|00|00|0041|0000000000|1|0000000000000000|000|20120329|P|      |0315185010|024214|00000000|         000000|MBKIO     |    |                    |");
		
		for(String[] sArr : list){
			for(String sStr : sArr){
				System.out.println(sStr+"#");
			}
		}
		
	}
}
