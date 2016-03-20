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
	
	<div>
		<s:form action="AdminAction_login" namespace="/Admin">
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">管理员登录</td>
				</tr>
				<s:if test="hasActionErrors()">
					<tr>
						<td colspan="2" align="center">
							<s:actionerror/>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>账号</td>
					<td>
						<s:textfield name="adminName" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td>密码</td>
					<td>
						<s:password name="adminPwd" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="登录"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>