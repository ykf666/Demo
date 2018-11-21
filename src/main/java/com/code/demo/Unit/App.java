package com.code.demo.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

	public int add(int a, int b) {
		return a + b;
	}

	public String getStr() {
		return "Hello World";
	}

	public List<String> getList() {
		List<String> list = new ArrayList<String>();
		list.add("yan");
		list.add("ke");
		list.add("fei");
		return list;
	}

	public Map<String, String> getMap(String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return map;
	}

}
