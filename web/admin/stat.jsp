<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="entity.CDDetails" %>
<%@page import="java.sql.*" %>
<%@page import="model.CDDaoImpl" %>
<%@page import="db.DBConnect" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin: All CDs</title>
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

        <h3 class="text-center mt-3">Sold Quantity</h3>
        <div class="container-fluid">
            <c:if test="${not empty succMsg}">
                <P class="text-center text-success">${succMsg}</P>
                    <c:remove var="succMsg" scope = "session"/>
                </c:if>

            <c:if test="${not empty failedMsg}">
                <P class="text-center text-danger">${failedMsg}</P>
                    <c:remove var="failedMsg" scope = "session"/>
                </c:if>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Image</th>
                        <th scope="col">CD Name</th>
                        <th scope="col">Artist</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Sold</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
                        List<CDDetails> list = dao.getAllCDsBySold();
                        NumberFormat formatter = NumberFormat.getInstance();
                        for(CDDetails cd : list) {
                    %>    
                    <tr>
                        <td><%= cd.getCdId() %></td>
                        <td><img src="../cds/<%= cd.getPhoto() %>" style="width: 50px; height: 50px"/></td>
                        <td><%= cd.getCdName() %></td>
                        <td><%= cd.getArtist() %></td>
                        <td><%= formatter.format(Float.parseFloat(cd.getPrice())) %></td>
                        <td><%= cd.getQuantity() %></td>
                        <td><%= cd.getSold() %>
                        </td>
                    </tr>
                    <%  } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
