package com.zhang.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhang.VerifyLogin.MD5Implementation;
import com.zhang.dao.MysqlAction;
import com.zhang.javabean.Booking;


public class FileUploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		///////////////////////////////////////////////////////////////////////////////////////////
		//说明：会前上传文件处理
		//修改人：孙少卡
		//时间：2016/9/6
		///////////////////////////////////////////////////////////////////////////////////////////	
		System.out.println("FileUploadServlet Begin!");
		request.setCharacterEncoding("UTF8");

		String conferName = null;
		HttpSession session =  request.getSession() ;
		MysqlAction mysqlaction = new MysqlAction();

		String uploadPath = "C:\\conference\\file\\"+"upload\\";

		File folder = new File(uploadPath);
		System.out.println("文件存放在服务器上的路径："+folder);
		//Servlet初始化时执行,如果上传文件目录不存在则自动创建  
		if(!folder.exists())
			folder.mkdirs();
		try {
			if(ServletFileUpload.isMultipartContent(request)){  //判断获取的是否是文件
				DiskFileItemFactory disk = new DiskFileItemFactory();
				disk.setSizeThreshold(20*1024);  //设置内存可取字节数
				disk.setRepository(disk.getRepository());  //设置临时文件目录
				ServletFileUpload up = new ServletFileUpload(disk);
				int maxsize = 20*1024*1024;
				List<FileItem> list = up.parseRequest(request);  //获取上传列表
				Iterator<FileItem> i = list.iterator();  //遍历类表的迭代器
				while(i.hasNext()){
					String fileName = "";
					double filesize =0;
					Date now = null;
					FileItem fm = (FileItem)i.next();  //遍历列表	
					filesize = fm.getSize();
					session.setAttribute("fileSize", filesize);
					if(!fm.isFormField()){
						String filePath = fm.getName();  //获取文件全路径名

						int startIndex = filePath.lastIndexOf("\\");
						if(startIndex!=-1){  //对文件名进行截取
							fileName = filePath.substring(startIndex+1);
							session.setAttribute("fileName", fileName);
						}else{
							fileName = filePath;
						}
						if(fm.getSize()>maxsize){
							break;
						}
						if((fileName==null)||(fileName.equals(""))&&(fm.getSize()==0)){
							break;
						}
						File saveFile = new File(uploadPath,fileName);
						
						/**  
						* @Description: 对重复文件名上传进行修改文件名
						* @Author: 汪志文（作者）
						* @Create Date: 2016-9-17（创建日期）
						*/
						int refile = 0;
						while(saveFile.exists()){
							refile++;
							String [] strings = fileName.split("\\.");
							int length = strings.length;

							String reg = ".*\\([0-9]\\)";
						    if(strings[length-2].matches(reg)){
						    	strings[length-2] = strings[length-2].substring(0,strings[length-2].length()-3);
						    }
							fileName = strings[length-2]+"("+refile+")"+"."+strings[length-1];
							saveFile=new File(uploadPath,fileName);
						}

						fm.write(saveFile);  //向文件中写入数据
						now = new Date();
						session.setAttribute("uploadtime", now);
						int conferId =  (Integer) session.getAttribute("conferId");
						String uploader = (String) session.getAttribute("userName");
						Booking newbooking = new Booking(); 
						try {
							newbooking= mysqlaction.getBookingbyconferId(conferId);
							int roomnum = newbooking.getRoomNum();
							conferName = newbooking.getConferName();
							mysqlaction.addFileInfo(fileName, uploader, now, roomnum, conferId, filesize);	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					///////////////////////////////////////////////////////////////////////////////////////////
					//说明：上传文件的会中处理
					//修改人：孙少卡
					//时间：2016/9/6
					///////////////////////////////////////////////////////////////////////////////////////////					
					// 1 获得不带扩展名的文件名和文件后缀
					String fileNameNoEx = "";
					String filetype = "";
					if ((fileName != null) && (fileName.length() > 0)) { 
						int dot = fileName.lastIndexOf('.'); 
						if ((dot >-1) && (dot < (fileName.length()))) { 
							//System.out.println("获得不带扩展名的文件名和文件后缀");
							fileNameNoEx =  fileName.substring(0, dot); 
							filetype = fileName.substring(dot+1,fileName.length());
						} 
					}
					

					// 对文件名加上当前时间进行hash处理
					MD5Implementation md5Implementation = new MD5Implementation();
					String fileHashName = md5Implementation.createPassPhrase(fileNameNoEx+now);

					//在red5服务器的upload/files下新建文件夹（名字为fileHashName），为后面把上传的文件重命名后生成pdf、swf等格式，生成xml文件作准备
					//String red5FilePath = "C:\\conference\\conference\\opm\\3.0.x\\dist\\red5\\webapps\\openmeetings\\upload\\files\\"+fileHashName+"\\";
					String red5FilePath = "E:\\red5\\webapps\\openmeetings\\upload\\files\\"+fileHashName+"\\";
					File newFile =new File(red5FilePath);
					if(!newFile.exists()){
						newFile.mkdirs();
					}
					

					// 复制文件
					FileInputStream fi = null;
					FileOutputStream fo = null;
					FileChannel in = null;
					FileChannel out = null;
					try {
						fi = new FileInputStream(folder+"\\"+fileName);
						fo = new FileOutputStream(newFile+"\\"+fileName);
						in = fi.getChannel();//得到对应的文件通道
						out = fo.getChannel();//得到对应的文件通道
						in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
					} catch (Exception e) {
						//System.out.println("复制文件时出错啦！");
						e.printStackTrace();
					}
					try {
						fi.close();
						in.close();
						fo.close();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					
					// 文件重命名
					File File1 = new File(red5FilePath+fileName);
					File FileOriginal = new File(red5FilePath+fileHashName+"."+filetype);
					if (File1.exists()) {		    	   
						File1.renameTo(FileOriginal);
						//System.out.println("转化后的文件名为："+FileOriginal.getName());
					} 		      


					// 生成pdf，swf，xml文件
					if (FileOriginal.exists()) { 
						//String openOfficePath = "C:\\conference\\conference\\OpenOffice 4\\";
						String openOfficePath = "C:\\Program Files (x86)\\OpenOffice 4";
						
						String sourceFile = red5FilePath + fileHashName + "." + filetype;	//会议中系统原始文件
						String destFile = red5FilePath + fileHashName + ".pdf";
						
						OfficeToPDFTools otp = new OfficeToPDFTools();
						otp.startService(openOfficePath, sourceFile, destFile);						
					}else {
						//System.out.println("找不到源文件，无法转化为pdf");
					}
					TimeUnit.MILLISECONDS.sleep(3000);	//休息3s

					
					//to swf
					if (new File(red5FilePath+fileHashName+".pdf").exists()) {
						Pdf2Swf.pdfToSwf(red5FilePath+fileHashName+".pdf", red5FilePath, fileHashName+".swf");
					} else {
						//System.out.println("找不到pdf文件，无法转化为swf");
					}					
					TimeUnit.MILLISECONDS.sleep(30000); //3s

					
					// xml
					File filePdf = new File(red5FilePath+fileHashName+".pdf"); 
					File fileSwf = new File(red5FilePath+fileHashName+".swf");

					String originalSize =Long.toString(FileOriginal.lastModified());
					String pdfSize = Long.toString(filePdf.lastModified());		        		
					String swfSize =  Long.toString(fileSwf.lastModified());	
					
					if (FileOriginal.exists()&&filePdf.exists()&&fileSwf.exists()) {	

						XmlGenerator xg = new XmlGenerator();			        
						xg.build(red5FilePath+"library.xml",red5FilePath+fileHashName, fileHashName+"."+filetype,fileHashName+".pdf" , fileHashName+".swf", Long.toString(FileOriginal.length()), Long.toString(filePdf.length()), Long.toString(fileSwf.length()), originalSize, pdfSize,swfSize);

						try {
							Date date = new Date();
							java.sql.Date sql_date = new java.sql.Date(date.getTime());
							int room_id = mysqlaction.getRoom_idbyconferName(conferName);
							mysqlaction.addFileInfo2Fileexploreritem(fileHashName, fileName, sql_date, room_id, sql_date);
						} catch (Exception e) {							
						}
					} else {
						//System.out.println("生成xml文件错误！");
					}       
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		response.sendRedirect("documentInfo.jsp");
	}  
}

