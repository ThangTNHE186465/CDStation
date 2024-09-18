<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin: Add CD</title>
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
        
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center">Add CD</h3>
                            <c:if test="${not empty succMsg}">
                                <P class="text-center text-success">${succMsg}</P>
                                <c:remove var="succMsg" scope = "session"/>
                            </c:if>
                                
                            <c:if test="${not empty failedMsg}">
                                <P class="text-center text-danger">${failedMsg}</P>
                                <c:remove var="failedMsg" scope = "session"/>
                            </c:if>
                            <form action="../addCds" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                                <div class="mb-3">
                                    <label for="exampleInputCDName1" class="form-label">CD Name</label>
                                    <input type="text" class="form-control" id="exampleInputCDName1" name="cdName">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputArtist1" class="form-label">Artist</label>
                                    <input type="text" class="form-control" id="exampleInputArtist1" name="artist">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPrice1" class="form-label">Price</label>
                                    <input type="number" class="form-control" id="exampleInputPrice1" name="price">
                                </div>
                                <div class="mb-3">
                                    <label for="inputState" class="form-label">CD Categories</label> 
                                    <select id="inputState" name="categories" class="form-control">
                                        <option hidden="">Choose...</option>
                                        <option value="New">New Arrivals</option>
                                        <option value="Trend">Trending</option>
                                        <option value="Sale">Sale</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="inputState" class="form-label">CD Status</label> 
                                    <select id="inputState" name="status" class="form-control">
                                        <option hidden="">Choose...</option>
                                        <option value="Active">Active</option>
                                        <option value="Inactive">Inactive</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="quantity" class="form-label">Quantity</label>
                                    <input type="number" class="form-control" id="exampleInputQuantity1" name="quantity">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleFormControlFile1" class="form-label">Upload Photo</label>
                                    <input type="file" class="form-control-file" id="exampleFormControlFile1" name="cdImg">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Add</button>
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
