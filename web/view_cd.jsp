<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="entity.CDDetails" %>
<%@page import="java.sql.*" %>
<%@page import="model.CDDaoImpl" %>
<%@page import="db.DBConnect" %>
<%@page import="entity.*" %>
<%@page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CD Station: CD Details</title>
        <link rel="shortcut icon" href="https://cdn-icons-png.freepik.com/256/7581/7581962.png?semt=ais_hybrid" type="image/png">
        <%@include file="component/bootstrap.jsp"%>
        <script>
            function updateQuantity(change) {
                var quantityInput = document.getElementById('quantity');
                var currentQuantity = parseInt(quantityInput.value);
                var newQuantity = currentQuantity + change;

                if (newQuantity < 1) {
                    newQuantity = 1;  // Ensure quantity does not go below 1
                }

                quantityInput.value = newQuantity;
            }

            function validateQuantity(maxQuantity) {
                var quantityInput = document.getElementById('quantity');
                var selectedQuantity = parseInt(quantityInput.value);
                if (selectedQuantity <= 0) {
                    alert('Select quantity > 0 to add to cart');
                    return false;
                }
                if (selectedQuantity > maxQuantity) {
                    alert('Selected quantity exceeds available stock');
                    return false;
                }
                return true;
            }
        </script>

        <style type="text/css">
            .crd-ho:hover {
                background-color: #fcf7f7;
            }

            .card-body {
                height: 265px
            }
        </style>
    </head>
    <body style="background-color: #f7f7f7">
        <%@include file="component/navbar.jsp"%>
        <div class="container">
            <div class="row p-5">
                <%
                    User u = (User) session.getAttribute("userObj");
                    int id = Integer.parseInt(request.getParameter("cdId"));
                    CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
                    CDDetails cd = dao.getCDById(id);
                    NumberFormat formatter = NumberFormat.getInstance();
                %>  
                <div class="col-md-6 text-center p-5 border bg-light">
                    <img src="cds/<%= cd.getPhoto() %>" style="width: 60%"/><br>
                </div>

                <div class="col-md-6 p-5 border bg-light">
                    <h3 class="mt-3"><span><%= cd.getArtist() %></span><span>- <%= cd.getCdName() %></span></h3>
                    <h4></h4>
                    <% if ("Sale".equals(cd.getCdCategory())) { %>
                    <h5><span style="text-decoration: line-through"><%= formatter.format(Float.parseFloat(cd.getPrice())) %><i class="fa-solid fa-dong-sign"></i></span>  
                        <span><%= formatter.format(Float.parseFloat(cd.getPrice()) * 0.8) %><i class="fa-solid fa-dong-sign"></i></span></h5>
                            <% } else { %>
                    <h5><span><%= formatter.format(Float.parseFloat(cd.getPrice())) %><i class="fa-solid fa-dong-sign"></i></span></h5>
                            <% } %>
                            <% if ("Inactive".equals(cd.getStatus())) { %>
                    <h5>Sold out</h5>
                    <a href="index.jsp" class="btn btn-outline-danger btn-sm">Continue Shopping</a>
                    <% } else { %>
                    <h5><span>Stock Remaining: </span><span><%= cd.getQuantity() %></span></h5>
                    <form action="cart" method="get" onsubmit="return validateQuantity(<%= cd.getQuantity() %>);">
                        <input type="hidden" name="cdId" value="<%= cd.getCdId() %>">
                        <input type="hidden" name="uId" value="<%= u.getId() %>">
                        <div class="quantity-container" style="display: flex; align-items: center;">
                            <button type="button" class="btn btn-outline-danger btn-sm" id="minus" onclick="updateQuantity(-1)">-</button>
                            <input type="text" id="quantity" name="quantity" value="1" min="1" style="width: 50px; height: 30px; text-align: center;">
                            <button type="button" class="btn btn-outline-danger btn-sm" id="plus" onclick="updateQuantity(1)">+</button>
                        </div>
                        <button type="submit" class="btn btn-outline-danger btn-sm mt-2"><i class="fa-solid fa-cart-plus"></i> Add To Cart</button>
                    </form>
                    <% } %>                    
                </div>
            </div>

            <h5 class="text-center" style="margin-top: -30px;">RELATED PRODUCTS</h5>
            <div class="row">
                <%
                    List<CDDetails> list1 = dao.getRelatedCDs(cd);
                    for(CDDetails c : list1) {
                %>  
                <div class="col-md-3">
                    <a href="view_cd.jsp?cdId=<%= c.getCdId() %>" style="text-decoration: none; color: black">
                        <div class="card crd-ho">
                            <div class="card-body text-center">
                                <img src="cds/<%= c.getPhoto() %>" style="width: 60%" class="img-thumblin"><br>
                                <span><%= c.getArtist() %></span> 
                                <span>- <%= c.getCdName() %></span>
                                <% if ("Sale".equalsIgnoreCase(cd.getCdCategory())) {%>
                                <p><span style="text-decoration: line-through"><%= formatter.format(Float.parseFloat(cd.getPrice())) %><i class="fa-solid fa-dong-sign"></i></span>  
                                    <span><%= formatter.format(Float.parseFloat(cd.getPrice()) * 0.8) %><i class="fa-solid fa-dong-sign"></i></span></p>
                                        <% } else { %>
                                <p><%= formatter.format(Float.parseFloat(cd.getPrice())) %><i class="fa-solid fa-dong-sign"></i></p>
                                    <% } %>
                            </div>
                        </div>
                    </a>
                </div>
                <%  }  %>
            </div>
        </div>

        <%@include file="component/footer.jsp"%>
    </body>
</html>
