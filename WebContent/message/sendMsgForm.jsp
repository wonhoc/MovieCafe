<%--sendMsgForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
 <head>
        <meta charset='UTF-8'>
        <title>쪽지함</title>
    </head>
    <body>
        <div id="navibox">
            <button type="button" id="sendFormBtn">쪽지쓰기</button>
            <button type="button" id="recieveMsglistBtn">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn">받은쪽지함</button>
        </div>
        <div id="content">
            <form id="sendMsgForm" action="${pageContext.request.contextPath }/sendMsg.do" method="POST">
            <input type="hidden" name="userid" id="userid" value="test_user01" >
		        <table border="1">
		                <tr id="recieveId">
		                    <td>받는사람 : <input type="text" name="reciveId" id="reciveId" placeholder="받는사람">
		                        <button type="button" id="plusFromForm">+</button></td>
		                </tr>
		                <tr>
		                    <td>내용<br>
		                    <textarea id="sendMsgContent" name="sendMsgContent" style="width: 300px; height: 200px;"></textarea>
		                    
		                    </td>
		                </tr>
		                <tr>
		                    <td><button type="submit" id="sendMsgBtn" style="margin-left: 250px;">보내기</button></td>
		                </tr>
		            
		        </table>
       	 </form>
        </div>


    </body>
</html>