package com.zhang.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//System.out.println("This is FileDownloadServlet !");
		response.setCharacterEncoding("UTF-8");
		String path=request.getParameter("path");	
		path=new String(path.getBytes("ISO-8859-1"));
		File file=new File(path);
		InputStream in=new FileInputStream(file);
		OutputStream os=response.getOutputStream();
		
		response.addHeader("Content-Disposition", "attachment;filename="+new String(file.getName().getBytes("gbk"),"iso-8859-1"));
		response.addHeader("Content-length", file.length()+"");
		response.setContentType("application/octet-stream");
		int data = 0;
		while((data=in.read())!=-1){
			os.write(data);
		}
		os.close();
		in.close();		
		}
}
