<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*, domain.board.BoardVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 700px;
	border-collapse: collapse;
	margin: 50px auto;
	font-size: 12px;
}

table, tr, th, td {
	border: 1px solid pink;
	text-align: center;
}

th, td {
	height: 35px;
}

h3 {
	text-align: center;
}

#paging {
	margin: 10px auto;
}
section {
text-align: center;
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>

	$(document).ready(function() {

        const getAjax = function(url, comContent,boardNo) {
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'GET',
                    dataType: 'json',
                    data: {
                    	url: url,
                     	comContent: comContent,
                     	boardNo: boardNo
                    },
                    success: function(data) {                    	
                        resolve(data);
                    }, 
                    error: function(e) {                    	
                        reject(e);
                    }
                });
            });
        }   
        
        const removeComment = function(url, comNo, boardNo) {
            // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'GET',
                    dataType: 'json',
                    data: {
                    	url: url,
                    	comNo: comNo,
                    	boardNo: boardNo
                    },
                    success: function(data) {                    	
                        resolve(data);
                    }, 
                    error: function(e) {                    	
                        reject(e);
                    }
                });
            });
        }   

        const modifyComment = function(url, comNo, comContent, boardNo) {
            // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'GET',
                    dataType: 'json',
                    data: {
                    	url: url,
                    	comNo: comNo,
                    	comContent: comContent,
                    	boardNo: boardNo
                    },
                    success: function(data) {                    	
                        resolve(data);
                    }, 
                    error: function(e) {                    	
                        reject(e);
                    }
                });
            });
        }   
        //댓글 작성ㅇ용 함수
        async function requestProcess(url, comContent, boardNo) {
            try {
            	let comList = null;
            	
            		comList = await getAjax(url, comContent,boardNo);	
            	
            	     
                                   
                $('#ListComment').html("");
               
			 	let htmlStr = [];
			 	for(let i = 0; i< comList.length; i++) {
			 		htmlStr.push('<table id=' + comList[i].comNo +'>');
			 		htmlStr.push('<tbody>');
			 		htmlStr.push('<tr>');
			 		htmlStr.push('<td>' + comList[i].userId + '</td>');
			 		htmlStr.push('<td>' + comList[i].comWdate + '</td>');
			 		htmlStr.push('</tr>');		
			 		htmlStr.push('<tr>');	
			 		htmlStr.push('<td colspan="2" class="comContent">' + comList[i].comContent + '</td>');
			 		htmlStr.push('</tr>');
			 		htmlStr.push('<tr>');
			 		
			 			htmlStr.push('<td colspan="2">');
				 		htmlStr.push('<button class="modifyFormBtn" type="button">수정</button>&nbsp;');		
				 		htmlStr.push('<button class="removeBtn" type="button">삭제</button>');			
				 		htmlStr.push('</td>');	
			 	
			 						
			 		
			 		htmlStr.push('</tr>');
			 		htmlStr.push('</tbody>');
			 		htmlStr.push('</table>');				 		
			 	}         
			 	
			 	$('#ListComment').html(htmlStr.join(""));
                  
            } catch (error) {
                console.log("error : ", error);   
            }
        }
        
        
        
        //댓글 삭제용 함수
        async function removeRequestProcess(url, comNo, boardNo) {
            try {
            	let comList = null;
            	
            		comList = await removeComment(url, comNo,boardNo);	
            		$('#ListComment').html("");
                    
    			 	let htmlStr = [];
    			 	for(let i = 0; i< comList.length; i++) {
    			 		htmlStr.push('<table id=' + comList[i].comNo +'>');
    			 		htmlStr.push('<tbody>');
    			 		htmlStr.push('<tr>');
    			 		htmlStr.push('<td>' + comList[i].userId + '</td>');
    			 		htmlStr.push('<td>' + comList[i].comWdate + '</td>');
    			 		htmlStr.push('</tr>');		
    			 		htmlStr.push('<tr>');	
    			 		htmlStr.push('<td colspan="2" class="comContent">' + comList[i].comContent + '</td>');
    			 		htmlStr.push('</tr>');
    			 		htmlStr.push('<tr>');	
    			 		htmlStr.push('<td colspan="2">');
    			 		htmlStr.push('<button class="modifyFormBtn" type="button">수정</button>&nbsp;');		
    			 		htmlStr.push('<button class="removeBtn" type="button">삭제</button>');			
    			 		htmlStr.push('</td>');					
    			 		htmlStr.push('</tr>');
    			 		htmlStr.push('</tbody>');
    			 		htmlStr.push('</table>');				 		
    			 	}         
            
    			 	$('#ListComment').html(htmlStr.join(""));
            }catch (error) {
                console.log("error : ", error);   
            }
        }
		
        //댓글 수정용 함수
        async function modifyRequestProcess(url, comNo, comContent, boardNo) {
            try {
            	let comList = null;
            	
            		comList = await modifyComment(url, comNo, comContent, boardNo);	
            	                   
                $('#ListComment').html("");
               
			 	let htmlStr = [];
			 	for(let i = 0; i< comList.length; i++) {
			 		htmlStr.push('<table id=' + comList[i].comNo +'>');
			 		htmlStr.push('<tbody>');
			 		htmlStr.push('<tr>');
			 		htmlStr.push('<td>' + comList[i].userId + '</td>');
			 		htmlStr.push('<td>' + comList[i].comWdate + '</td>');
			 		htmlStr.push('</tr>');		
			 		htmlStr.push('<tr>');	
			 		htmlStr.push('<td colspan="2" class="comContent">' + comList[i].comContent + '</td>');
			 		htmlStr.push('</tr>');
			 		htmlStr.push('<tr>');
			 		
			 		htmlStr.push('<td colspan="2">');
			 		htmlStr.push('<button class="modifyFormBtn" type="button">수정</button>&nbsp;');		
			 		htmlStr.push('<button class="removeBtn" type="button">삭제</button>');			
			 		htmlStr.push('</td>');	
			 		
			 		htmlStr.push('</tr>');
			 		htmlStr.push('</tbody>');
			 		htmlStr.push('</table>');				 		
			 	}         
			 	
			 	$('#ListComment').html(htmlStr.join(""));
                  
            } catch (error) {
                console.log("error : ", error);   
            }
        }
        
        //댓글 작성
	    $('#addComBtn').on('click', function() {
	    	const comContent = $('#addComContent').val();
	    	const boardNo = ${requestScope.board.boardNo};
	    	requestProcess('${pageContext.request.contextPath}/board/writeComment.do', comContent,boardNo);
	   
	    });
        

        $('#ListComment').on('click', '.modifyFormBtn', function() {                
        	const comNo = $(this).parents('table').attr('id');
        	$('#modifyComment').insertAfter('#' + comNo);                	
        	const comContent = $(this).parents('tbody').find('.comContent').text();                
        	$('#modifyComContent').val(comContent);
        	$('#comNo').val(comNo);
        	$('#modifyComment').show();
        	$('#' + comNo).hide();                	
        });
        
        
        
        //댓글 삭제
        $('#ListComment').on('click', '.removeBtn', function() { 
        	const boardNo = ${requestScope.board.boardNo};
        	const comNo = $(this).parents('table').attr('id');
        	removeRequestProcess('${pageContext.request.contextPath}/board/removeComment.do', comNo, boardNo);        	
        });
        
        
        //댓글 취소
        $('#cancel').on('click', function() {
        	const comNo = $('#comNo').val();
        	$('#' + comNo).show();    
        	$('#modifyComment').hide();
        	$('#modifyComment').insertAfter('#addComment');
        });
        
        //댓글 수정
        $('#modifyBtn').on('click', function() {
        	const comNo = $('#comNo').val();
        	const comContent = $('#modifyComContent').val();
        	const boardNo = ${requestScope.board.boardNo};
        	modifyRequestProcess('${pageContext.request.contextPath}/board/modifyComment.do', comNo, comContent, boardNo);   
        
        });
        
        <!--  추천 처리 -->
        const recomAjax = function(url, boardNo) {
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'GET',
                    dataType: 'json',
                    data: {
                    	url: url,
                     	boardNo: boardNo
                     	
                    },
                    success: function(data) {                    	
                        resolve(data);
                    }, 
                    error: function(e) {                    	
                        reject(e);
                    }
                });
            });
        }   
        
        
        //추천등록용 함수
        async function requestRecomProcess(url, boardNo) {
            try {
            	await recomAjax(url,boardNo);
            	
            	
            	}         
			 	 catch (error) {
                console.log("error : ", error);   
            }
        }
        
        
        //추천 insert
        let isRecommend = true;
        console.log("is", isRecommend);
	    $("#recomBtn").click(	    		
	    	function() {
	    		
	    	
	    		if(isRecommend) {
	    			alert("추천완료");
	    			isRecommend = false;
	    			const boardNo = ${requestScope.board.boardNo};
		    		requestRecomProcess('${pageContext.request.contextPath}/board/recomBoard.do', boardNo);
	    		} else {
	    			alert("추천취소");
	    			isRecommend = true;	 
	    			const boardNo = ${requestScope.board.boardNo};
		    		requestRecomProcess('${pageContext.request.contextPath}/board/recomBoard.do', boardNo);
	    		}
	    		
	    	 }
	    );
        
	    
	    <!--  신고 처리 -->
        const reportAjax = function(url, boardNo) {
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'GET',
                    dataType: 'json',
                    data: {
                    	url: url,
                     	boardNo: boardNo
                     	
                    },
                    success: function(data) {                    	
                        resolve(data);
                    }, 
                    error: function(e) {                    	
                        reject(e);
                    }
                });
            });
        }   
        
        
        //신고등록용 함수
        async function requestReportProcess(url, boardNo) {
            try {
            	await reportAjax(url,boardNo);         	
            	}         
			 	 catch (error) {
                console.log("error : ", error);   
            }
        }
        
        
        //신고 insert
       $("#reportBtn").click(	    		
	    	function() {
	    		alert("신고완료");
	    			const boardNo = ${requestScope.board.boardNo};
		    		requestReportProcess('${pageContext.request.contextPath}/board/reportBoard.do', boardNo);
	    		
	    	 }
	    );
	});
	
	
	
	</script>

	


