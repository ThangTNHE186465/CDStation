package controller;

import db.DBConnect;
import entity.CDDetails;
import entity.Cart;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import model.CDDaoImpl;
import model.CartDAOImpl;
import model.OrderDAO;

public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("userName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String payment = request.getParameter("payment");

            HttpSession session = request.getSession();
            CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
            CDDaoImpl cdDao = new CDDaoImpl(DBConnect.getConn());
            List<Cart> list = dao.getCdByUser(id);
            if (list.isEmpty()) {
                session.setAttribute("failedMsg", "Please add item");
                response.sendRedirect("checkOut.jsp");
            } else {
                OrderDAO dao2 = new OrderDAO(DBConnect.getConn());
                Order o = null;
                ArrayList<Order> orders = new ArrayList<>();
                Random rand = new Random();
                String orderId = "ORD-" + rand.nextInt(1000);
                Date currentDate = new Date();
                for (Cart c : list) {
                    CDDetails cd = cdDao.getCDById(c.getCdId());
                    float p = Float.parseFloat(cd.getPrice());
                    if (cd.getCdCategory().equalsIgnoreCase("Sale")) {
                        p = Float.parseFloat(cd.getPrice()) * 0.8f;
                    }
                    o = new Order();
                    o.setOrderId(orderId);
                    o.setuId(id);
                    o.setAddress(address);
                    o.setCdName(c.getCdName());
                    o.setArtist(c.getArtist());
                    o.setQuantity(c.getQuantity());
                    o.setPrice(p * c.getQuantity() + "");
                    o.setPayment(payment);
                    o.setDate(currentDate);
                    orders.add(o);

                    // Update product quantity and status
                    int newQuantity = cd.getQuantity() - c.getQuantity();
                    cdDao.updateCDQuantity(cd.getCdId(), newQuantity);
                    if (newQuantity <= 0) {
                        cdDao.updateCDStatus(cd.getCdId(), "Inactive");
                    }
                    cdDao.updateSold(cd, cd.getQuantity() - newQuantity);
                }
                if ("none".equals(payment)) {
                    session.setAttribute("failedMsg", "Please choose payment method");
                    response.sendRedirect("checkOut.jsp");
                } else {
                    boolean f = dao2.saveOrder(orders);
                    if (f) {
                        dao.deleteCartByUserId(id);
                        response.sendRedirect("order_success.jsp");
                    } else {
                        session.setAttribute("failedMsg", "Something wrong on server...");
                        response.sendRedirect("checkOut.jsp");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
