<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정폼</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 <style>
 body {                
       margin: 10px auto;
       width: 500px;
       padding-top: 50px;
       padding-bottom: 30px;
       }
</style>

<script src="https://code.jquery.com/jquery-3.5.0.js"></script>


<script>


//클
	$(document).ready(function() {
		$('.removeBtn').on('click' , function() {
			const boardfileNo = $(this).attr('id')
			location.href = '${pageContext.request.contextPath}/removeFile.do?boardfileNo=' + boardfileNo;
			
		});
	
	
	});

</script>
</head>
<body>
<div class="container" role="main">
<h1>게시글 수정</h1>
<input type="hidden" id="apiX1" value="${sessionScope.board.apiX}">
	<input type="hidden" id="apiY1" value="${sessionScope.board.apiY}">

	<form action="${pageContext.request.contextPath}/modifyBoardForm" method="POST" enctype="multipart/form-data">
		 <%-- 세션에 있는 아이디  --%>
	 <input type="hidden" name = "userId" value="test_user01">
	 
	<input type="hidden" id="apiX" name="apiX" value="">
	<input type="hidden" id="apiY" name="apiY" value="">
	
	 <%-- 말머리(나중에 수정) --%>
	 <div class="mb-3">
            <label>말머리</label>
            <select name="horseNo" id="horseNo">
                <option value="10">할인정보</option>
                <option value="11">영화관 추천 </option>
            </select>
        </div>
         
        
       
        
        <div class="mb-3">
        	<input type="text" class="form-control" name="boardTitle" id = "boardTitle" value="${sessionScope.board.boardTitle}">
        </div>
        
        <div class="mb-3">
        	<input type="text" class="form-control" name="boardContent" id = "boardContent" value="${sessionScope.board.boardContent}" style="height: 300px;">
        </div>
        
        
		<div class="mb-3">
			<label>파일</label>
			<input type="file" class="form-control" name = "fileList" multiple>
		</div>
		
		<div>
			<button type="submit" class="btn btn-sm btn-primary" id="modifyBtn">수정</button>
			<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='listBoard.do'">목록</button>
			<input type="checkbox" value ="1" name="boardNotice"><label>공지</label>
		</div>
		
	</form>
</div>

	

<c:if test="${not empty sessionScope.board.boardfileList}">
	<table id ="tbl">
		<tr>
			<th>파일명</th><th>파일크기</th><th>비고</th>
		</tr>
		
		<c:forEach var="file" items="${sessionScope.board.boardfileList}">
		<tr>
			<td>${file.boardfileOrigin}</td>
			<td>${file.boardfileSize}</td>
			<td><button class="removeBtn" id="${file.boardfileNo}">삭제</button></td>
		</tr>
		
		
		</c:forEach>
	</table>
</c:if>


<input type="text" id="sample5_address" placeholder="주소">
<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a78a37acbaa267bade844724b94c806&libraries=services"></script>
<script>
	var apiX1 = document.getElementById('apiX1').value;
	var apiY1 = document.getElementById('apiY1').value;
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(apiY1, apiX1), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(apiY1, apiX1),
        map: map
    });


    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                        
                        document.getElementById('apiY').value = result.y;
                        document.getElementById('apiX').value = result.x;
                    
                        alert(document.getElementById('apiY').value );
                        alert(document.getElementById('apiX').value );
                    }
                });
            }
        }).open();
    }
</script>

</body>
</html>