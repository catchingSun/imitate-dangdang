<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="<%=basePath%>/css/login.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#imageCode').click(function(){
					$('#imgVcode').attr('src','<%=basePath%>/image?dt='+new Date().getTime());
					return false;
				});
				$('#imgVcode').click(function(){
					$('#imageCode').click();
				});
			});
			
			//表单项是否通过检测
			var flag={
				"email":false,
				"nickname":false,
				"password":false,
				"repassword":false,
				"code":false
			};
			//点击进入输入框先将旁边的信息置空
			$(function(){
				$('#txtEmail').click(function(){
					$("#email\\.info").html("");
					flag.email=false;
				});
				$('#txtNickName').click(function(){
					$("#name\\.info").html("");
					flag.nickname=false;
				});
				$('#txtPassword').click(function(){
					$("#password\\.info").html("");
					$("#password1\\.info").html("");
					flag.password=false;
					flag.repassword=false;
				});
				$('#txtRepeatPass').click(function(){
					$("#password1\\.info").html("");
					flag.repassword=false;
				});
				$("#txtVerifyCode").click(function(){
					$("#number\\.info").html("");
					flag.code=false;
				});
			});
			//除掉字符串两端的空格
			function strip(str){
				var reg=/(^\s*)|(\s*$)/g;
				return str.replace(reg,'');
			}
			//验证码检测
			$(function(){
				$("#txtVerifyCode").blur(function(){
					flag.code=false;//设置成为通过检测
					var code=strip($("#txtVerifyCode").val());
					 //非空检测
					 if(code==""){
					 	$("#number\\.info").html("<img src='../images/wrong.gif'/>验证码不能为空!");
					 	return;
					 }
					 //ajax检测
					 $("#number\\.info").html("<img src='../images/window_loading.gif'/>正在检测...");
					 $.post(
					 	"<%=basePath%>/valid.do",
					 	{"code":code},
					 	function(data){
					 		data=data.evalJSON();
					 		if(data){
					 			$("#number\\.info").html("<img src='../images/right.gif'/>验证码正确!");
					 			flag.code=true;
					 		}else{
					 			$("#number\\.info").html("<img src='../images/wrong.gif'/>验证码错误!");
					 		}
					 	}
					 );
				});
			});
			//邮箱检测
			$(function(){
				$('#txtEmail').blur(function(){
					flag.email=false;
					var email=strip($('#txtEmail').val());
					 //非空检测
					if(email==""){
						$("#email\\.info").html("<img src='../images/wrong.gif'/>邮箱不能为空!");
						return;
					}
					//邮箱格式检查
					var pattern=/\b(^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/;
					if(!pattern.test(email)){
						$("#email\\.info").html("<img src='../images/wrong.gif'/>邮箱格式错误!");
						return;
					}
					//ajax检测
					$("#email\\.info").html("<img src='../images/window_loading.gif'/>正在检测...");
					$.post(
						"<%=basePath%>/validEmail.do",
						{"email":email},
						function(data){
							data=data.evalJSON();
							if(data){
								$("#email\\.info").html("<img src='../images/right.gif'/>邮箱可以使用!");
					 			flag.email=true;
							}else{
								$("#email\\.info").html("<img src='../images/wrong.gif'/>邮箱已经被注册!");
							}
						}
					); 
				});
			});
			//昵称验证
			$(function(){
				$('#txtNickName').blur(function(){
					flag.nickname=false;
					var nickname=strip($('#txtNickName').val());
					if(nickname==""){
						$("#name\\.info").html("<img src='../images/wrong.gif'/>昵称不能为空!");
						return true;
					}
					var pattern=/^([\u4e00-\u9fa5]*[a-z0-9]*)*$/;//小写英文字母,中文,数字的正则表达式
					//var pattern=/^([a-z\d]|[\u4e00-\u9fa5]){4,20}$/;
					var singlechar=/[\u4e00-\u9fa5]/g;//单个中文的正则表达式
					if(pattern.test(nickname)){
						var temp=nickname.replace(singlechar,"xx");
						if(temp.length<4||temp.length>20){
							$("#name\\.info").html("<img src='../images/wrong.gif'/>昵称长度非法!");
						}else{
							flag.nickname=true;
							$("#name\\.info").html("<img src='../images/right.gif'/>可以使用!");
						}
					}else{
						$("#name\\.info").html("<img src='../images/wrong.gif'/>昵称字符非法!");
					}
				});
			});
			//密码验证
			$(function(){
				$('#txtPassword').blur(function(){
					flag.password=false;
					var password=strip($('#txtPassword').val());
					 //非空检测
					if(password==""){
						$("#password\\.info").html("<img src='../images/wrong.gif'/>密码不能为空!");
						return;
					}
					if(password.length<6||password.length>20){
						$("#password\\.info").html("<img src='../images/wrong.gif'/>长度非法!");
						return;
					}
					var pattern=/^[0-9a-zA-Z]+$/;
					if(pattern.test(password)){
						$("#password\\.info").html("<img src='../images/right.gif'/>可以使用!");
						flag.password=true;
					}else{
						$("#password\\.info").html("<img src='../images/wrong.gif'/>包含非法字符!");
					}
				});
			});
			//再次输入密码验证
			$(function(){
				$('#txtRepeatPass').blur(function(){
					flag.repassword=false;
					var password=strip($('#txtPassword').val());
					var repassword=strip($('#txtRepeatPass').val());
					if(flag.password){
						if(password==repassword){
							$("#password1\\.info").html("<img src='../images/right.gif'/>");
							flag.repassword=true;
						}else{
							$("#password1\\.info").html("<img src='../images/wrong.gif'/>两次密码不一致!");
						}
					}
					
				});
			});
			//表单提交处理
			$(function(){
				$('#f').submit(function(){
					if(flag.email&&flag.nickname&&flag.password&&flag.repassword&&flag.code){
						return true;
					}else{
						alert("请检测表单,未通过检查!");						
						$("#email\\.info").html("");
						$("#name\\.info").html("");
						$("#password\\.info").html("");
						$("#password1\\.info").html("");
						$("#number\\.info").html("");
						return false;
					}
				});
			});
			
			
		</script>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> > 2.验证邮箱 > 3.注册成功
		</div>
		<div class="fill_message">
			<form name="ctl00" method="post" action="<%=basePath%>/user/regist.do" id="f">
				<h2>
					以下均为必填项
				</h2>
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<input name="email" type="text" id="txtEmail" class="text_input"/>
							<div class="text_left" id="emailValidMsg">
								<p>
									请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。
								</p>
									<span id="email.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置您在当当网的昵称：
						</td>
						<td>
							<input name="nickname" type="text" id="txtNickName" class="text_input" />
							<div class="text_left" id="nickNameValidMsg">
								<p>
									您的昵称可以由小写英文字母、中文、数字组成，
								</p>
								<p>
									长度4－20个字符，一个汉字为两个字符。
								</p>
								<span id="name.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<input name="password" type="password" id="txtPassword"
								class="text_input" />
							<div class="text_left" id="passwordValidMsg">
								<p>
									您的密码可以由大小写英文字母、数字组成，长度6－20位。
								</p>
								<span id="password.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password1" type="password" id="txtRepeatPass"
								class="text_input"/>
							<div class="text_left" id="repeatPassValidMsg">
							<span id="password1.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" id='imgVcode' src="<%=basePath%>/image" />
							<input name="number" type="text" id="txtVerifyCode"
								class="yzm_input"/>
							<div class="text_left t1">
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
									<a id="imageCode" href="#">看不清楚？换个图片</a>
								</p>
								<span id="number.info" style="color:red"></span>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<input id="btnClientRegister" class="button_1" name="submit"  type="submit" value="注 册"/>
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

