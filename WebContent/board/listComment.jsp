<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


 [
  	
  	<c:forEach var="comment" items="${requestScope.commentList}" varStatus="status">
		{
			"comNo": ${comment.comNo},
			"boardNo": ${comment.boardNo},
			"userId": "${comment.userId}",
			"comContent": "${comment.comContent}",
			"comWdate": "${comment.comWdate}"
		}
		
		<c:if test="${fn:length(requestScope.commentList) - status.index > 1}">
			,
		</c:if>
	</c:forEach>
	
 ]





