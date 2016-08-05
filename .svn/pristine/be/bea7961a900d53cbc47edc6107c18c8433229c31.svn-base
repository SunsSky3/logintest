<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="mainStyle.css" rel="stylesheet" type="text/css">
</head>
<style>
	/*                 框架                      */
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
		background: url('img/bg.jpg') no-repeat;
	
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
		width: 453px;
		height:75px;
		margin-top: 25px;
		margin-left: 100px;
		background: url('img/title.png') no-repeat;
	}
	#content{
		height:560px;
		border:1px solid transparent;
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
	/*                 框架end                    */
	/*                 登录框                    */
	#login{
		width: 354px;
		height:291px;
		margin-top: 220px;
		margin-left: 550px;
		background: url('img/login.png') no-repeat;
	}
	#input{
		position: absolute;
		margin-top: 105px;
	}
	.input{
		  display: block;
		  height: 38px;
		  width: 85%;
		  margin-left: 36px;
		  margin-top: 12px;
		  font-size: 23px;
		  background: none;
		  border: 0;
	}
	div #buttonCont{
		position: absolute;
		margin-top: 226px;
		margin-left: 30px;
	}
	.button{
		width: 295px;
		height: 48px;
		color:#fff;
		font-family:隶书;
		font-size: 30px;
		border: none;
		border-radius: 5px;
	}
	.loginButton{
		background: #49D7D2
	}
	.loginButton:hover{
		background: #008792;
	}
	/*                 登录框end                    */
</style>

<body>
<div id='container'>
		<div id='header'>
			<div id='logo'></div>
			<div id="title"></div>
		</div>
		
		  <div id="content">

			<form id='login' class="login"  method="POST" name="frmLogin" action="LoginServlet">
				<div id="input">
					<input type="text" placeholder="用户名" class="input userNameInput" name="username" onfocus="if (this.value=='Your name') this.value='';"/>
					<input type="password" placeholder="密码" class="input passwordInput" name="password" onfocus="if (this.value=='Your password') this.value='';"/>
				</div>

				<div id='buttonCont'>
				<input type="submit" class="button loginButton" value="登录"/>
				</div>
			</form>

		  </div>

		<div id="bottomDivider"></div>
		<div id='bottom'>copyright blabla版权所有</div>
	</div>
	 <script language="javascript">
   function validateLogin(){
    var sUserName = document.frmLogin.username.value ;
    var sPassword = document.frmLogin.password.value ;
    if ((sUserName =="") || (sUserName=="Your name")){
     alert("请输入用户名！");
     return false ;
    }
    
    if ((sPassword =="") || (sPassword=="Your password")){
     alert("请输入密码！");
     return false ;
    }
   }
  </script>
</body>
</html>