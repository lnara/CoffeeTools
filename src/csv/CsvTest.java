package csv;

import java.util.List;

import csv.analysis.CSVReader;

public class CsvTest {
	public static void main(String[] args) throws Exception {
		String basepath = new CsvTest().getClass().getResource("").toString();
		basepath = basepath.replace("file:/", "").split("classes")[0]
				+ "classes/";
		CSVReader reader = new CSVReader(basepath + "123.csv");
		List<String[]> list = reader.readAll();
		for (String[] strings : list) {
			for (String str : strings) {
				System.out.print(str + "  ");
			}
			System.out.println();
		}
	}
}
