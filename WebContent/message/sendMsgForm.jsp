<%--sendMsgForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
 <head>
        <meta charset='UTF-8'>
        <title>쪽지 작성</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" 
        		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
        		crossorigin="anonymous">
        </script>
        <script>
        	
        </script>
    </head>
    <body>
        <div id="navibox" style="margin-left: 40px; margin-bottom: 20px">
            <button type="button" id="sendFormBtn" onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
            <button type="button" id="sendMsglistBtn" onclick="location='${pageContext.request.contextPath }/sendMsgList.do'">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn" onclick="location='${pageContext.request.contextPath }/receiveMsgList.do'">받은쪽지함</button>
        </div>
        <div id="content">
            <form id="sendMsgForm" action="${pageContext.request.contextPath }/sendMsg.do" method="POST">
            <input type="hidden" value="${sessionScope.UserInfo.userId }"> 
		        <table border="1" id="sendMsgListTb">
		                <tr>
		                    <td id="recieveId">받는사람 : <input type="text" name="reciveId" class="reciveId" placeholder="받는사람">
		                        <button type="button" id="plusFrom" >+</button></td>
		                </tr>
		                <tr>
		                    <td>내용<br>
		                    <textarea id="sendMsgContent" name="sendMsgContent" id="sendMsgContent" style="width: 300px; height: 200px;"></textarea>
		                    
		                    </td>
		                </tr>
		                <tr>
		                    <td><button type="button" id="sendMsgBtn" style="margin-left: 250px;">보내기</button></td>
		                </tr>
		            
		        </table>
       	 </form>
        </div>
		<script>
		<%-- 받는사람 숫자 --%>
		var temp = 0;
		<%-- 받는사람, 내용 없을시 false return --%>
		$(document).ready(function() {
				$('#sendMsgBtn').on('click', function() {
					
				if($('#sendMsgContent').val().trim() == ""){
					alert('내용을 입력해주세요.');
					return false;
				}
					
					
					let count = 0;
					$('input[name=reciveId]').each(function() {
						const name = $(this).val().trim();
						if(name == '') {
							count++;
						}
					});	
					if(count != 0) {
						alert('받는사람을 정확하게 입력해주세요.')
						return false;
					}	
				})     		
    	});
		<%-- 받는사람 추가 --%>
		$('#plusFrom').on('click', function() {
			if(temp<4){temp = temp + 1;}else{
				alert('쪽지는 한번에 5명까지만 보낼 수 있습니다.');
				return false;			
			}		
			const htmlstr = '<br><input type="text" name="reciveId" id="reciveId" placeholder="받는사람">'
				+ '<button type="button" id="minForm">-</button>';
			$('#recieveId').append(htmlstr);		
		});
		<%-- -버튼 클릭시 받는사람 삭제 --%>
		$(document).on("click","#minForm",function(){
			temp = temp -1;
			$(this).prev().prev().remove();
			$(this).prev().remove();
			$(this).remove();
		});
		</script>
    </body>
</html>