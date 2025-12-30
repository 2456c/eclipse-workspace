package com.controller;

import com.dao.InventoryDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inventoryServlet")
public class InventoryServlet extends HttpServlet {
    private InventoryDao inventoryDao = new InventoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("products".equals(action)) {
            request.setAttribute("list", inventoryDao.getAllProducts());
            request.getRequestDispatcher("/inventory/product_list.jsp").forward(request, response);
        } else if ("materials".equals(action)) {
            request.setAttribute("list", inventoryDao.getAllMaterials());
            request.getRequestDispatcher("/inventory/material_list.jsp").forward(request, response);
        }
    }
}
