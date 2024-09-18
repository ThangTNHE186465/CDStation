package admin;

import db.DBConnect;
import entity.CDDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CDDaoImpl;
import model.OrderDAO;

public class deleteCDs extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteCDs</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteCDs at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cdId = Integer.parseInt(request.getParameter("cdId"));
            CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
            CDDetails cd = dao.getCDById(cdId);
            HttpSession session = request.getSession();
            boolean f = dao.deleteCds(cdId);
            OrderDAO dao2 = new OrderDAO(DBConnect.getConn());
            if (f) {
                dao2.delete(cd.getCdName(), cd.getArtist());
                session.setAttribute("succMsg", "CD delete successfully!");
                response.sendRedirect("admin/all_CDs.jsp");
            } else {
                session.setAttribute("failedMsg", "Something wrong on server...");
                response.sendRedirect("admin/all_CDs.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
