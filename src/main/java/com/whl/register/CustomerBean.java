package com.whl.register;

import java.util.ArrayList;

public class CustomerBean {
	public void getSomeData() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		for (String string : list) {
			System.out.println(string);
		}
	}
}
