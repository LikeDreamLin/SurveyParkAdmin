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
	
	<s:include value="/resources_jsp/top.jsp"/>
	<div class="block-div navigatorDiv">
		<div class="locationDiv">我发起的调查</div>
	</div>
	
	<%-- 将Page对象临时压入栈顶 --%>
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
				<tr>
					<td>您现在还没有未完成的调查，赶快去创建吧！</td>
				</tr>
			</s:if>
			<s:else>
			<tr>
				<td>调查标题</td>
				<td>操作</td>
			</tr>
			<%-- iterator标签执行时，栈顶对象是：Page对象，所以list属性可以直接使用 --%>
			<%-- 遍历过程中栈顶是Survey对象 --%>
			<s:iterator value="list">
			<tr>
				<td>
					<s:property value="title"/>
				</td>
				<td>
					<%-- 在选择目标调查的过程中，排除包裹来源的那个调查 --%>
					<s:if test="surveyId == #parameters.surveyId[0]">
						当前调查
					</s:if>
					<s:else>
						<s:a namespace="/Guest" action="BagAction_moveToThisSurvey?surveyId=%{surveyId}&bagId=%{bagId}">移动到这个调查</s:a>
						<s:a namespace="/Guest" action="BagAction_copyToThisSurvey?surveyId=%{surveyId}&bagId=%{bagId}">复制到这个调查</s:a>
					</s:else>
				</td>
			</tr>
			</s:iterator>
			</s:else>
			
			<tr>
				<td colspan="5" align="center">
					<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%>
					<%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
					<s:set value="'BagAction_toMoveCopyPage'" var="pageActionName"/>
					<s:set value="'/Guest'" var="pageNamespace"/>
					<%-- 为了保持bagId和surveyId，需要附着附加的请求参数，即：给extraParams赋值 --%>
					<%-- &bagId=5&surveyId=6 --%>
					<%-- s:set标签的value属性是自动OGNL解析的，所以不解析的部分，加上单引号，多个部分之间使用+连接 --%>
					<s:set value="'&bagId='+#parameters.bagId[0]+'&surveyId='+#parameters.surveyId[0]" var="extraParams"></s:set>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
					<%-- <s:debug/> --%>
				</td>
			</tr>
			
		</table>
	</div>
	</s:push>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>