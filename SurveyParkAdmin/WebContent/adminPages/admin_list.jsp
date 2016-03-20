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
		
		$(".roleName").change(function(){
			
			var roleName = $.trim(this.value);
			
			if(roleName == "") {
				this.value = this.defaultValue;
				return ;
			}
			
			var roleId = this.title;
			
			var url = "${pageContext.request.contextPath }/Admin/RoleAction_update";
			var paramData = {"roleId":roleId,"roleName":roleName,"time":new Date()};
			var type = "json";
			var callBack = function(respData){
				
				alert(respData.message);
				
			};
			
			$.post(url,paramData,callBack,type);
			
		});
		
	});
	
</script>
</head>
<body>

	<s:include value="/resources_jsp/admin_top.jsp"/>
	
	<div class="block-div navigatorDiv">
		<div class="locationDiv">显示所有管理员账号</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.size() == 0">
				<tr>
					<td>现在还没有创建任何角色</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td>管理员ID</td>
					<td>账号名称</td>
					<td>分配角色</td>
				</tr>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="adminId"/>
						</td>
						<td>
							<s:property value="adminName"/>
						</td>
						<td>
							<s:a namespace="/Admin" action="AdminAction_toRoleMngPage?adminId=%{adminId}">分配角色</s:a>
						</td>
					</tr>
				
				</s:iterator>
				
				<tr>
					<td colspan="5" align="center">
						<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%>
						<%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
						<s:set value="'AdminAction_showAdmins'" var="pageActionName"/>
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