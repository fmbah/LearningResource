package java虚拟机.jdk8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class NPE {

	private static final ThreadLocal<DateFormat> date = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};


	public static void main(String[] args) throws Exception{

		User user = new User();
		user.setName("123");
		User user1 = new User();
		user1.setName("456");

		List list = new ArrayList<>();
		list.add("3");
		list.add("2");
		list.add("1");
//		ArrayList list1 = (ArrayList)list.subList(0, 1);
//		System.out.println(list1.size());

		String name = Optional.ofNullable(user).map(u -> u.getName()).map(u1 -> user1.getName()).orElseThrow(()->new Exception("123"));
		System.out.println(name);


		List<String> list2 = new ArrayList<>();
		list2.add("1");
		list2.add("12");
		list2.add("123");
		Object[] objects = list2.toArray();
		System.out.println(objects.length);
		String[] strings = new String[list2.size() - 1];
		strings = list2.toArray(strings);
		System.out.println(strings.length + "==" + list2.size());


		String[] strs = {"1", "2"};
		List<String> asList = Arrays.asList(strs);
		System.out.println(asList.size() + "==" + asList.get(0));
		strs[0] = "caonima";
		System.out.println(asList.size() + "==" + asList.get(0));


		for (String str : list2) {
			if ("123".equals(str)) {
				list2.remove(str);
			}
		}


		new HashMap(16);



	}


}
class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
