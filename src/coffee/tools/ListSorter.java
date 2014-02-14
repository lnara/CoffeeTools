//package coffee.tools;
//
//import java.text.Collator;
//import java.text.ParseException;
//import java.text.RuleBasedCollator;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Locale;
//
///**
// * 
// * 对List&ltbean&gt 按照bean 的属性排序, <br>
// * 例 List 中存放 User Bean, 则 ListSorter.sort(userList,"userName","userType");
// * 
// * @author coffee
// * 
// */
//@SuppressWarnings("unchecked")
//public class ListSorter {
//
//	// 排序 规则 -- 自定义
//	static final String number = "<1<2<3<4<5<6<7<8<9<10<11<12<13<14<15<16<17<18<19<20<21<22<23<24<25<26<27<28<29<30"
//			+ "<31<32<33<34<35<36<37<38<39<40<41<42<43<44<45<46<47<48<49<50<51<52<53<54<55<56<57<58<59<60"
//			+ "<61<62<63<64<65<66<67<68<69<70<71<72<73<74<75<76<77<78<79<80<81<82<83<84<85<86<87<88<89<90"
//			+ "<91<92<93<94<95<96<97<98<99<100";
//	static final String chineseNum = "<一<二<三<四<五<六<七<八<九<十<十一<十二<十三<十四<十五"
//			+ "<十六<十七<十八<十九<二十<二十一<二十二<二十三<二十四<二十五<二十六<二十七<二十八<二十九<三十";
//
//	public static <V> void sort(List<V> list, final String... properties) {
//
//		RuleBasedCollator collator1 = (RuleBasedCollator) Collator.getInstance(Locale.CHINA);
//		RuleBasedCollator collator2 = (RuleBasedCollator) Collator.getInstance(Locale.US);
//
//		Comparator comparator = null;
//		try {
//			comparator = new RuleBasedCollator(collator1.getRules() + collator2.getRules() + number + chineseNum);
//		} catch (ParseException e) {
//			comparator = (RuleBasedCollator) Collator.getInstance(Locale.CHINA);
//			e.printStackTrace();
//		}
//		;
//
//		ListComparator comp = new ListComparator<V>(comparator, properties);
//
//		Collections.sort(list, comp);
//	}
//}
//
//@SuppressWarnings("unchecked")
//class ListComparator<T> implements Comparator<T> {
//
//	private Comparator comparator;
//	private String[] properties;
//
//	public int compare(T o1, T o2) {
//		if (o1 == null && o2 == null)
//			return 0;
//		if (o1 == null)
//			return -1;
//		if (o2 == null)
//			return 1;
//		for (String property : properties) {
//			Comparator c = new BeanComparator(property, comparator);
//			int result = c.compare(o1, o2);
//			if (result != 0) {
//				return result;
//			}
//		}
//		return 0;
//	}
//
//	public ListComparator(Comparator comparator, String[] properties) {
//		super();
//		this.comparator = comparator;
//		this.properties = properties;
//	}
//}