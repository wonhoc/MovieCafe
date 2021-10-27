<%@ page contentType="text/html; charset=utf-8" %>
<%@ page session="false" %>
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
   <jsp:param name="headerTemplate" value="/header" />
   <jsp:param name="asideTemplate" value="/aside" />
   <jsp:param name="footerTemplate" value="/footer" />
   
   <jsp:param name="contentTemplate" value="<%=contentTemplate %>" />
</jsp:forward>