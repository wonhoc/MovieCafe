<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

[

	<c:forEach var="board" items="${requestScope.boards}" varStatus="status">
		{
			"no": ${boards.length - status.index},
			"userId": "${board.userId}",
			"title": "${board.title}",
			"boardWdate": "${board.boardWdate}",
			"hitCount": ${board.hitCount},
			"recomCount": ${board.recomCount},
			"commentCount": ${board.commentCount}
		}

		<c:if test="${fn:length(requestScope.boards) - status.index > 1}">
			,
		</c:if>
	</c:forEach>
]


