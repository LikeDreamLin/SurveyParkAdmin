<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//request.getRequestDispatcher("/Guest/SurveyAction_top10").forward(request, response);
	response.sendRedirect(request.getContextPath()+"/Guest/SurveyAction_top10");
%>