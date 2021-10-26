
<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	


	<c:forEach var="chkOk" items="${requestScope.ok }" varStatus="i">
	{
		"ok": "${chkOk}"
	
	}
	</c:forEach>
	
	
	
  	<%--
  	<c:set var='chkOk' value='${requestScope.ok }'/>
  	{
  	"ok" : "${chkOk}"
  	}
  	
  	--%>
  	
  <%--	<c:set var="chkOk" value="${requestScope.ok }" scope="page"/>
  	--%>
 
 