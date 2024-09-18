<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="entity.User" %>
<%@page import="java.sql.*" %>
<%@page import="model.UserDAOImpl" %>
<%@page import="db.DBConnect" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin: Users</title>
        <link rel="shortcut icon" href="https://cdn-icons-png.freepik.com/256/7581/7581962.png?semt=ais_hybrid" type="image/png">
        <%@include file="bootstrap.jsp"%>
    </head>
    <body style="background-color: #f7f7f7">
        <%@include file="navbar.jsp"%>

        <c:if test="${empty userObj}">
            <c:redirect url="../login.jsp"/>
        </c:if>

        <c:if test="${userObj.role ne 1}">
            <c:redirect url="../index.jsp"/>
        </c:if>

        <h3 class="text-center mt-3">Users</h3>
        <div class="container-fluid"><table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone number</th>
                        <th scope="col">Role</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
                        List<User> list = dao.getAll();
                        for(User u : list) {
                    %>    
                    <tr>
                        <td><%= u.getId() %></td>
                        <td><%= u.getName() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getPhone() %></td>
                        <td><% if (u.getRole() == 1) { %>Admin
                            <% } else {%>Customer<% } %>
                        </td>
                    </tr>
                    <%  } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
