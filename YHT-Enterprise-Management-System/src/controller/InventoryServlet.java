package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.InventoryService;
import model.Material;
import java.util.List;

@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
    private InventoryService inventoryService = new InventoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<Material> list = inventoryService.getAllMaterials();
            request.setAttribute("materials", list);
            request.getRequestDispatcher("/jsp/inventory/materialList.jsp").forward(request, response);
        }
    }
}
