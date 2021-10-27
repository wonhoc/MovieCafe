<%-- detailBoard.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.board.BoardVo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%
	session.setAttribute("userId", "test_user07");
pageContext.setAttribute("CR", "\n");
pageContext.setAttribute("BR", "<br>");
%>
<!DOCTYPE html>

<html lang='ko'>

<head>

<meta charset='UTF-8'>

<title>게시글 상세조회</title>

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
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!--  댓글 작성 -->
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

	<%-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!수정 , 삭제 추가하기 --%>
	
	<%-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!수정 , 삭제 추가하기 --%>

	<c:if test="${requestScope.cateNo==1}">
<c:url var="listUrl" value="/board/listBoard_NewMem.do">
						<c:param name= "cateNo" value="${requestScope.cateNo }"/>
					</c:url>
<a href="${listUrl }">목록</a>
		<input type="button" >
		<h3>새싹 게시글 상세보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td>${requestScope.board.boardTitle}</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${requestScope.cateNo==2}">
	<c:url var="listUrl" value="/board/listBoard_MovieReview.do">
						<c:param name= "cateNo" value="${requestScope.cateNo }"/>
					</c:url>
					<a href="${listUrl }">목록</a>
		<h3>영화 리뷰 상세보기</h3>

		<table>
			<tr>
				<td>장르</td>
				<td>${requestScope.board.horse}</td>
				<td>제목</td>
				<td>${requestScope.board.boardTitle}</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${requestScope.cateNo==3}">
	<c:url var="listUrl" value="/board/listBoard_sisa.do">
						<c:param name= "cateNo" value="${requestScope.cateNo }"/>
					</c:url>
					<a href="${listUrl }">목록</a>
		<h3>시사회 정보글 상세보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td>${requestScope.board.boardTitle}</td>
			</tr>
		</table>
	</c:if>

<%----- 영화관람 팁은 따로 jsp!(이코드는 삭제예정) -----------------------------%>
	<c:if test="${requestScope.cateNo==4}">
	<c:url var="listUrl" value="/board/listBoard_Tip.do">
						<c:param name= "cateNo" value="${requestScope.cateNo }"/>
					</c:url>
					<a href="${listUrl }">목록</a>
		<h3>영화 관람 팁 상세보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td>${requestScope.board.boardTitle}</td>
			</tr>
		</table>
	</c:if>
<%----------------------------- --%>
	<c:if test="${requestScope.cateNo==5}">
	<c:url var="listUrl" value="/board/listBoard_Ticket.do">
						<c:param name= "cateNo" value="${requestScope.cateNo }"/>
					</c:url>
					<a href="${listUrl }">목록</a>
		<h3>티켓마켓 글 상세보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td>${requestScope.board.boardTitle}</td>
			</tr>
		</table>
	</c:if>


	<c:if test="${requestScope.cateNo==6}">
	<c:url var="listUrl" value="/board/listBoard_Event.do">
						<c:param name= "cateNo" value="${requestScope.cateNo }"/>
					</c:url>
					<a href="${listUrl }">목록</a>
		<h3>이벤트 상세보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td>${requestScope.board.boardTitle}</td>
			</tr>
		</table>
	</c:if>

	<table>
		<tr>
			<td>닉네임</td>
			<td>${requestScope.board.userNick}</td>
			<td>작성일자</td>
			<td>${requestScope.board.boardWdate}</td>
			<td>조회수</td>
			<td>${requestScope.board.boardCount}</td>
		</tr>
		<tr>
			<td height="200px">내용</td>
			<td colspan="6">${fn:replace(requestScope.board.boardContent, CR, BR) }</td>
			<%-- pageScope.CR이지만 pageScope는 생략 가능해서! --%>
		</tr>
	</table>

	추천!
	<button type="button" id="recomBtn">
		<img src="../images/recomBtn.png">
	</button>
	신고
	<img id="reportBtn" src="../images/reportBtn.jpg">

	<c:url var="modifyUrl" value="/board/modifyBoardForm.do">
		<c:param name="boardNo" value="${requestScope.board.boardNo}"/>
		<c:param name="cateNo" value="${requestScope.cateNo}"/>
	</c:url>
	<a href="${modifyUrl}">수정</a>&nbsp;&nbsp;
	
	<c:url var="removeUrl" value="/removeBoard.do">
		<c:param name="boardNo" value="${requestScope.board.boardNo}"/>
		
	</c:url>
	<a href="${removeUrl}">삭제</a>&nbsp;&nbsp;
	

	<c:if test="${not empty requestScope.board.boardfileList}">
		<table>
			<tr>
				<th>파일명</th>
				<th>파일크기</th>
			</tr>

			<c:forEach var="file" items="${requestScope.board.boardfileList }">
				<c:url var="downloadUrl" value="/fileDownload">
					<c:param name="boardfileOrigin" value="${file.boardfileOrigin }"></c:param>
					<c:param name="boardfileSys" value="${file.boardfileSys }"></c:param>
				</c:url>
				<tr>
					<td><a href="${downloadUrl}">${file.boardfileOrigin }</a></td>
					<td>${file.boardfileSize}bytes</td>
				</tr>

			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty requestScope.board.boardfileList}">
		<table>
			<tr>
				<th>파일명</th>
				<th>파일크기</th>
			</tr>
			<tr>
				<td colspan="2">파일 없음</td>
			</tr>
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


</body>
</html>