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
        User user;
        try {
            user = dao.findUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IncorrectPasswordException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/userProfile.jsp").forward(request, response);

    }


}