package com.controller;

import com.dao.PurchaseDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/procurementServlet")
public class ProcurementServlet extends HttpServlet {
    private PurchaseDao purchaseDao = new PurchaseDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", purchaseDao.getAllPurchases());
        request.getRequestDispatcher("/procurement/purchase_list.jsp").forward(request, response);
    }
}
