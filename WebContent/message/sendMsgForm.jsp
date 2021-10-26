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
       
<style>
#sendFormBtn, #sendMsglistBtn, #recieveMsglistBtn, #sendMsgBtn {
	height: 2rem;
	margin: 0 0.3em;
	font-size: 18px;
	color: #5b5b5b;
	background-color: #fdfdfd;
	border: 2px solid #b1b1b1;
	border-radius: 0.25em;
	transition: all 150ms ease-in;
	cursor: pointer;
}

#sendFormBtn:hover, #sendMsglistBtn:hover, #recieveMsglistBtn:hover,
	#sendMsgBtn:hover {
	background-color: #e2e2e2;
}

#sendMsgListTb {
	border: #eab48a 4px solid;
	border-radius: 0.5em;
}

.reciveId {
	width: 60%;
	height: 1.7rem;
	line-height: 1.7rem;
}

#plusFrom {
	cursor: pointer;
}

#recieveId, .content {
	font-size: 16px;
	padding: 0.5em;
	border: 1px solid #b1b1b1;
}

#sendMsgContent {
	width: 300px;
	height: 200px;
}

.bottom {
	text-align: end;
}

#infoSendtext{
	color: red;
}
</style>
</head>
<body>
	<div id="navibox" style="margin-left: 40px; margin-bottom: 20px">
		<button type="button" id="sendFormBtn"
			onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
		<button type="button" id="sendMsglistBtn"
			onclick="location='${pageContext.request.contextPath }/sendMsgList.do'">보낸쪽지함</button>
		<button type="button" id="recieveMsglistBtn"
			onclick="location='${pageContext.request.contextPath }/receiveMsgList.do'">받은쪽지함</button>
	</div>
	<div id="content">
		<form id="sendMsgForm"
			action="${pageContext.request.contextPath }/sendMsg.do" method="POST">
			<input type="hidden" name="userid" id="userid" value="test_user01">
			<table border="1" id="sendMsgListTb">
				<tr>
					<td id="recieveId">받는사람 : <input type="text" name="reciveId"
						class="reciveId" placeholder="받는사람">
						<button type="button" id="plusFrom">+</button></td>
				</tr>
				<tr>
					<td>내용<br> <textarea id="sendMsgContent"
							name="sendMsgContent" id="sendMsgContent"
							style="width: 300px; height: 200px;"></textarea>

					</td>
				</tr>
				<tr>
					<td><div id="infoSendtext"></div>
						<button type="button" id="sendMsgBtn" style="margin-left: 250px;">보내기</button></td>
				</tr>

			</table>
		</form>
	</div>
	 <script>

		<%-- 받는사람 숫자 --%>
		var temp = 0;
		<%-- 받는사람, 내용 없을시 false return --%>
		$(document).ready(function() {
			
			
			$('#content').on('blur', 'input[name=reciveId]', function() {
				
				let inputId = $(this).val();
				receivechkProcess('${pageContext.request.contextPath}/CheckReceiveId.do',inputId);
				
				
			});
			
				$('#sendMsgBtn').on('click', function() {
					<%-- 내용유효성 검사 --%>				
					if($('#sendMsgContent').val().trim() == '') {
						alert('내용을 정확하게 입력해주세요.');
						return false;
					}
					<%-- 내용유효성 검사 끝 --%>
				<%-- 받는 사람 유효성 검사--%>
				$('input[name=reciveId]').each(function(i) {
					const name = $(this).val();

					if(name == '') {
						alert('받는 사람을 입력해주세요.');
						return false;							
					}
					
				$('#sendMsgForm').submit();
				
				});						
				<%-- 받는 사람 유효성 검사 끝 --%>
					});

				<%-- Ajax  --%>
				 const getAjax = function(url, userId) {
					return new Promise( (resolve, reject) => {
						$.ajax({
							url: url,
							method: 'POST',
							dataType: 'json',
							data: {
								userId: userId
							},
							//콜백함수
							success: function(data) {
								resolve(data);
							},
							error : function(e) {
								reject(e);
							}
						});
					});
				};
				
				async function receivechkProcess(url, userId) {
	
					console.log('name = ' , userId);
					try {
						const result = await getAjax(url, userId);
						console.log("result : ", result.resultCount);
						if(result.resultCount == 0 ) {
							alert(userId +  '에게는 쪽지를 보낼 수 없습니다.\n아이디를 다시확인 해주세요.');
							$('#sendMsgBtn').attr("disabled", true);
							$('#infoSendtext').html('<div>아이디를 다시 확인해주세요</div>');
		 				}else{
		 					$('#sendMsgBtn').attr("disabled", false);
		 					$('#infoSendtext').html('');
		 				}
					} catch (error)  {
						console.log("error : ", error);	
						
					}
				}

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
		<%-- 버튼 클릭시 받는사람 삭제 --%>
		$(document).on("click","#minForm",function(){
			temp = temp -1;
			$(this).prev().prev().remove();
			$(this).prev().remove();
			$(this).remove();
		});
		
		</script>
</body>
</html>