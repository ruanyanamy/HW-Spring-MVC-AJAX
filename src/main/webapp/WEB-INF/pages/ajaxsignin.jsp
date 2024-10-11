<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ajax Signin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center my-4">| AJAX 登入 |</h2>
                
                <div class="mb-3">
                    <label for="account" class="form-label">帳號</label>
                    <input type="text" id="account" name="account" class="form-control" required>
                </div>
                
                <div class="mb-3">
                    <label for="password" class="form-label">密碼</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                
                <div class="d-grid">
                    <button id="submitbtn" class="btn btn-primary">登入</button>
                </div>

                <div id="errmsg" class="text-danger mt-3"></div>
                
                <div class="text-center mt-3">
                    <a href="/playlottery/signin" class="btn btn-secondary">登入頁面</a>
                </div>
            </div>
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
