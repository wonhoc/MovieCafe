<!DOCTYPE html>
<html lang='ko'>
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
            <form id="MsgboxList" action="${pageContext.request.contextPath }/sendMsg.do" method="POST">
		        <table border="1">
		        	<tr>
		                <td>${recieveId }</td>
		                <td>${sos }zz</td>
		            </tr>
		            
		        </table>
       	 </form>
        </div>


    </body>
</html>