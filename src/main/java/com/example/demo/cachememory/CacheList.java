package com.example.demo.cachememory;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Game;

public class CacheList {
	public static List<Game> list = new ArrayList<>();
	
	public static void save(List<Game> games) {
		list.addAll(games);
	}
	
	public static  List<Game> get() {
		return list;
	}
	
}