</head>

<body>
	<c:if test="${userInfo.rankType.equals('A') }">
		<div id="div">
			<c:url var="modifyUrl" value="/tipModifyBoardForm.do">
				<c:param name="boardNo" value="${requestScope.board.boardNo}" />
			</c:url>
			<a href="${modifyUrl}">수정</a>&nbsp;&nbsp;

			<c:url var="removeUrl" value="/main.do">
				<c:param name="boardNo" value="${requestScope.board.boardNo}" />
			</c:url>
			<a href="${removeUrl}">삭제</a>&nbsp;&nbsp;


		</div>
	</c:if>
	<c:url var="listUrl" value="/main.do">
		<c:param name="boardNo" value="${requestScope.board.boardNo}" />
	</c:url>
	<a href="${listUrl}">메인페이지</a>&nbsp;&nbsp;
	<input type="hidden" id="apiX" value="${requestScope.board.apiX}">
	<input type="hidden" id="apiY" value="${requestScope.board.apiY}">
	<table>
		<tr>
			<td>장르</td>
			<td id=>${requestScope.board.horse}</td>
			<td>제목</td>
			<td>${requestScope.board.boardTitle}</td>
		</tr>
	</table>
	<table>

		<tr>
			<td>닉네임</td>
			<td>${requestScope.board.userNick}</td>
			<td>작성일자</td>
			<td>${requestScope.board.boardWdate}</td>
			<td>조회수</td>
			<td>${requestScope.board.boardCount}</td>
		</tr>

	</table>
	<table>
		<tr>
			<td style="height: 100px;">${requestScope.board.boardContent}</td>
		</tr>
	</table>

	<c:if test="${not empty requestScope.board.apiX}">

		<div id="map" style="width: 300px; height: 300px;"></div>
		<script
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a78a37acbaa267bade844724b94c806"></script>
		<script>
	
 	var mapX = document.getElementById('apiX').value;
 	var mapY = document.getElementById('apiY').value;
 	
 	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(mapY, mapX), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
    }; 

