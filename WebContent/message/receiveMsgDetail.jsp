<%-- receiveMsgDetail.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
 <head>
        <meta charset='UTF-8'>
        <title>받은 쪽지 상세보기</title>
    </head>
    <body>
        <div id="navibox" style="margin-left: 40px; margin-bottom: 20px">
            <button type="button" id="sendFormBtn" onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
            <button type="button" id="sendMsglistBtn" onclick="location='${pageContext.request.contextPath }/sendMsgList.do'">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn" onclick="location='${pageContext.request.contextPath }/receiveMsgList.do'">받은쪽지함</button>
        </div>
        <div id="content">
         	<table border="1">
         		<thead>
         			<tr>
         				<td>보낸 사람</td>
         				<td>        		       			        	
         				${requestScope.receiveMsg.writerId }
         				</td>
         				<td>작성시간</td>
         				<td>${requestScope.receiveMsg.msgWdate }</td>
         			</tr>
         		</thead>
         		<tbody>
         			<tr>
         				<td colspan="4" style="text-align: center;">내용</td>
         			</tr>
         			<tr>
         				<td colspan="4" style="text-align: center;">${requestScope.receiveMsg.receiveMsgContent }</td>
         			</tr>
         		</tbody>
         	</table>
		            
		     
        </div>


    </body>
</html>