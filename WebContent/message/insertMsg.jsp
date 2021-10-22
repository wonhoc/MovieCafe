<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        	${writer}
        	${receiveId }
           	${sendMsgContent }
		              
        </div>


    </body>
</html>