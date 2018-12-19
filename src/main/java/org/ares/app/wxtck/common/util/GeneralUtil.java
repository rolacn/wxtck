package org.ares.app.wxtck.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.ares.app.wxtck.common.exception.AppSysException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.binarywang.utils.qrcode.QrcodeUtils;

/**
 * @author Administrator
 * 常用工具类
 * 不使用static method
 */

@Component()
public class GeneralUtil {
	
	/**
	 * 返回一个单机版的订单id
	 * 规则:时间戳-openid-6位随机数
	 * @param flagid
	 * @return
	 */
	public String orderNum(String flagid) {
		String r=null;
		String r1=sdf.format(new Date());
		String r2=flagid;
		String r3=randomNum6();
		r=r1+"-"+r2+"-"+r3;
		return r;
	}
	
	public String randomNum4() {
		return randomNum(4);
	}
	
	public String randomNum6() {
		return randomNum(6);
	}
	
	/**
	 * 生成指定长度的随机数
	 * @param len
	 * @return
	 */
	public String randomNum(int len) {
		if(len<1)
			throw new AppSysException("length of random value is too short!");
		String s="0123456789";
		final int bound=10;
		int suffix=-1;
		StringBuilder t=new StringBuilder();
		for(int i=0;i<len;i++) {
			suffix=random.nextInt(bound);
			t.append(s.charAt(suffix));
		}
		return t.toString();
	}
	
	/**
	 * 判断字符串是否使用BCR方式加密
	 * @param plain
	 * @return
	 */
	public String buildBcrPass(String plain) {
		if(StringUtils.isEmpty(plain)||plain.startsWith("$2a$"))
			return plain;
		return bcrPasswordEncoder.encode(plain);
	}
	
	/**
	 * 判断明文是否符合Bcr密文
	 * @param plain
	 * @param cipher
	 * @return
	 */
	public boolean isMatchBcrPass(String plain,String cipher) {
		if(StringUtils.isEmpty(plain)||!cipher.startsWith("$2a$"))
			return false;
		return bcrPasswordEncoder.matches(plain, cipher);
	}
	
	/**
	 * @param content
	 * @return dafault 200*200
	 */
	public byte[] getScalaQrcode(String content) {
		return getScalaQrcode(content, 2);
	}
	
	/**
	 * 
	 * @param content
	 * @param multiple 放大倍数 maxsize=16
	 * size=100*multiple
	 * @return
	 */
	public byte[] getScalaQrcode(String content,int multiple) {
		if(multiple<0) multiple=2;
		multiple=multiple%16;
		return buildQrcode(content, MIN_QRCODE_SIZE*multiple, null);
	}
	
	public byte[] buildQrcode(String content) {
		return buildQrcode(content, DEFAULT_QRCODE_SIZE, null);
	}
	
	public byte[] buildQrcode(String content,int size) {
		return buildQrcode(content, size, null);
	}
	
	public byte[] buildQrcode(String content, File logoFile) {
		return buildQrcode(content, DEFAULT_QRCODE_SIZE, logoFile);
	}
	
	public byte[] buildQrcode(String content, int size, File logoFile) {
		return QrcodeUtils.createQrcode(content, size, logoFile);
	}
	
	Random random=new Random();
	PasswordEncoder bcrPasswordEncoder=new BCryptPasswordEncoder();
	
	static final int MIN_QRCODE_SIZE=100;
	static final int DEFAULT_QRCODE_SIZE=200;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static void main(String[] args) {
		GeneralUtil g=new GeneralUtil();
		System.out.println(g.buildBcrPass("ares@20196yhn^YHN"));
	}
}
