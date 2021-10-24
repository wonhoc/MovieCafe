<%--sendMsgForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
 <head>
        <meta charset='UTF-8'>
        <title>쪽지함</title>
    </head>
    <body>
        <div id="navibox" style="margin-left: 40px; margin-bottom: 20px">
            <button type="button" id="sendFormBtn" onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
            <button type="button" id="recieveMsglistBtn" onclick="location='${pageContext.request.contextPath }/sendMsgList.do'">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn">받은쪽지함</button>
        </div>
        <div id="content">
         	<table border="1">
         		<thead>
         			<tr>
         				<td>수신자</td>
         				<td>
         				<%--수신자 --%>
         				<c:forEach var="addr" items="${requestScope.sendMsg.address }" varStatus="i">
         				<%--메일 조회여부 --%>
         				<c:forEach var="isRead" items="${requestScope.sendMsg.isRead }" varStatus="i">
         				${addr }
         				<%--메일 조회여부 --%>
         				<c:if test="${isRead == 0}">
         					 안읽음 <br>
         				</c:if>
         				<c:if test="${isRead != 0}">
         					 읽음 <br>
         				</c:if>
         				</c:forEach>
         				</c:forEach>
         				</td>
         				<td>작성시간</td>
         				<td>${requestScope.sendMsg.msgWdate }</td>
         			</tr>
         		</thead>
         		<tbody>
         			<tr>
         				<td colspan="4" style="text-align: center;">내용</td>
         			</tr>
         			<tr>
         				<td colspan="4" style="text-align: center;">${requestScope.sendMsg.sendMsgContent }</td>
         			</tr>
         		</tbody>
         	</table>
		            
		     
        </div>


    </body>
</html>