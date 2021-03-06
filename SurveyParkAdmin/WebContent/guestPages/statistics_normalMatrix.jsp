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
		<div class="locationDiv">
			<s:property value="#request.question.questionName"/>
		</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable">
		
			<tr>
				<td></td>
				<td>显示图表</td>
			</tr>
			
			<s:iterator value="#request.question.matrixRowTitlesArray" status="myStatus">
				
				<tr>
					<td>
						<s:property/>
					</td>
					<td>
						<img src="<s:url namespace="/Guest" action="StatisticsAction_showNormalMatrixChart?questionId=%{#request.question.questionId}&rowNumber=%{#myStatus.index}"/>">
					</td>
				</tr>
				
			</s:iterator>
		
		</table>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>