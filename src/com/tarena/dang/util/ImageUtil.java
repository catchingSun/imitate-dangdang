package com.tarena.dang.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class ImageUtil {
	private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W"
			, "X", "Y", "Z","a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"
			, "x", "y", "z"};
	private static final int SIZE = 5;
	private static final int LINES = 20;
	private static final int WIDTH = 200;
	private static final int HEIGHT = 100;
	private static final int FONT_SIZE = 50;
	
	public static Map<String,BufferedImage> 
                              createImage() {
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(
        WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.WHITE);
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		Random ran = new Random();
		//画随机字符
		for(int i=1;i<=SIZE;i++){
			
			int r = ran.nextInt(chars.length);
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null,Font.BOLD+Font.ITALIC,FONT_SIZE));
			graphic.drawString(chars[r],(i-1)*WIDTH/SIZE , HEIGHT/2);
			sb.append(chars[r]);//将字符保存，存入Session
		}
		//画干扰线
		for(int i=1;i<=LINES;i++){
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH),ran.nextInt(HEIGHT));
		}
		System.out.println(sb.toString());
		Map<String,BufferedImage> map = 
      new HashMap<String,BufferedImage>();
		map.put(sb.toString(), image);
		return map;
	}
  
  public static InputStream change(
                  BufferedImage image) throws Exception{
    ByteArrayOutputStream dest = 
          new ByteArrayOutputStream();
  //使用JPEGImageEncoder将图片压缩编码
    //将压缩编码后的字节信息写入dest对象
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(dest);
      encoder.encode(image);
      //将dest中的图片字节取出，封装成inputstream
      //给imageStream属性赋值
      byte[] bts = dest.toByteArray();
      InputStream imageStream = 
        new ByteArrayInputStream(bts);
      return imageStream;
  }
  
  
	
	public static Color getRandomColor(){
		Random ran = new Random();
		Color color = new Color(
        ran.nextInt(256),
        ran.nextInt(256),
        ran.nextInt(256));
		return color;
	}
}
