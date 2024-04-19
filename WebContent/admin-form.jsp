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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${admin != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${admin == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${admin != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${admin == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${admin != null}">
                            <input type="hidden" name="id" value="<c:out value='${admin.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Admin workNum</label> <input type="text" value="<c:out value='${admin.workNum}' />" class="form-control" name="workNum">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Admin passWord</label> <input type="text" value="<c:out value='${admin.passWord}' />" class="form-control" name="passWord">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Admin Name</label> <input type="text" value="<c:out value='${admin.name}' />" class="form-control" name="name" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Admin Role</label> <input type="text" value="<c:out value='${admin.role}' />" class="form-control" name="role">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Admin Token</label> <input type="text" value="<c:out value='${admin.token}' />" class="form-control" name="token">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Admin Authority</label> <input type="text" value="<c:out value='${admin.authority}' />" class="form-control" name="authority">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>