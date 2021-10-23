<%--sendMsgList.jsp --%>
<%@page import="domain.message.SendMessageVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

    
<!DOCTYPE html>
<html>
 <head>
        <meta charset='UTF-8'>
        <title>내가쓴 쪽지함</title>
    </head>
    <body>
        <div id="navibox">
            <button type="button" id="sendFormBtn">쪽지쓰기</button>
            <button type="button" id="recieveMsglistBtn">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn">받은쪽지함</button>
        </div>
        <div id="content">
            <div>
		        <table border="1" id="sendMsgList">
		        	<thead>
		              <tr>		              	
		              	<td>받는사람</td>
		              	<td width="200px">내용</td> 
		              	<td>작성시간</td>   
		              </tr>
		             </thead>
		             <tbody>
				<c:if test="${empty requestScope.sendMsgList }">
					<tr>
						<td colspan="4">보낸 쪽지가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty requestScope.sendMsgList }">
					<c:forEach var="sendMsg" items="${requestScope.sendMsgList }" varStatus="loop">
						<c:url var="url" value="/detailSendMsg.do" >
							<c:param name="sendMSgNo" value="${pageScope.sendMsg.sendMsgNo }"/>
						</c:url>
						<tr>
							<td>
							<!-- 받는 아이디가 1명 이상일때  -->
								<c:if test="${fn:length(pageScope.sendMsg.address) < 1 }">
									<c:forEach var="receiveId" items="${pageScope.sendMsg.address }" begin="0" end="0" >
										${reciveId[0] } 외 ${fn:length(pageScope.sendMsg.address) }명		
									</c:forEach>
								</c:if> 
								<!--  받는 아이디가 1명일때 -->
								<c:if test="${fn:length(pageScope.sendMsg.address) == 1 }">							
										${sendMsg.address[0]}							
								</c:if>
							
						
								
							</td>
							<td><a href="${pageScope.url }">${pageScope.sendMsg.sendMsgContent }</a></td>
							<td>${pageScope.sendMsg.msgWdate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		        </table>
       	 
       		</div>
		</div>

    </body>
</html>