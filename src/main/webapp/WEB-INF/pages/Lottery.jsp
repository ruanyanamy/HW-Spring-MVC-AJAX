<%@page import="java.util.LinkedList"%>
<%@page import="com.systex.PlayLottery.model.Member"%>
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
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h2 class="text-center my-4">| Lottery |</h2>

                <% Member member = (Member) session.getAttribute("member"); %>
                <h3 class="text-center">很高興為 <%= member.getName()%> 服務</h3>

                <form action="/PlayLottery/validandgenlotterynum" method="post">
                    <div class="mb-3">
                        <label for="group" class="form-label">您想要生成的組數</label>
                        <input type="text" id="group" name="group" class="form-control" placeholder="請輸入組數">
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
				    <a href="index.jsp" class="btn btn-primary">Go Home</a>
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
        </div>
    </div>

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
