package admin;

import db.DBConnect;
import entity.CDDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import model.CDDaoImpl;

@MultipartConfig
public class addCds extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addCds</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addCds at " + request.getContextPath() + "</h1>");
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
            String cdName = request.getParameter("cdName");
            String artist = request.getParameter("artist");
            String price = request.getParameter("price");
            String categories = request.getParameter("categories");
            String status = request.getParameter("status");
            Part part = request.getPart("cdImg");
            String fileName = part.getSubmittedFileName();
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CDDetails cd = new CDDetails(cdName, artist, price, categories, status, fileName, quantity, 0);

            CDDaoImpl dao = new CDDaoImpl(DBConnect.getConn());
            HttpSession session = request.getSession();
            boolean f = dao.addCds(cd);
            if (f) {
                String path = getServletContext().getRealPath("") + "cds";
                File file = new File(path);
                part.write(path + File.separator + fileName);
                session.setAttribute("succMsg", "CD add successfully!");
                response.sendRedirect("admin/add_CDs.jsp");
            } else {
                session.setAttribute("failedMsg", "Something wrong on server...");
                response.sendRedirect("admin/add_CDs.jsp");
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
