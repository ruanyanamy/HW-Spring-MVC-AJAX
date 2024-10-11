<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>錯誤頁面</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
   <div style="text-align: center;">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th class="text-danger" style="font-size: 1.5rem; font-weight: bold;">錯誤訊息</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ErrorMsgs" items="${ErrorMsgs}">
                <tr>
                    <td>${ErrorMsgs}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/playlottery/signup" class="btn btn-primary">返回註冊頁</a>
    <a href="/playlottery/signin" class="btn btn-primary">返回登入頁</a>
    <a href="/playlottery/lottery" class="btn btn-primary">樂透重新輸入</a>
</div>
</div>
</body>
</html>
