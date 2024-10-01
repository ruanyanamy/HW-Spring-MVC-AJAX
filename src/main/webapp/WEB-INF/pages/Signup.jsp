<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Signup Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center my-4">| Signup |</h2>
                
                <form:form modelAttribute="signup" action="/PlayLottery/signup" method="post">
                    <div class="mb-3">
                        <form:label path="name" class="form-label">姓名</form:label>
                        <form:input path="name" class="form-control"/>
                    </div>
                    
                    <div class="mb-3">
                        <form:label path="account" class="form-label">帳號</form:label>
                        <form:input path="account" class="form-control"/>
                    </div>
                    
                    <div class="mb-3">
                        <form:label path="password" class="form-label">密碼</form:label>
                        <form:input path="password" type="password" class="form-control"/>
                    </div>
                    
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">註冊</button>
                    </div>
                </form:form>
                
                <div class="text-center mt-3">
                    <a href="/PlayLottery/signin" class="btn btn-primary"
                    style="background-color: #ADADAD; color: white;">登入</a>
                </div>

            </div>
        </div>
    </div>

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
