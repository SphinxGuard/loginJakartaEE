package org.sphinx.loginprojectee.servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.exception.ConstraintViolationException;
import org.sphinx.loginprojectee.dao.UserHibernateDAOImpl;
import org.sphinx.loginprojectee.dto.UserAuthenticationDTO;
import org.sphinx.loginprojectee.dto.UserProfileDTO;
import org.sphinx.loginprojectee.exceptions.IncorrectPasswordException;
import org.sphinx.loginprojectee.exceptions.UserNotFoundException;
import org.sphinx.loginprojectee.model.Role;
import org.sphinx.loginprojectee.model.User;

@WebServlet( value = "/app/*")
public class FrontController extends HttpServlet {
    private UserHibernateDAOImpl dao;
    @Override
    public void init(){
       dao = new UserHibernateDAOImpl();
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
            case "/logout":
                logoutHandler(request, response);
                break;
            case "/register":
                view = registerHandler(request, response);
                break;
            default:
                // If no handler matches, send 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
        if(view != null) {
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
        UserProfileDTO userProfileDTO = UserProfileDTO.fromUser(user);
        request.setAttribute("userProfileDTO",userProfileDTO);

        UserAuthenticationDTO userAuthenticationDTO = UserAuthenticationDTO.fromUser(user);
        request.getSession().setAttribute("userAuthenticationDTO",userAuthenticationDTO);
        return "userProfile";

    }
    private void logoutHandler(HttpServletRequest request, HttpServletResponse response)throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/loginProjectEE");

    }
    private String registerHandler(HttpServletRequest request, HttpServletResponse response) {

        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        if (firstName == null || lastName == null || username == null ||
                password == null || email == null) {
            request.setAttribute("errorMessage", "All fields are required");
            return "registerError";
        }
        if (!password.equals(request.getParameter("confirmPassword"))) {
            request.setAttribute("errorMessage", "Passwords do not match");
            return "registerError";
        }
        User user = new User(firstName, lastName, username, password, email, Role.USER);
        UserProfileDTO userProfileDTO = UserProfileDTO.fromUser(user);
        request.setAttribute("userProfileDTO",userProfileDTO);
        UserAuthenticationDTO userAuthenticationDTO = UserAuthenticationDTO.fromUser(user);
        request.getSession().setAttribute("userAuthenticationDTO",userAuthenticationDTO);
        try {
            dao.addUser(user);
        }
        catch (ConstraintViolationException e){
            request.setAttribute("errorMessage", "Username or email already exists");
            return "registerError";
        }
        catch (Exception e){
            request.setAttribute("errorMessage", "Something went wrong");
            return "registerError";
        }
        return "userProfile";
    }

}