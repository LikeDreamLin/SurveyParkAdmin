<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>
<script type="text/javascript">
	
	$(function(){
		
		$("#goBack").click(function(){
			
			//通过浏览器历史记录实现后退操作
			window.history.back();
			
		});
		
	});
	
</script>
</head>
<body>
	
	<s:include value="/resources_jsp/top.jsp"/>
	
		<%-- <s:debug/> --%>
		
		<!-- 1.获取异常全类名 --><!-- exception.getClass().getName() -->
		<!-- 2.根据异常全类名读取国际化资源文件 -->
		<div class="textAlignCenter">
			<s:property value="getText(exception.class.name)"/>
			<br/>
			<s:if test="hasActionErrors()">
				<s:actionerror/>
			</s:if>
			<br/>
			<button id="goBack">返回上一个操作</button>
		</div>
		<%-- 错误的写法：<s:property value="text(exception.class.name)"/> --%>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>