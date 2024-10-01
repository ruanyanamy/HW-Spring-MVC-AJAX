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
    <div class="container">
        <h2 class="text-danger text-center my-4">錯誤</h2>
        <div class="text-center">
            <p>${errormsg}</p> 
            <a href="/PlayLottery/signup" class="btn btn-primary">返回註冊頁</a>
            <a href="/PlayLottery/signin" class="btn btn-primary">返回登入頁</a>
        </div>
    </div>
</body>
</html>
