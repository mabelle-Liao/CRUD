<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Admin Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.foxlink.com/web/" class="navbar-brand"> Foxlink Home </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Admins</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Admins</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New User</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>WorkNum</th>
                                <th>PassWord</th>
                                <th>Name</th>
                                <th>Role</th>
                                <th>Token</th>
                                <th>Authority</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="admin" items="${listAdmin}">

                                <tr>
                                    <td>
                                        <c:out value="${admin.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${admin.workNum}" />
                                    </td>
                                    <td>
                                        <c:out value="${admin.passWord}" />
                                    </td>
                                    <td>
                                        <c:out value="${admin.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${admin.role}" />
                                    </td>
                                    <td>
                                        <c:out value="${admin.token}" />
                                    </td>
                                    <td>
                                        <c:out value="${admin.authority}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${admin.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${admin.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>