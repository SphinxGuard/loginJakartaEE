package org.sphinx.loginprojectee.servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.sphinx.loginprojectee.dao.UserHibernetDAOImpl;
import org.sphinx.loginprojectee.exceptions.IncorrectPasswordException;
import org.sphinx.loginprojectee.exceptions.UserNotFoundException;
import org.sphinx.loginprojectee.model.User;

@WebServlet( value = "/login")
public class LoginProjectServlet extends HttpServlet {
    private UserHibernetDAOImpl dao;
    @Override
    public void init(){
       dao = new UserHibernetDAOImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)  {


    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            user = dao.findUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        }
        catch (UserNotFoundException | IncorrectPasswordException e) {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/loginError.jsp").forward(request, response);
        }
        catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong");
            request.getRequestDispatcher("/WEB-INF/loginError.jsp").forward(request, response);
        }

        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/userProfile.jsp").forward(request, response);

    }


}