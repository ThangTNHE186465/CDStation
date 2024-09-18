package controller;

import db.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartDAOImpl;

public class RemoveCDCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RemoveCDCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveCDCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cdId = Integer.parseInt(request.getParameter("cdId"));
        int uId = Integer.parseInt(request.getParameter("uId"));
        int cId = Integer.parseInt(request.getParameter("cId"));
        CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
        boolean f = dao.removeCD(cdId, uId, cId);
        HttpSession session = request.getSession();
        if (f) {
            session.setAttribute("succMsg", "CD Removed from Cart");
            response.sendRedirect("checkOut.jsp");
        } else {
            session.setAttribute("failedMsg", "Something wrong on server...");
            response.sendRedirect("checkOut.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
