package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.enums.OrderStatus;
import com.prototype.restaurante_el_negro.models.Member;
import com.prototype.restaurante_el_negro.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/")
public class DashboardController {
    private final ProductBaseServices productBaseServices;
    private final RecipeServices recipeServices;
    private final OrderServices orderServices;
    private final AdminServices adminServices;

    public DashboardController(ProductBaseServices productBaseServices, RecipeServices recipeServices, OrderServices orderServices, AdminServices adminServices) {
        this.productBaseServices = productBaseServices;
        this.recipeServices = recipeServices;
        this.orderServices = orderServices;
        this.adminServices = adminServices;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("products", this.productBaseServices.getAllProductBases().size());
        model.addAttribute("recipes", this.recipeServices.getAllRecipes().size());
        model.addAttribute("member",this.adminServices.allMembers().size());
        model.addAttribute("orders",this.orderServices.allOrders().stream().filter(order-> order.getStatus().equals(OrderStatus.PAGADO)).toList().size());
        return "previous";
    }
}