// 지도를 생성한다 
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 지도에 마커를 생성하고 표시한다
var marker = new kakao.maps.Marker({
    position: new kakao.maps.LatLng(mapY, mapX), // 마커의 좌표
    map: map // 마커를 표시할 지도 객체
});


	</script>
	</c:if>



	<%-- 추천기능, 신고기능 --%>
	추천!
	<button type="button" id="recomBtn">
		<img src="../images/recomBtn.png">
	</button>
	신고
	<img id="reportBtn" src="../images/reportBtn.jpg">

	<c:if test="${not empty requestScope.board.boardfileList}">
		<table>
			<tr>
				<th>파일명</th>
				<th>파일크기</th>
			</tr>

			<c:forEach var="file" items="${requestScope.board.boardfileList }">
				<c:url var="downloadUrl" value="/fileDownload">
					<c:param name="originalFileName" value="${file.boardfileOrigin }"></c:param>
					<c:param name="systemFileName" value="${file.boardfileSys }"></c:param>
				</c:url>
				<tr>
					<td><a href="${downloadUrl}">${file.boardfileOrigin }</a></td>
					<td>${file.boardfileSize}bytes</td>
				</tr>

			</c:forEach>
		</table>
	</c:if>




	<%-- 파일리스트 --%>
	<c:if test="${not empty requestScope.board.boardfileList}">
		<table id="tbl">
			<tr>
				<th>파일명</th>
				<th>파일크기</th>
			</tr>
			<c:forEach var="file" items="${requestScope.board.boardfileList}">
				<c:url var="downloadUrl" value="/fileDownload">
					<c:param name="boardfileOrigin" value="${file.boardfileOrigin}" />
					<c:param name="boardfileSys" value="${file.boardfileSys}" />
				</c:url>
				<tr>
					<td><a href="${downloadUrl}">${file.boardfileOrigin}</a></td>
					<td>${file.boardfileSize}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<div id="ListComment">
		<c:forEach var="comment" items="${requestScope.commentList }">

			<table id="${comment.comNo}">
				<tbody>
					<tr>
						<td>${comment.userId}</td>
						<td>${comment.comWdate}</td>
					</tr>
					<tr>
						<td colspan="2" class="comContent">${comment.comContent }</td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="modifyFormBtn" type="button">수정</button>
							<button class="removeBtn" type="button">삭제</button>
						</td>
					</tr>
				</tbody>
			</table>

		</c:forEach>
	</div>






	<%-- 댓글 달기 --%>
	<div id="addComment">
		<div>
			<textarea id="addComContent" rows="5" cols="50"
				placeholder="댓글을 입력해주세오 ."></textarea>
		</div>
		<div>
			<button id="addComBtn">댓글 달기</button>
		</div>
	</div>


	<c:if test="${userInfo.rankType.equals('A') }">
		<%-- 댓글 수정--%>
		<div id="modifyComment" style="display: none;">
			<div>
				<input type="hidden" id="comNo" />
				<textarea id="modifyComContent" rows="5" cols="50"
					placeholder="댓글을 입력해주세오 ."></textarea>
			</div>
			<div>
				<button id="cancel">취소</button>
				<button id="modifyBtn">수정하기</button>
			</div>
		</div>
	</c:if>



</body>
</html>