<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/jQuery/jquery-1.8.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css"></link>
<script type="text/javascript">
	function quanzhi(){
		location.href="${pageContext.request.contextPath}/quanzhi.action";
	}
	function xiugai(){
		location.href="${pageContext.request.contextPath}/xiugai.action?cname="+$(":radio[name='cname']:checked").val();
	}
	function xiazai(){
		location.href="${pageContext.request.contextPath}/xiazai.action"
	}
	function zhu(){
		location.href="${pageContext.request.contextPath}/zhu.action"
	}
</script>
<body>
<table>
<tr><th colspan="10"><h1 style="font-size:xx-large;color: green;">用户${user.username}<input type="button" onclick="zhu()" value="注销"></h1></th></tr>
</table>
<table>
<tr><th colspan="10"><h1 style="font-size: xx-large;">中国十大宜居城市管理</h1></th></tr>
<tr><th colspan="10">
<form action="${pageContext.request.contextPath}/getcity.action" method="post">
	<select name="name" style="background-color:inherit;">
		<option value="shehui">社会文明度</option>
		<option value="jingji">经济富裕度</option>
		<option value="huanjing">环境优美度</option>
		<option value="ziyuan">资源承载度</option>
		<option value="shenghuo">生活便宜度</option>
		<option value="gong">公共安全度</option>
	</select>
	<input type="submit" value="按单项条件排名">
</form>
<input type="button" value="权重设置" onclick="quanzhi()">
<input type="button" value="修改城市得分" onclick="xiugai()">
</th></tr>
<tr>
	<th></th>
	<th>排名</th>
	<th>城市</th>
	<th>社会文明度(权重${plist.shehui})</th>
	<th>经济富裕度(权重${plist.jingji})</th>
	<th>环境优美度(权重${plist.huanjing})</th>
	<th>资源承载度(权重${plist.ziyuan})</th>
	<th>生活便宜度(权重${plist.shenghuo})</th>
	<th>公共安全度(权重${plist.gong})</th>
	<th>总分</th>
</tr>
<c:forEach items="${list}" var="ll" varStatus="p">
<tr>
	<th><input type="radio" name='cname' value="${ll.cname}"></th>
	<th>${p.count}</th>
	<th>${ll.cname}</th>
	<th>${ll.shehui}</th>
	<th>${ll.jingji}</th>
	<th>${ll.huanjing}</th>
	<th>${ll.ziyuan}</th>
	<th>${ll.shenghuo}</th>
	<th>${ll.gong}</th>
	<th><fmt:formatNumber value="${ll.zong}"></fmt:formatNumber></th>
</tr>
</c:forEach>
<tr><th colspan="10">
<form action="${pageContext.request.contextPath}/unload.action" method="post" enctype="multipart/form-data">
	file1:<input type="file" name="user_file"/>
	<br>
	file2:<input type="file" name="user_file"/>
	<br>
	<input type="submit" value="上传">
	<input type="button" value="下载" onclick="xiazai()">
</form>
</th>
</tr>
</table>
</body>
</html>