package com.whl.util.controller;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class DateConvert implements Converter<String, Date> {

	private static final Logger log = LoggerFactory.getLogger(DateConvert.class);

	@Override
	public Date convert(String source) {
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTime dateTime = DateTime.parse(source, dateTimeFormatter);
			log.debug(dateTime.toString());
			return dateTime.toDate();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
