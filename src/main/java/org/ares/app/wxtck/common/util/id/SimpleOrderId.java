package org.ares.app.wxtck.common.util.id;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 仅够参考,未使用
 * @author rola
 *
 */
public class SimpleOrderId {

	/*
	 * 生成一个订单号
	 */
	public synchronized String getOrderId() {
		String datastr = getNowDateStr();
		if (datastr.equals(now)) {
			count++;// 自增
		} else {
			count = 1;
			now = datastr;
		}
		int countInteger = String.valueOf(total).length() - String.valueOf(count).length();// 算补位
		String bu = "";// 补字符串
		for (int i = 0; i < countInteger; i++) {
			bu += "0";
		}
		bu += String.valueOf(count);
		if (count >= total) {
			count = 0;
		}
		return datastr + bu;
	}

	String getNowDateStr() {
		return sdf.format(new Date());
	}

	private static int count = 0;// 全局自增数
	private static final int total = 9999;// 每毫秒秒最多生成多少订单(最好是像9999这种准备进位的值)
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static String now = null;// 记录上一次的时间,用来判断是否需要递增全局数
	
	public static void main(String[] args) {
		SimpleOrderId soi=new SimpleOrderId();
		System.out.println(soi.getOrderId());
	}

}
