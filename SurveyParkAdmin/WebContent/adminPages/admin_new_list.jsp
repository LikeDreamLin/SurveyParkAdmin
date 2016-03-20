<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>
</head>
<body>

	<s:include value="/resources_jsp/admin_top.jsp"/>
	
	<div class="block-div navigatorDiv">
		<div class="locationDiv">新生成的20个管理员账号</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable">
			<tr>
				<td>账号</td>
				<td>密码</td>
			</tr>
			<s:iterator value="#request.newAdminList">
				<tr>
					<td>
						<s:property value="adminName"/>
					</td>
					<td>
						<s:property value="adminPwd"/>
					</td>
				</tr>
			
			</s:iterator>
		</table>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>