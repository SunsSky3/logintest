package com.zhang.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class OfficeToPDFTools {

	public void startService(String openOfficePath, String sourceFile, String destFile) throws InterruptedException{
		 
		String OpenOffice_HOME = openOfficePath; //这里是OpenOffice的安装目录
		
		// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
		if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
			OpenOffice_HOME += "\\";
		}
		
		// 启动OpenOffice的服务
		String command = OpenOffice_HOME
				//+ "program\\soffice.exe -headless -accept=\"socket,host=10.0.25.15,port=8100;urp;\"--nofirststartwizard";
				+ "program\\soffice.exe -headless -accept=\"socket,host=localhost,port=8100;urp;\"";
		Process pro =null;
		try {			
			pro = Runtime.getRuntime().exec(command);			
			TimeUnit.MILLISECONDS.sleep(10000);	//休息10s，确保OpenOffice已启动
			
			File inputFile = new File(sourceFile);
			// 如果目标路径不存在, 则新建该路径
			File outputFile = new File(destFile);
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			
			// connect to an OpenOffice.org instance running on port 8100
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(
			//		"10.0.25.15", 8100);
					"127.0.0.1", 8100);
			connection.connect();

			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);

			// close the connection
			connection.disconnect();
			
			// 封闭OpenOffice服务的进程  
            pro.destroy(); 
		} catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            pro.destroy();  
        } 
	}	
}
