package com.lyf.test;

import java.text.DateFormat;
import java.util.Date;

public class DateMachine {
	public String getDate() {
		System.out.println("[DateMachine] Somebody is calling me!");
		Date now = new Date();
		return DateFormat.getInstance().format(now);
	}
}
