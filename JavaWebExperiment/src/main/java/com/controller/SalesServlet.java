package com.controller;

import com.dao.SalesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/salesServlet")
public class SalesServlet extends HttpServlet {
    private SalesDao salesDao = new SalesDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", salesDao.getAllOrders());
        request.getRequestDispatcher("/sales/order_list.jsp").forward(request, response);
    }
}
