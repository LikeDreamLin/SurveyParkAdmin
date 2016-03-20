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
		<div class="locationDiv">显示所有日志</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.size() == 0">
				<tr>
					<td>现在还没有日志信息</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td>LOGID</td>
					<td>操作人</td>
					<td>操作时间</td>
					<td>方法类型</td>
					<td>方法名称</td>
					<td>方法参数</td>
					<td>方法返回值</td>
					<td>方法结果</td>
				</tr>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="logId"/>
						</td>
						<td>
							<s:property value="operator"/>
						</td>
						<td>
							<s:property value="operateTime"/>
						</td>
						<td>
							<s:property value="methodType"/>
						</td>
						<td>
							<s:property value="methodName"/>
						</td>
						<td>
							<s:property value="methodArgs"/>
						</td>
						<td>
							<s:property value="methodReturnValue"/>
						</td>
						<td>
							<s:property value="methodResultMsg"/>
						</td>
					</tr>
				
				</s:iterator>
				
				<tr>
					<td colspan="8" align="center">
						<s:submit value="OK"></s:submit>
					</td>
				</tr>
				
				<tr>
					<td colspan="8" align="center">
						<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%>
						<%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
						<s:set value="'LogAction_showLogs'" var="pageActionName"/>
						<s:set value="'/Admin'" var="pageNamespace"/>
						<s:include value="/resources_jsp/pageNavigator.jsp"/>
						<%-- <s:debug/> --%>
					</td>
				</tr>
				
			</s:else>
		</table>
	</div>
	</s:push>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>