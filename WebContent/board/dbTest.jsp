<%-- dbTest.jsp --%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="model.DBConn, java.sql.*" %>

<%

	Connection conn = DBConn.getConnection();
%>

Connection : <%=conn %> <br>
