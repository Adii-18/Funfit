package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/batches")
public class BatchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BatchDAO batchDAO;

    public void init() {
        batchDAO = new BatchDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listBatches(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "insert":
                        insertBatch(request, response);
                        break;
                    case "delete":
                        deleteBatch(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateBatch(request, response);
                        break;
                    default:
                        listBatches(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBatches(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        List<Batch> listBatches = batchDAO.selectAllBatches();
        request.setAttribute("listBatches", listBatches);
        RequestDispatcher dispatcher = request.getRequestDispatcher("batch-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("batch-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Batch existingBatch = batchDAO.selectBatch(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("batch-form.jsp");
        request.setAttribute("batch", existingBatch);
        dispatcher.forward(request, response);
    }

    private void insertBatch(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String time = request.getParameter("time");
        Batch newBatch = new Batch();
        newBatch.setName(name);
        newBatch.setTime(time);
        batchDAO.addBatch(newBatch);
        response.sendRedirect("batches");
    }

    private void updateBatch(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String time = request.getParameter("time");

        Batch batch = new Batch();
        batch.setId(id);
        batch.setName(name);
        batch.setTime(time);
        batchDAO.updateBatch(batch);
        response.sendRedirect("batches");
    }

    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        batchDAO.deleteBatch(id);
        response.sendRedirect("batches");
    }
}