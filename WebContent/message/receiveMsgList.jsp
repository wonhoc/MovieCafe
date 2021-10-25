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
        <title>받은 쪽지</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    </head>
    <body>
    
        <div id="navibox" style="margin-left: 40px; margin-bottom: 20px">
            <button type="button" id="sendFormBtn" onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
            <button type="button" id="sendMsglistBtn" onclick="location='${pageContext.request.contextPath }/sendMsgList.do'">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn" onclick="location='${pageContext.request.contextPath }/receiveMsgList.do'">받은쪽지함</button>
        </div>
        <form action="${pageContext.request.contextPath }/removeReceiveMsg.do" method="GET" id="removeReceiveMsgForm">
        <div id="content">
            <div>
		        <table border="1" id="sendMsgList">
		        	<thead>
		              <tr>		              	
		              	<td style="text-align: center;">보낸사람</td>
		              	<td style="text-align: center; width: 100px">내용</td> 
		              	<td>작성시간</td>  
		              	<c:if test="${not empty requestScope.receiveMsgList }"> 
		              	<td><input type="checkbox" id="allChecked">삭제</td>
		              	</c:if>
		              </tr>
		             </thead>
		             <tbody>
				<c:if test="${empty requestScope.receiveMsgList }">
					<tr>
						<td colspan="4">받은 쪽지가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty requestScope.receiveMsgList }">				
					<c:forEach var="receiveMsg" items="${requestScope.receiveMsgList }" varStatus="loop">	
						<c:url var="detailReceiveMsgUrl" value="/detailReceiveMsg.do">
							<c:param name="receiveMsgNo" value="${receiveMsg.receiveMsgNo }"/><%-- 받은 메세지 번호 --%>
							<c:param name="isRead" value="${receiveMsg.isRead }"/><%-- 읽음확인 정보 --%>
							<c:param name="receiveId" value="${requestScope.userId }"/><%-- 주소록에 수정할 읽은 사람 추후 session으로 바꿔주기 --%>
						</c:url>					
						<tr>				
							<td>
							${receiveMsg.writerId }
							</td>
							<%-- 내용이 10글자가 넘으면 --%>
							<c:if test="${fn:length(pageScope.receiveMsg.receiveMsgContent) >= 11 }">
							<td><a href="${detailReceiveMsgUrl } ">${fn:substring(pageScope.receiveMsg.receiveMsgContent,0,10) }...</a></td>			
							</c:if>					
							<%-- 내용이 10글자가 안넘으면 --%>
							<c:if test="${fn:length(pageScope.receiveMsg.receiveMsgContent) <= 10 }">
							<td><a href="${detailReceiveMsgUrl } ">${fn:substring(pageScope.receiveMsg.receiveMsgContent,0,10) }</a></td>
							</c:if>
							<td>${fn:substring(pageScope.receiveMsg.msgWdate,0,10) }</td>																		<%-- 나중에 session으로 --%>
							<td><input type="checkbox" name="removeCheckBox" value="${pageScope.receiveMsg.receiveMsgNo },${pageScope.receiveMsg.isRead},${requestScope.userId}"></td>			
						</tr>					
					</c:forEach>
						
				</c:if>
			</tbody>
		        </table>
		        <c:if test="${not empty requestScope.receiveMsgList }"> 
		     <div id="etc">
       	 		<button type="submit" id="removeSendMsgBtn" style="margin-left: 300px">삭제</button>
       	 	</div>
       	 		</c:if>
       		</div>
		</div>
		</form>
		<script>
		<%-- 전체삭제 클릭시  --%>
		$('#allChecked').on('click',function(){
			//allChecked가 체크될시
			if($('#allChecked').is(':checked')){
				//removeCheckBox의 체크를 true로
				 $("input[name=removeCheckBox]").prop("checked",true);
			}else{
				//removeCheckBox의 체크를 false로
				 $("input[name=removeCheckBox]").prop("checked",false);
			}
			
			
		});
		
		</script>
    </body>
</html>