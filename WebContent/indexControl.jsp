<%@ page contentType="text/html; charset=utf-8" %>

<%
	String contentTemplate = null;
	try{
		contentTemplate = request.getParameter("contentTemplate");
		
		if(contentTemplate.equals(null)) {
			contentTemplate = "/main";
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
%>

<jsp:forward page="/template.jsp">	
	<jsp:param name="contentTemplate" value="<%=contentTemplate %>" />
</jsp:forward> 