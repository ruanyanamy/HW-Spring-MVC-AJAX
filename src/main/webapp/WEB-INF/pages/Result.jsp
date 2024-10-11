<%@page import="com.systex.playlottery.model.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Result Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        body {
            background-color: #f4f4f9; 
            font-family: 'Arial', sans-serif;
        }
        h2 {
            font-weight: bold;
            color: #333;
        }
        h4 {
            color: #5a67d8;
        }
        h5 {
            color: #333;
        }
        .table {
            border-radius: 25px; 
            overflow: hidden; 
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); 
        }
        .table th, .table td {
            vertical-align: middle; 
            padding: 15px; 
        }
        .table th {
            background-color: #5a67d8; 
            color: white; 
            font-weight: bold; 
        }
        .table tbody tr:nth-child(even) {
            background-color: #f9f9f9; 
        }
        .table tbody tr:hover {
            background-color: #e2e8f0;
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
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h2 class="text-center my-4">Result</h2>

                <% Member member = (Member) session.getAttribute("member"); 
                    ArrayList<Integer> result = (ArrayList<Integer>) request.getAttribute("pickLotteryNumber");
                    int group = (int) request.getAttribute("group");
                %>
                
                <h4 class="text-center">為 <%= member.getName()%> 生成 <%= group %> 組樂透號碼</h4>
                <h5 class="text-center mb-4">您排除的樂透號碼: <%= request.getAttribute("excludes") %></h5>

                <table class="table table-striped text-center">
                    <thead>
                        <tr>
                            <th>組數</th>
                            <th>樂透號碼</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        int index = 0;
                        for (int i = 1; i <= group; i++) { %>
                        <tr>
                            <td>第 <%= i %> 組</td>
                            <td>
                                <% for (int j = 0; j < 6; j++) { 
                                    out.print(result.get(index) + (j < 5 ? ", " : ""));       
                                    index++;
                                } %> 
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

                <div class="text-center mt-3">
                    <a href="/playlottery/lottery" class="btn btn-primary">重新產生樂透號碼</a>
                </div>

            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
