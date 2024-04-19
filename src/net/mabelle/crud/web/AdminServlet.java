package net.mabelle.crud.web;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mabelle.crud.model.Admin;
import net.mabelle.crud.dao.AdminDAO;

@WebServlet("/")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;

    public void init() {
    	adminDAO = new AdminDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        doGet(request, response);
    	    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        String action = request.getServletPath();

    	        try {
    	            switch (action) {
    	                case "/new":
    	                    showNewForm(request, response);
    	                    break;
    	                case "/insert":
    	                    insertUser(request, response);
    	                    break;
    	                case "/delete":
    	                    deleteUser(request, response);
    	                    break;
    	                case "/edit":
    	                    showEditForm(request, response);
    	                    break;
    	                case "/update":
    	                    updateUser(request, response);
    	                    break;
    	                default:
    	                	listAdmin(request, response);
    	                    break;
    	            }
    	        } catch (SQLException ex) {
    	            throw new ServletException(ex);
    	        }
    	    }
    private void listAdmin(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < Admin > listAdmin = adminDAO.selectAllUsers();
    	        request.setAttribute("listAdmin", listAdmin);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-list.jsp");
    	        dispatcher.forward(request, response);
    	    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-form.jsp");
    	        dispatcher.forward(request, response);
    	    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        Admin existingUser = adminDAO.selectUser(id);
    	        System.out.println(existingUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-form.jsp");
    	        request.setAttribute("admin", existingUser);
    	        dispatcher.forward(request, response);

    	    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String workNum = request.getParameter("workNum");
    	        String passWord = request.getParameter("passWord");
    	        String name = new String(request.getParameter("name").getBytes("ISO8859_1"),"UTF-8");
    	        String role = new String(request.getParameter("role").getBytes("ISO8859_1"),"UTF-8");
    	        String token = request.getParameter("token");
    	        String authority = new String(request.getParameter("authority").getBytes("ISO8859_1"),"UTF-8");
    	        Admin newUser = new Admin(workNum, passWord, name, role, token, authority,"");
    	        adminDAO.insertUser(newUser);
    	        response.sendRedirect("list");
    	    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        String workNum = request.getParameter("workNum");
    	        String passWord = request.getParameter("passWord");
    	        String name = new String(request.getParameter("name").getBytes("ISO8859_1"),"UTF-8");
    	        String role = new String(request.getParameter("role").getBytes("ISO8859_1"),"UTF-8");
    	        String token = request.getParameter("token");
    	        String authority = new String(request.getParameter("authority").getBytes("ISO8859_1"),"UTF-8");

    	        Admin adminrecord = new Admin(id, workNum, passWord, name, role, token, authority,"");
    	        adminDAO.updateUser(adminrecord);
    	        response.sendRedirect("list");
    	    }

    	    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        adminDAO.deleteUser(id);
    	        response.sendRedirect("list");

    	    }

}
