<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="java.util.*" %>
<%@page import="entity.CDDetails" %>
<%@page import="java.sql.*" %>
<%@page import="model.CDDaoImpl" %>
<%@page import="db.DBConnect" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin: Edit CDs</title>
        <link rel="shortcut icon" href="https://cdn-icons-png.freepik.com/256/7581/7581962.png?semt=ais_hybrid" type="image/png">
        <%@include file="bootstrap.jsp"%>
        <script>
            function validateForm() {
                var price = document.getElementById("exampleInputPrice1").value;
                var quantity = document.getElementById("exampleInputQuantity1").value;
                if (price < 0) {
                    alert("Price cannot be less than 0");
                    return false;
                }
                if (quantity < 0) {
                    alert("Quantity cannot be less than 0");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body style="background-color: #f7f7f7">
        <%@include file="navbar.jsp"%>
        
        <c:if test="${empty userObj}">
            <c:redirect url="../login.jsp"/>
        </c:if>
        
        <c:if test="${userObj.role ne 1}">
            <c:redirect url="../index.jsp"/>
        </c:if>

        <div class="container">
            <div class="row mt-3">
                <div class="col-md-4 offset-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center">Edit CDs</h3>

                            <c:if test="${not empty failedMsg}">
                                <P class="text-center text-danger">${failedMsg}</P>
                                    <c:remove var="failedMsg" scope = "session"/>
                                </c:if>

                            <%
                                int cdId = Integer.parseInt(request.getParameter("cdId"));
                                CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
                                CDDetails cd = dao.getCDById(cdId);
                            %>

                            <form action="../editCds" method="post" onsubmit="return validateForm()">
                                <div class="mb-3">
                                    <label for="exampleInputCDId1" class="form-label">ID</label>
                                    <input type="text" class="form-control" id="exampleInputCDId1" name="cdId" value="<%= cd.getCdId() %>" readonly="">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputCDName1" class="form-label">CD Name</label>
                                    <input type="text" class="form-control" id="exampleInputCDName1" name="cdName" value="<%= cd.getCdName() %>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputArtist1" class="form-label">Artist</label>
                                    <input type="text" class="form-control" id="exampleInputArtist1" name="artist" value="<%= cd.getArtist() %>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPrice1" class="form-label">Price</label>
                                    <input type="number" class="form-control" id="exampleInputPrice1" name="price" value="<%= cd.getPrice() %>">
                                </div>
                                <div class="mb-3">
                                    <label for="inputState" class="form-label">CD Status</label> 
                                    <select id="inputState" name="status" class="form-control">
                                        <%if ("Active".equals(cd.getStatus())) {%>
                                        <option value="Active">Active</option>
                                        <option value="Inactive">Inactive</option>
                                        <%} else {%>
                                        <option value="Inactive">Inactive</option>
                                        <option value="Active">Active</option>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="quantity" class="form-label">Quantity</label>
                                    <input type="number" class="form-control" id="exampleInputQuantity1" name="quantity" value="<%= cd.getQuantity() %>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleFormControlFile1" class="form-label">Change Photo</label><br>
                                    <img src="../cds/<%= cd.getPhoto() %>" style="width: 75px; height: 75px"/>
                                    <input type="file" class="form-control-file" id="exampleFormControlFile1" name="cdImg" value="<%= cd.getPhoto() %>">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
