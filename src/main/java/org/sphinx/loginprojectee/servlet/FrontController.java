package org.sphinx.loginprojectee.servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.sphinx.loginprojectee.dao.UserHibernetDAOImpl;
import org.sphinx.loginprojectee.dto.UserDTO;
import org.sphinx.loginprojectee.exceptions.IncorrectPasswordException;
import org.sphinx.loginprojectee.exceptions.UserNotFoundException;
import org.sphinx.loginprojectee.model.User;

@WebServlet( value = "/app/*")
public class FrontController extends HttpServlet {
    private UserHibernetDAOImpl dao;
    @Override
    public void init(){
       dao = new UserHibernetDAOImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); //for the sake of test the default exceptions added btw
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/login"; // default action
        }
        String view = null;

        switch(path) {
            case "/login":
                view = loginHandler(request, response);
                break;
            default:
                // If no handler matches, send 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
        if(view != null){
            // Apply prefix/suffix
            String jspPath = "/WEB-INF/" + view + ".jsp";
            request.getRequestDispatcher(jspPath).forward(request, response);
        }


    }

    private String loginHandler(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            // No login attempt yet — just show login page
            return "loginNull"; // forward to loginNull.jsp
        }
        try {
            user = dao.findUserByUsernameAndPassword(username, password);
        }
        catch (UserNotFoundException | IncorrectPasswordException e) {
            request.setAttribute("errorMessage", "Invalid username or password");
            return "loginError";

        }
        catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong");
            return "loginError";
        }
        UserDTO userDTO = UserDTO.fromUser(user);
        request.setAttribute("userDTO",userDTO);
        return "userProfile";

    }


}