package controller;

import db.DBConnect;
import entity.CDDetails;
import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CDDaoImpl;
import model.CartDAOImpl;

public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cdId = Integer.parseInt(request.getParameter("cdId"));
            int uId = Integer.parseInt(request.getParameter("uId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
            CDDetails cd = dao.getCDById(cdId);

            if (cd.getQuantity() < quantity) {
                HttpSession session = request.getSession();
                session.setAttribute("error", "Selected quantity exceeds available stock");
                response.sendRedirect("view_cd.jsp?cdId=" + cdId);
                return;
            }

            CartDAOImpl cartDao = new CartDAOImpl(DBConnect.getConn());
            Cart existingCart = cartDao.getCartByUserAndCdId(uId, cdId);

            float price = Float.parseFloat(cd.getPrice());
            if (cd.getCdCategory().equalsIgnoreCase("Sale")) {
                price = Float.parseFloat(cd.getPrice()) * 0.8f;
            }

            if (existingCart != null) {
                int newQuantity = existingCart.getQuantity() + quantity;
                if (newQuantity > cd.getQuantity()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("error", "Selected quantity exceeds available stock");
                    response.sendRedirect("view_cd.jsp?cdId=" + cdId);
                    return;
                }
                float newTotalPrice = newQuantity * price;
                cartDao.updateCartQuantity(existingCart.getcId(), newQuantity, newTotalPrice);
            } else {
                Cart c = new Cart();
                c.setCdId(cdId);
                c.setuId(uId);
                c.setCdName(cd.getCdName());
                c.setArtist(cd.getArtist());
                c.setPrice(price);
                c.setQuantity(quantity);
                c.setTotal_price(quantity * price);
                cartDao.addcart(c);
            }

            HttpSession session = request.getSession();
            response.sendRedirect("checkOut.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
