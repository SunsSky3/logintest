<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="javax.print.DocFlavor.STRING"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<%
	//response.setCharacterEncoding("UTF-8");
	String path=request.getParameter("path");	
	System.out.println("path:"+path);
	path=new String(path.getBytes("iso8859-1"),"GBK");
	System.out.println("path2:"+path);
	File file=new File(path);
    InputStream in=new FileInputStream(file);
    OutputStream os=response.getOutputStream();
	
	response.addHeader("Content-Disposition", "attachment;filename="+new String(file.getName().getBytes("GBK"),"iso8859-1"));
	
	response.addHeader("Content-length", file.length()+"");
	response.setContentType("application/octet-stream");
	int data = 0;
	while((data=in.read())!=-1){
		os.write(data);
	}
	os.close();
	in.close();
%>