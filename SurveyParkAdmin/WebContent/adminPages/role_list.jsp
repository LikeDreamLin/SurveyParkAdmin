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
		<div class="locationDiv">显示所有角色</div>
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
				<s:form action="RoleAction_batchDelete" namespace="/Admin">
				<tr>
					<td>角色ID</td>
					<td>角色名称</td>
					<td>分配权限</td>
					<td></td>
				</tr>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="roleId"/>
						</td>
						<td>
							<input title="<s:property value="roleId"/>" class="roleName longInput" type="text" name="roleName" value="<s:property value="roleName"/>"/>
						</td>
						<td>
							<s:a namespace="/Admin" action="RoleAction_toAuthMngPage?roleId=%{roleId}">分配权限</s:a>
						</td>
						<td>
							<!-- 全部的id：1,2,3,4,5,6 -->
							<!-- 要删除的id：2,3,6 -->
							<!-- 在Action类中接收的方式：List<Integer> resIdList -->
							<input type="checkbox" name="roleIdList" value="<s:property value="roleId"/>" id="CheckBox<s:property value="roleId"/>"/>
							<label for="CheckBox<s:property value="roleId"/>">选择</label>
						</td>
					</tr>
				
				</s:iterator>
				
				<tr>
					<td colspan="4" align="center">
						<s:submit value="OK"></s:submit>
					</td>
				</tr>
				
				<tr>
					<td colspan="5" align="center">
						<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%>
						<%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
						<s:set value="'RoleAction_showRoles'" var="pageActionName"/>
						<s:set value="'/Admin'" var="pageNamespace"/>
						<s:include value="/resources_jsp/pageNavigator.jsp"/>
						<%-- <s:debug/> --%>
					</td>
				</tr>
				
				</s:form>
			</s:else>
		</table>
	</div>
	</s:push>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>