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

        <h3 class="text-center mt-3">All CDs</h3>
        <div class="container-fluid">
            <c:if test="${not empty succMsg}">
                <P class="text-center text-success">${succMsg}</P>
                    <c:remove var="succMsg" scope = "session"/>
                </c:if>

            <c:if test="${not empty failedMsg}">
                <P class="text-center text-danger">${failedMsg}</P>
                    <c:remove var="failedMsg" scope = "session"/>
                </c:if>
            <a href="add_CDs.jsp" class="btn btn-outline-danger btn-sm mb-1"><i class="fa-solid fa-circle-plus"></i> Add product</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Image</th>
                        <th scope="col">CD Name</th>
                        <th scope="col">Artist</th>
                        <th scope="col">Price</th>
                        <th scope="col">Category</th>
                        <th scope="col">Status</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
                        List<CDDetails> list = dao.getAllCDs();
                        NumberFormat formatter = NumberFormat.getInstance();
                        for(CDDetails cd : list) {
                    %>    
                    <tr>
                        <td><%= cd.getCdId() %></td>
                        <td><img src="../cds/<%= cd.getPhoto() %>" style="width: 50px; height: 50px"/></td>
                        <td><%= cd.getCdName() %></td>
                        <td><%= cd.getArtist() %></td>
                        <td><%= formatter.format(Float.parseFloat(cd.getPrice())) %></td>
                        <td><%= cd.getCdCategory() %></td>
                        <td><%= cd.getStatus() %></td>
                        <td><%= cd.getQuantity() %></td>
                        <td>
                            <a href="edit_CDs.jsp?cdId=<%= cd.getCdId() %>" class="btn btn-sm btn-primary"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a data-bs-toggle="modal" data-bs-target="#exampleModal<%= cd.getCdId() %>" class="btn btn-sm btn-danger"><i class="fa-solid fa-trash"></i></a>
                            <div class="modal fade" id="exampleModal<%= cd.getCdId() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <div class="text-center">
                                                <h4>Are you sure you want to delete this CD?</h4>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <a href="../deleteCDs?cdId=<%= cd.getCdId() %>" class="btn btn-primary">Delete</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <%  } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
