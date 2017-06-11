package com.whl.test.joda;

import java.util.Date;

import org.joda.time.DateTime;

public class TimeTest {
	public static void main(String[] args) {
		DateTime now = DateTime.now();
		System.out.println(now);
		System.out.println(new Date().after(new Date()));
		System.out.println(now.isAfterNow());
		System.out.println(now.toString("yyyy-MM-dd"));
		String timeString = "2017-06-10";
		DateTime dateTime = DateTime.parse(timeString);
		System.out.println(dateTime);
	}
}
