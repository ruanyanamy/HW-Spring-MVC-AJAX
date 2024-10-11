<%@page import="com.systex.playlottery.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Game Home</title>
</head>
<body>
    <div class="container">
        <div class="py-5 text-center">
            <h3>| Play Lottery |</h3>
        </div>
        
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <% Member member = (Member) session.getAttribute("member"); %>
                <% if (member != null) { %>
                    <h3 class="mb-3">歡迎回來, <%= member.getName() %></h3>
                    <a href="lottery" class="btn btn-primary me-2">Lottery</a>
                    <a href="signout" class="btn btn-danger">登出</a>
                <% } else { %>
                    <h3 class="mb-3">您好, 訪客</h3>
                    <a href="signup" class="btn btn-success me-2">註冊</a>
                    <a href="signin" class="btn btn-secondary">登入</a>
                <% } %>
            </div>
        </div>
    </div>

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
