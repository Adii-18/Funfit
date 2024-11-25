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

@WebServlet("/participants")
public class ParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParticipantDAO participantDAO;

    public void init() {
        participantDAO = new ParticipantDAO();
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
                listParticipants(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "insert":
                        insertParticipant(request, response);
                        break;
                    case "delete":
                        deleteParticipant(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateParticipant(request, response);
                        break;
                    default:
                        listParticipants(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listParticipants(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        List<Participant> listParticipants = participantDAO.selectAllParticipants();
        request.setAttribute("listParticipants", listParticipants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("participant-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("participant-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant existingParticipant = participantDAO.selectParticipant(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("participant-form.jsp");
        request.setAttribute("participant", existingParticipant);
        dispatcher.forward(request, response);
    }

    private void insertParticipant(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Participant newParticipant = new Participant();
        newParticipant.setName(name);
        newParticipant.setEmail(email);
        participantDAO.addParticipant(newParticipant);
        response.sendRedirect("participants");
    }

    private void updateParticipant(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Participant participant = new Participant();
        participant.setId(id);
        participant.setName(name);
        participant.setEmail(email);
        participantDAO.updateParticipant(participant);
        response.sendRedirect("participants");
    }

    private void deleteParticipant(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        participantDAO.deleteParticipant(id);
        response.sendRedirect("participants");
    }
}