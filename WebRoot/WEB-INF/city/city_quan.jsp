<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/jQuery/jquery-1.8.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css"></link>
<script type="text/javascript">
	function ceshi(){
		$(":text").each(function(){
			if($(this).val()>=1){
				alert("您必须输入小于1的小数")
			}
		})
	}
	
	function toupdate(){
		var count=0;
		$(":text").each(function(){
			 count+=parseInt($(this).val()*10);
		})
		var counte=count/10;
		if((counte==1)){
			$.ajax({
				type:"post",
				data:$("#myForm").serialize(),
				dataType:"json",
				url:"<%=request.getContextPath()%>/toupdate.action",
				success:function(data){
					if(data){
						alert("保存成功！")
					}else{
						alert("保存失败！")
					}
					location.href="<%=request.getContextPath()%>/getcity.action";
				}
				
			})
		}else{
			alert("权重总和为1")
		}
	}
</script>
<body>
<form id="myForm">
<table>
<tr><th colspan="2"><h1 style="font-size: xx-large;">权重设置</h1></th></tr>
<tr>
	<th>社会文明度</th>
	<th><input type="text" value="${plist.shehui}" name="shehui" onblur="ceshi()"></th>
</tr>
<tr>
	<th>经济富裕度</th>
	<th><input type="text" value="${plist.jingji}" name="jingji" onblur="ceshi()"></th>
</tr>
<tr>
	<th>环境优美度</th>
	<th><input type="text" value="${plist.huanjing}" name="huanjing" onblur="ceshi()"></th>
</tr>
<tr>
	<th>资源承载度</th>
	<th><input type="text" value="${plist.ziyuan}" name="ziyuan" onblur="ceshi()"></th>
</tr>
<tr>
	<th>生活便宜度</th>
	<th><input type="text" value="${plist.shenghuo}" name="shenghuo" onblur="ceshi()"></th>
</tr>
<tr>
	<th>公共安全度</th>
	<th><input type="text" value="${plist.gong}" name="gong" onblur="ceshi()"></th>
</tr>
<tr><th colspan="2">
<input type="button" value="保存设置" onclick="toupdate()">
</th></tr>
</table>
</form>
</body>
</html>