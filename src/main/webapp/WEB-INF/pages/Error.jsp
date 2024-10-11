<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>錯誤頁面</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f9; 
            font-family: 'Arial', sans-serif;
            height: 100vh; 
            display: flex; 
            align-items: center; 
            justify-content: center; 
            margin: 0; 
        }
        .error-container {
            max-width: 600px; 
            padding: 2rem; 
            background-color: #ffffff; 
            border-radius: 10px; 
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); 
        }
        h2 {
            font-weight: bold;
            color: #333;
            text-align: center;
            margin-bottom: 1rem; 
        }
        .error-message {
            font-size: 1.2rem; 
            font-weight: bold; 
            text-align: center; 
            color: #d9534f; 
            margin: 0.5rem 0; 
            background-color: #ffe5e5; 
            padding: 1rem; 
            border-radius: 8px; 
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); 
        }
        .btn-primary {
            background-color: #5a67d8; 
            border: none;
            border-radius: 25px; 
            padding: 0.75rem 1.25rem; 
            display: inline-block; 
            margin: 0.5rem; 
            transition: background-color 0.3s; 
        }
        .btn-primary:hover {
            background-color: #4c51bf; 
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>錯誤訊息</h2>
        <c:forEach var="ErrorMsgs" items="${ErrorMsgs}">
            <div class="error-message">${ErrorMsgs}</div>
        </c:forEach>
        <div class="text-center mt-3">
            <a href="/playlottery/signup" class="btn btn-primary">返回註冊頁</a>
            <a href="/playlottery/signin" class="btn btn-primary">返回登入頁</a>
            <a href="/playlottery/lottery" class="btn btn-primary">樂透重新輸入</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
