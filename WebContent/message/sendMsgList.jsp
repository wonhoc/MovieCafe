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
        <title>내가쓴 쪽지</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    </head>
    <body>
    
        <div id="navibox" style="margin-left: 40px; margin-bottom: 20px">
            <button type="button" id="sendFormBtn" onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
            <button type="button" id="recieveMsglistBtn">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn">받은쪽지함</button>
        </div>
        <form action="${pageContext.request.contextPath }/removeSendMsg.do" method="GET" id="removeSendMsgForm">
        <div id="content">
            <div>
		        <table border="1" id="sendMsgList">
		        	<thead>
		              <tr>		              	
		              	<td style="text-align: center;">받는사람</td>
		              	<td style="text-align: center; width: 100px">내용</td> 
		              	<td>작성시간</td>   
		              	<td><input type="checkbox" id="allChecked">삭제</td>
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
						<c:url var="detailSendMsgUrl" value="/detailSendMsg.do">
							<c:param name="sendMsgNo" value="${sendMsg.sendMsgNo }"/>
						</c:url>					
						<tr>				
							<td>
							<!-- 받는 아이디가 1명 이상일때  -->
								<c:if test="${fn:length(pageScope.sendMsg.address) > 1 }">								
									${ pageScope.sendMsg.address[0] }외 ${fn:length(pageScope.sendMsg.address) -1 }명		
								</c:if> 
								<!--  받는 아이디가 1명일때 -->
								<c:if test="${fn:length(pageScope.sendMsg.address) == 1 }">							
										${sendMsg.address[0]}							
								</c:if>						
							</td>
							<%-- 내용이 10글자가 넘으면 --%>
							<c:if test="${fn:length(pageScope.sendMsg.sendMsgContent) >= 11 }">
							<td><a href="${detailSendMsgUrl } ">${fn:substring(pageScope.sendMsg.sendMsgContent,0,10) }...</a></td>			
							</c:if>					
							<%-- 내용이 10글자가 안넘으면 --%>
							<c:if test="${fn:length(pageScope.sendMsg.sendMsgContent) <= 10 }">
							<td><a href="${detailSendMsgUrl } ">${fn:substring(pageScope.sendMsg.sendMsgContent,0,10) }</a></td>
							</c:if>
							<td>${fn:substring(pageScope.sendMsg.msgWdate,0,10) }</td>
							<td><input type="checkbox" name="removeCheckBox" value="${pageScope.sendMsg.sendMsgNo }"></td>			
						</tr>					
					</c:forEach>
						
				</c:if>
			</tbody>
		        </table>
		     <div id="etc">
       	 		<button type="submit" id="removeSendMsgBtn" style="margin-left: 300px">삭제</button>
       	 	</div>
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