<%@page import="java.util.LinkedList"%>
<%@page import="com.systex.playlottery.model.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lottery Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        body {
            background-color: #f4f4f9; 
            font-family: 'Arial', sans-serif;
            height: 100vh; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
        }
        .form-control {
            border-radius: 25px; 
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
            transition: box-shadow 0.3s ease; 
        }
        .form-control:focus {
            box-shadow: 0 4px 15px rgba(90, 103, 216, 0.4); 
            outline: none; 
        }
        .form-container {
            padding: 20px; 
            border-radius: 25px; 
            width: 100%; 
            max-width: 400px; 
            background-color: transparent; 
        }
        h2 {
            font-weight: bold;
            color: #333;
        }
        h3 {
            color: #5a67d8;
            text-align: center; 
        }
        .alert {
            border-radius: 25px;
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
        .visitor-message {
            font-size: 1.2rem; 
            color: #666; 
            text-align: center; 
            margin-bottom: 0.5rem; 
            position: relative; 
        }


    </style>
</head>
<body>
    <div class="form-container">
        
		<div class="visitor-message">
	        <% Member member = (Member) session.getAttribute("member"); %>
	        <span>您好, <%= member.getName() %> </span>
		</div>
		<h2 class="text-center my-4">Lottery</h2>
        <form action="/playlottery/validandgenlotterynum" method="post">
            <div class="mb-3">
                <label for="group" class="form-label">您想要生成的組數</label>
                <input type="text" id="group" name="group" class="form-control" placeholder="請輸入組數" required>
            </div>

            <div class="mb-3">
                <label for="notpicknum" class="form-label">您不想要包含的號碼</label>
                <input type="text" id="notpicknum" name="notpicknum" class="form-control" placeholder="請輸入號碼">
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-primary">送出</button>
            </div>
        </form>

        <div class="text-center mt-3">
            <a href="/playlottery/signout" class="btn btn-primary">登出</a>
        </div>

        <% LinkedList<String> errors = (LinkedList<String>) request.getAttribute("error"); %>
        <% if (errors != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
                <ul class="mb-0">
                    <% for (String error : errors) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
