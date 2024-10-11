<%@page import="com.systex.playlottery.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ajax Signin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        body {
            background-color: #f4f4f9; 
            font-family: 'Arial', sans-serif;
        }
        .login-container {
            max-width: 400px;
            margin: 0 auto;
            padding-top: 10%;
        }
        h2 {
            font-weight: bold;
            color: #333;
            text-align: center;
            margin-bottom: 1rem; 
        }
        .visitor-message {
            font-size: 1.2rem; 
            color: #666; 
            text-align: center; 
            margin-bottom: 0.5rem; 
            position: relative; 
        }
        .visitor-message::before, .visitor-message::after {
            content: ''; 
            position: absolute;
            top: 50%; 
            width: 40%;
            height: 1px; 
            background: rgba(0, 0, 0, 0.2); 
        }
        .visitor-message::before {
            left: 0; 
            margin-right: 10px; 
        }
        .visitor-message::after {
            right: 0; 
            margin-left: 10px; 
        }
        input.form-control {
            border-radius: 25px;
            padding: 0.75rem 1.25rem;
            border: 1px solid #ccc;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
            transition: box-shadow 0.3s ease; 
        }
        input.form-control:focus {
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2); 
            border-color: #5a67d8; 
        }
        button.btn {
            border-radius: 25px;
            padding: 0.75rem 1.25rem;
        }
        .btn-primary {
            background-color: #5a67d8;
            border: none;
        }
        .btn-primary:hover {
            background-color: #4c51bf;
        }
        .text-center a {
            color: #5a67d8;
            text-decoration: none;
        }
        .text-center a:hover {
            color: #4c51bf;
        }
        .custom-btn-login {
        	color: white;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="login-container">
        <div class="visitor-message">
            <% Member member = (Member) session.getAttribute("member"); %>
            <% if (member != null) { %>
                <span>歡迎回來, <%= member.getName() %></span>
            <% } else { %>
                <span>您好, 訪客</span>
            <% } %>
        </div>
        
        <h2>AJAX 登入</h2>
        
        <div class="mb-3">
            <input type="text" id="account" name="account" class="form-control" placeholder="帳號" required>
        </div>
        
        <div class="mb-3">
            <input type="password" id="password" name="password" class="form-control" placeholder="密碼" required>
        </div>
        
        <div class="d-grid">
            <button id="submitbtn" class="btn btn-primary btn-block">登入</button>
        </div>

        <div id="errmsg" class="text-danger mt-3"></div>

        <div class="text-center mt-3">
            <a href="/playlottery/signup">還沒有帳號嗎? 註冊一個</a>
        </div>
        <div class="text-center">
            <a href="/playlottery/signin" class="btn custom-btn-login">登入頁面</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    
    <script>
        $(document).ready(function(){
            $('#submitbtn').click(function(event){
                event.preventDefault();
                
                const account = $('#account').val();
                const password = $('#password').val();
                
                const jsonData = JSON.stringify({ account: account, password: password });
                console.log(jsonData);
                
                $.ajax({
                    type: 'POST',
                    url: '/playlottery/ajaxsignin',
                    data: jsonData,
                    contentType: 'application/json',
                    dataType: 'json', 
                    success: function(response){
                        if (response.status == 'success'){
                            window.location.href = '/playlottery/lottery';
                        } else {
                            $('#errmsg').text(response.message).show();
                        }
                    },
                    error: function(){
                        $('#errmsg').text('發生錯誤').show();
                    }
                });
            });
        });
    </script>
</body>
</html>
