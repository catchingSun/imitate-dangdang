package com.tarena.dang.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tarena.dang.util.ImageUtil;

public class ImageServlet extends HttpServlet {
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		Map<String,BufferedImage> imageMap=ImageUtil.createImage();//1
		String code=imageMap.keySet().iterator().next();//2
		HttpSession session=request.getSession();
		session.setAttribute("imageCode",code);
		BufferedImage image=imageMap.get(code);//3
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		ImageIO.write(image,"jpeg",os);
		os.close();
	}
}



