package admin;

import entity.CDDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CDDaoImpl;
import db.DBConnect;
import jakarta.servlet.http.HttpSession;

public class editCds extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editCds</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editCds at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cdId = Integer.parseInt(request.getParameter("cdId"));
            String cdName = request.getParameter("cdName");
            String artist = request.getParameter("artist");
            String price = request.getParameter("price");
            String status = request.getParameter("status");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CDDetails cd = new CDDetails();
            cd.setCdId(cdId);
            cd.setCdName(cdName);
            cd.setArtist(artist);
            cd.setPrice(price);
            cd.setStatus(status);
            cd.setQuantity(quantity);

            CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
            HttpSession session = request.getSession();
            boolean f = dao.editCds(cd);
            
            if (f) {
                session.setAttribute("succMsg", "CD update successfully!");
                response.sendRedirect("admin/all_CDs.jsp");
            } else {
                session.setAttribute("failedMsg", "Something wrong on server...");
                response.sendRedirect("admin/edit_CDs.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
