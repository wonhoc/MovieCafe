<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>


<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

</head>
<body>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>조회</th>
				<th>추천</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>


	<%-- 검색 --%>
	<div id = "search">
		<select id = "keyfield" style="height : 30px;">
			<option value = "title">제목</option>
			<option value = "content">내용</option>
			<option value = "title">작성자</option>
		</select>
		<input type ="search" placeholder="검색어를 입력해주세요" size="30" id="keyword" style="height : 30px">
		<button type ="button" id ="Btn" style="height : 30px">검색</button>
	</div>

	<%-- 검색 --%>
<script>
	
	const getAjax = function(url, keyfield, keyword){
		
		return new Promise( (resolve,reject) => {
			$.ajax({
				url: url,
				method: 'POST',
				dataType: 'json',
				data: {
					keyfield: keyfield,
					keyword: keyword
				},
				success: function(data){
					resolve(data);
				},
				error: function(e){
					reject(e);
				}
			});
		});
	}

	async function requestProcess(url, keyfield, keyword){
		try{
			const boards = await getAjax(url, keyfield, keyword);
			
			var htmlStr = '';
			for (var i = 0; i < boards.length; i++) {
				html += '<tr id="' + boards[i].no + '">';
				html += '<td>' + (boards.length - i) + '</td>';
				html += '<td>' + boards[i].userId + '</td>';
				html += '<td>' + boards[i].title + '</td>';
				html += '<td>' + boards[i].boardWdate + '</td>';
				html += '<td>' + boards[i].hitCount + '</td>';
				html += '<td>' + boards[i].recomCount + '</td>';
				html += '<td>' + boards[i].commentCount + '</td>';
				html += '</tr>';
			}
			$('body > table > tbody').html(htmlStr);			
			
		}catch(error){
			consolw.log("error : ", error);
		}
	}
	
	$('#Btn').on('click', function(){
		const keyfield = $('#keyfield option:selected').val();
		const keyword = $('#keyword').val();
		
		requestProcess('/board/searchBoard.do',keyfield,keyword);
	});

</script>
</body>
</html>