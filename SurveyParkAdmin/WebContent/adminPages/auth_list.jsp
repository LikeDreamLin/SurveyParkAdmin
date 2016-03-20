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
		
		$(".authorityName").change(function(){
			
			var authorityName = $.trim(this.value);
			
			if(authorityName == "") {
				this.value = this.defaultValue;
				return ;
			}
			
			var authorityId = this.title;
			
			var url = "${pageContext.request.contextPath }/Admin/AuthorityAction_update";
			var paramData = {"authorityId":authorityId,"authorityName":authorityName,"time":new Date()};
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
		<div class="locationDiv">显示所有权限</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.size() == 0">
				<tr>
					<td>现在还没有创建任何权限对象</td>
				</tr>
			</s:if>
			<s:else>
				<s:form action="AuthorityAction_batchDelete" namespace="/Admin">
				<tr>
					<td>权限ID</td>
					<td>权限名称</td>
					<td>分配资源</td>
					<td></td>
				</tr>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="authorityId"/>
						</td>
						<td>
							<input title="<s:property value="authorityId"/>" class="authorityName longInput" type="text" name="authorityName" value="<s:property value="authorityName"/>"/>
						</td>
						<td>
							<s:a namespace="/Admin" action="AuthorityAction_toResMngPage?authorityId=%{authorityId}">分配资源</s:a>
						</td>
						<td>
							<input type="checkbox" name="authIdList" value="<s:property value="authorityId"/>" id="CheckBox<s:property value="authorityId"/>"/>
							<label for="CheckBox<s:property value="authorityId"/>">选择</label>
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
						<s:set value="'AuthorityAction_showAuthorities'" var="pageActionName"/>
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