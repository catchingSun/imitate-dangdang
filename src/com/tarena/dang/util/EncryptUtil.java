package com.tarena.dang.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	public static String md5Encrypt(String s){
		if("".equals(s)||s==null){
			return "";
		}
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] bys=md.digest(s.getBytes());
			//System.out.println(Arrays.toString(bys));
			return Base64Encrypt(bys);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String Base64Encrypt(byte[] bys){
		BASE64Encoder base=new BASE64Encoder();
		String s=base.encode(bys);
		return s;
	}
	public static byte[] Base64Decrypt(String s)throws IOException{
		BASE64Decoder base=new BASE64Decoder();
		byte[] bys=base.decodeBuffer(s);
		return bys;
	}
	public static void main(String[] args) {
		String password="123456";
		String s=md5Encrypt(password);
//		System.out.println(s);
//		s = md5Encrypt("123");
//		System.out.println(s);
	}
}
