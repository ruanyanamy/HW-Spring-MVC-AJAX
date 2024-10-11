<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>註冊成功</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        body {
            background-color: #f4f4f9; 
            font-family: 'Arial', sans-serif;
            height: 100vh; 
            display: flex;
            align-items: center; 
            justify-content: center;
        }
        h2 {
            font-weight: bold;
            color: #333;
            margin-bottom: 20px; 
        }
        .btn-primary {
            background-color: #5a67d8;
            border: none;
            border-radius: 25px; 
            padding: 0.75rem 1.25rem; 
        }
        .btn-primary:hover {
            background-color: #4c51bf;
        }
        p {
            font-size: 1.1rem;
            color: #555;
            margin-bottom: 20px; 
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <div class="row">
            <div class="col-md-12">
                <h2>註冊成功</h2>
                <p>您已成功註冊！</p>
                <a href="/playlottery/signin" class="btn btn-primary">登入</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
