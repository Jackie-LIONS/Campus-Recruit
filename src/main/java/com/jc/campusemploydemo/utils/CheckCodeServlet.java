//package com.jc.campusemploydemo.utils;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Random;
//
///**
// * 验证码
// */
//@WebServlet(value = "/frontdesk/checkCode")
//public class CheckCodeServlet extends HttpServlet {
//	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
//		//服务器通知浏览器不要缓存
//		response.setHeader("pragma","no-cache");
//		response.setHeader("cache-control","no-cache");
//		response.setHeader("expires","0");
//
//		//在内存中创建一个长80，宽30的图片，默认黑色背景
//		//参数一：长
//		//参数二：宽
//		//参数三：颜色
//		int width = 80;
//		int height = 30;
//		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//
//		//获取画笔
//		Graphics g = image.getGraphics();
//		//设置画笔颜色为灰色
//		g.setColor(Color.GRAY);
//		//填充图片
//		g.fillRect(0,0, width,height);
//
//		//产生4个随机验证码，12Ey
//		String checkCode = getCheckCode();
//		//将验证码放入HttpSession中
//		request.getSession().setAttribute("checkCode",checkCode);
//
//		//设置画笔颜色为黄色
//		g.setColor(Color.YELLOW);
//		//设置字体的小大
//		g.setFont(new Font("黑体",Font.BOLD,24));
//		//向图片上写入验证码
//		g.drawString(checkCode,15,25);
//
//		//将内存中的图片输出到浏览器
//		//参数一：图片对象
//		//参数二：图片的格式，如PNG,JPG,GIF
//		//参数三：图片输出到哪里去
//		ImageIO.write(image,"PNG",response.getOutputStream());
//	}
//
//	/**
//	 * 产生4位随机字符串
//	 */
//	private String getCheckCode() {
//		String base = "0123456789ABCDEFGabcdefg";
//		int size = base.length();
//		Random r = new Random();
//		StringBuffer sb = new StringBuffer();
//		for(int i=1;i<=4;i++){
//			//产生0到size-1的随机值
//			int index = r.nextInt(size);
//			//在base字符串中获取下标为index的字符
//			char c = base.charAt(index);
//			//将c放入到StringBuffer中去
//			sb.append(c);
//		}
//		return sb.toString();
//	}
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.doGet(request,response);
//	}
//}