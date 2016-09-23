<%@page import="com.zhang.mrbs.utils.sortClass"%>
<%@page import="com.zhang.mrbs.dao.IFindFileByIdDao"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.zhang.javabean.Booking"%>
<%@page import="com.zhang.javabean.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="com.zhang.booking.*"%>
<%@page import="com.zhang.booking.GetRoomInfoServlet"%>
<%@page import="com.zhang.dao.MysqlAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>会议历史检索系统</title>
	<script type="text/javascript" src="jquery-2.1.1.min.js"></script>
  	<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<style>
/*           整体框架                 */
	*{
  		margin:0;
  		padding: 0;
  	}
	body{
		background: url('img/mainbg1.png') repeat-x;

	}
	#container{
		height:760px;
		width:1000px;
		margin: 10px auto;
		background: url('img/bg1.jpg') no-repeat;
	}
	#header{
		height:134px;
		background: url('img/header.jpg') no-repeat;
	}
	#logo{
		float: left;
		width: 100px;
		height: 100px;
		margin-top: 10px;
		margin-left:40px;
		background: url('img/logo.png') no-repeat;
	}
	#title{
		float: left;
		width: 520px;
		height:75px;
		margin-top: 25px;
		margin-left: 100px;
		background: url('img/title1.png') no-repeat;
	}
	#content{
		height:560px;
		border:1px solid transparent;
		background: url('img/content.png') repeat;
	}
	#bottomDivider{
		height: 5px;
		background: url('img/bottomDivider.jpg') no-repeat;
	}
	#bottom{
		height:55px;
		text-align: center;
		padding-top: 10px;
		background: url('img/bottom.jpg') no-repeat;
	}
	/*           整体框架  end               */

	/*                搜索区                 */
	.searchContainer{
	
		padding-top: 27px;
		background: url('img/searchContainerBg.png') no-repeat;
	}
	.searchContainer table{
		margin: auto;
        width: 95%;
        height: 98%;
        font-size: 20px;
	}
	.searchContainer table td{
		padding-left: 10px;
		letter-spacing: 0px;
	}
	.searchContainer table .txt{
		height: 40px;
		text-align: center;
		font-size: 28px;
		border: 1px solid #000;
	}
	.searchContainer table .txt1{
		width: 446px;
	}
	.searchContainer table .txt2{
		width: 210px;
	}
	.searchContainer table .txt3{
		width: 185px;
	}
	.searchContainer table .txt4{
		width: 185px;
	}
	.searchContainer table .txt5{
		width: 446px;
	}
	.searchContainer table .documentSearchBtn{
		background: #0a69a8;
	    background: -webkit-linear-gradient(top, #0a69a8, #7aabcc);
	    background: -moz-linear-gradient(top, #0a69a8, #7aabcc);
	    background: -ms-linear-gradient(top, #0a69a8, #7aabcc);
	    background: -o-linear-gradient(top, #0a69a8, #7aabcc);
	    padding: 16.5px 33px;
	    margin-left: 80px;
	    border:none;
	    -webkit-border-radius: 40px;
	    -moz-border-radius: 40px;
	    border-radius: 40px;
	    text-shadow: rgba(0,0,0,.4) 0 1px 0;
	    color: white;
	    font-size: 25px;
	    font-weight: bold;
	    font-family: 'Lucida Grande', Helvetica, Arial, Sans-Serif;
	    text-decoration: none;
	    vertical-align: middle;
	}
	.searchContainer table .documentSearchBtn:hover{
   		background: #2b7cb5;
	}
	.searchContainer table .documentSearchBtn:active{
  		background: #28a0f0;		
	}
	/*                搜索区 end                */

	/*tableTitle       */
	.tableTitle{ 
		height: 45px;
		background: url('img/tableTitleBg.png') repeat-y;
	}
	.tableTitle table{
		margin: auto;
        width: 95%;
        height: 100%;
        border-collapse:collapse;
        border-width: 1px;
        font-size: 22px;
		text-align: center;
	}
	.tableTitle tr{
		border-width: 1px;
        border:1px solid #006f86;  
	}
	.tableTitle td{
		border-width: 1px;
        border:1px solid #006f86; 		
	}
	.tableTitle table td:nth-child(1){
		width: 249px;
	}
	.tableTitle table td:nth-child(2){
		width: 245px;
	}
    .tableTitle table td:nth-child(3){
    	width: 319px;
    }
    .tableTitle table td:nth-child(4){
      width: 75px;
    }
	/*         tableTilte end                  */

	/*                文件表区                 */
	.documentTableCont{
		height: 320px;
		padding-top:6px; 
		background: url('img/documentTableConBg.png')no-repeat;
	}
	.documentTableCont1{
		height: 250px;
		width: 960px;
		height: 97%;
		margin:auto;
		overflow: auto;
	}
	table.documentTable{
        margin: auto;
        width: 99%;
        border-collapse:collapse;
        border-width: 1px;
        font-size: 20px;
    }
    .timeShow{
        margin: auto;
        width: 99%;
        border-collapse:collapse;
        border-width: 1px;
        font-size: 15px;
    }
    table.documentTable tr{
        border-width: 1px;
        border:1px solid #006f86;  
        }
    table.documentTable td{
        border-width: 1px;
        border:1px solid #006f86 ;
        }
    table.documentTable tr:nth-child(2n+1){
        background: #ddd;
        } 
    table.documentTable tr:hover{
    	background: #fbf8e9;
    }   
    table.documentTable tbody td:nth-child(1){
     width:250px;
     }  
    table.documentTable tbody td:nth-child(2){
     width: 245px;
    }
    table.documentTable tbody td:nth-child(3){
     width: 320px;
    }
    table.documentTable tbody td:nth-child(4){
      width: 75px;
    }

    #docuDownBtn{
    	width: 50px;
    	height: 20px;
    	margin-left: 10px;
    	border: none;
    	background: url('img/docuDownBtn.png') no-repeat;
    }
    #docuDownBtn:hover{
		background: url('img/docuDownBtn1.png') no-repeat;
    } 
    .btnDisabled{ 
   		border: 2px solid #aaa !important;                /*按钮禁用*/
   		background:#d8d8d8 !important ;
  }
    
    /*                文件表区end                 */  
</style>
<body>
	<div id='container'>
		<div id='header'>
			<div id='logo'></div>
			<div id="title"></div>
			<div id="links">
				<a onclick="window.open('choosetofutureorhistory.jsp','_parent')" alt="首页">首页</a>
                <a onclick="window.open('reservationInfo.jsp','_parent')" alt="会议室预定">会议室预定</a>
                <a onclick="window.open('documentSearch0.jsp','_parent')" alt="会议历史检索">会议历史检索</a>
			</div>
		</div>
		<%
			String conferNameSearch = session.getAttribute("conferNameSearch").toString();
			String conferThemeSearch = session.getAttribute("conferThemeSearch").toString();
			String roomNumSearch = session.getAttribute("roomNumSearch").toString();
			String startSearch = session.getAttribute("startSearch").toString();
			String endSearch = session.getAttribute("endSearch").toString();
		%>
		  <div id="content">
			<div class="searchContainer" >
			 <form method="post" action="HistoryServlet">
				<table>
					<tr>
						<td colspan="2">
							<span>输入搜索条件：</span>
						</td>      
					</tr>
					<tr>
						<td>   
							 <label for="keyWordTxt">会议名称：</label>
							 <input type="text" name = "confername" class="txt txt1" id="keyWordTxt" value="<%=conferNameSearch%>"/>   
						</td>
						<td>
							<label for="uploaderTxt">会议主题：</label>
							<input type="text" name = "confertheme" class="txt txt2" id="uploaderTxt" value="<%=conferThemeSearch%>"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="fromTxt">会议时间：&nbsp;从</label>
							<input type="text" name = "start" class="txt txt3" id="fromTxt" placeholder="选择日期" value="<%=startSearch%>" onclick="WdatePicker({el:'fromTxt'})"/>
							<label for="toTxt">&nbsp;到&nbsp;</label>
							<input type="text" name = "end" class="txt txt4" id="toTxt" placeholder="选择日期" value="<%=endSearch%>" onclick="WdatePicker({el:'toTxt'})"/>
						</td>
						<td rowspan="2">
							<input type="submit" class="documentSearchBtn" value="搜索一下" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="roomChoose">会 议 室 :&nbsp;&nbsp; </label>
							<select class="roomChoose txt txt5" name = "roomnum" id="roomChoose">
						         <option value ="">--------------请选择--------------------------</option>
				                 <%
				                   MysqlAction mysqlAction2 = new MysqlAction();
				                   String meetRoomSel = mysqlAction2.getAllRooms();
				                   String[] meetRoomSel1 = meetRoomSel.split(",");
				                   if(meetRoomSel1!=null){
				                       for(int i=0;i<meetRoomSel1.length;i++){
				                    	   if(meetRoomSel1[i].equals(roomNumSearch) ){
				                  %>
				                    		   <option value = "<%=meetRoomSel1[i]%>" selected="selected"><%="会议室编号："+meetRoomSel1[i]%></option>
				                    	   <%}else{ %>
				                       		   <option value = "<%=meetRoomSel1[i]%>"><%="会议室编号："+meetRoomSel1[i]%></option>
				                  <%
				                    	   }
				                   	   }
				                   }
				                  %> 
							</select>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<div class="tableTitle">
				<table>
					<tr>
						<td>会议名称</td>
						<td>会议主题</td>
						<td>会议日期</td>
						<td>会议室</td>
						<td>详情</td>
					</tr>
				</table>
			</div>
			<div class="documentTableCont">
				<div class="documentTableCont1">
				
				  <table class="documentTable">
						<%
							ArrayList list  = (ArrayList)request.getAttribute("list"); 
							//System.out.println(list);
							 MysqlAction mysqlAction = new MysqlAction();
							if (!list.equals("")&&!list.equals("null")){   
							
							/**  
							* @Description: 按照时间的顺序对文件进行排序。
							* @Author: 汪志文（作者）
							* @Create Date: 2016-9-23（创建日期）
							*/         	  
		                 	  sortClass c = new sortClass();
		                 	  Collections.sort(list,c);	                 
		                 	  for(int i = list.size()-1;i>=0;i--){						
		   		                Booking booking = (Booking)list.get(i);
		   		              	String [] timeStringArray =booking.getTime().split(",");
		   		             	int[] timeIntArray= new int[timeStringArray.length];
		   		               	for(int j=0;j<timeStringArray.length;j++){
			   		              	 timeIntArray[j] = Integer.parseInt(timeStringArray[j]);
		   		              	}  
		   		              	Arrays.sort(timeIntArray);
	                    %>  
	                   <form action="GetRoomInfoServlet" method="post">
						<tr>
							<td><%= booking.getConferName()%></td>
							<td><%= booking.getConferTheme()%></td>
							<td><%= booking.getDate()%><br/>
							<%
							 for(int jj =0;jj<timeIntArray.length;jj++){
		   		              	String timeString = mysqlAction.getTimeslotsById(timeIntArray[jj]);
		   		 			%><span class="timeShow">&lt;<%=timeString%>&gt;</span><%}%>
	
							 </td>
							<td><%= booking.getRoomNum() %></td>			
							<td> 
								   <input type="submit"  value="详情"/>
								   <input type="hidden"  name="conferId" value="<%=booking.getBookingNum()%>"/> 
							</td>
						</tr>
						</form>
					<% }%>
				   <% }%>
				</table>
			
				</div>
			</div>
		  </div>
		  
	 
		  
		  

	<script>
		//下载按钮 禁用
		var oDocuDownBtn=$('#docuDownBtn');
		var isDocuDownBtnDis=false;            ////后台判断
		BtnDisabled(oDocuDownBtn,isDocuDownBtnDis);
		//下载按钮 禁用end
		function BtnDisabled(elem,isBtnDisabled){    
			if(isBtnDisabled){
				elem.attr("disabled", "disabled")//按钮禁用
				elem.addClass("btnDisabled");
			}
			else{
				elem.removeAttr("disabled");//解禁
			}	
		};
	</script>	
		<div id="bottomDivider"></div>
		<div id='bottom'>copyright blabla版权所有</div>
	</div>
	
	
</body>
</html>