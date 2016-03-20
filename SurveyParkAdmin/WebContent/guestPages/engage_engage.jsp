<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.atguigu.com/survey" prefix="atguigu" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>
</head>
<body>
	
	<s:include value="/resources_jsp/top.jsp"/>
	
	<!-- 将Survey对从Session域中取出，放到栈顶 -->
	<s:push value="#session.currentSurvey">
	<div class="block-div navigatorDiv">
		<div class="locationDiv">
			<s:if test="isLogoExists(logoPath)">
				<img src="<s:url value="%{logoPath}"/>"/>
			</s:if>
			<s:else>
				<img src="<s:url value="/resources_static/logo.gif"/>">
			</s:else>
			
			<s:property value="title"/>
		</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable">
			<!-- 将currentBag压入栈顶 -->
			<s:push value="currentBag">
				<s:form namespace="/Guest" action="EngageAction_doEngage">
					<s:hidden name="bagId" value="%{bagId}"/>
					<s:hidden name="surveyId" value="%{surveyId}"/>
					<tr>
						<td>包裹名称：<span style="color:red"><s:property value="bagName"/></span></td>
					</tr>
					<tr>
						<td>
							<table class="dashedTable">
								<s:iterator value="questions">
									<%-- 遍历问题集合 --%>
									<%-- 栈顶对象：Question --%>
									<%-- 一.Question的基本信息 --%>
									<tr>
										<td><s:property value="questionName"/></td>
									</tr>
									<%-- 二.Question的详细信息 --%>
									<tr>
										<td>
											<atguigu:generateQuestion currentBagIdOGNL="bagId"/>
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center">
							<s:if test="bagOrder > minOrder">
								<s:submit name="submit_prev" value="返回上一个包裹"/>
							</s:if>
							<s:if test="bagOrder < maxOrder">
								<s:submit name="submit_next" value="进入下一个包裹"/>
							</s:if>
							<s:if test="bagOrder == maxOrder">
								<s:submit name="submit_done" value="完成"/>
							</s:if>
							<s:submit name="submit_quit" value="放弃"/>
						</td>
					</tr>
				</s:form>
			</s:push>
		</table>
	</div>
	</s:push>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>