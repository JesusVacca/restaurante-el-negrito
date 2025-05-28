package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.dto.OrderDto;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.Order;
import com.prototype.restaurante_el_negro.services.MenuService;
import com.prototype.restaurante_el_negro.services.OrderServices;
import com.prototype.restaurante_el_negro.services.TableModelServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard/orders/")
public class OrderController {
    private final OrderServices orderServices;
    private final TableModelServices tableModelServices;
    private final MenuService menuService;
    public OrderController(OrderServices orderServices, TableModelServices tableModelServices, MenuService menuService) {
        this.orderServices = orderServices;
        this.tableModelServices = tableModelServices;
        this.menuService = menuService;
    }


    @GetMapping
    public String orders(Model model) {
        model.addAttribute("orders",this.orderServices.allOrders());
        return "order/listOrders";
    }

    @GetMapping("create-order")
    public String createOrder(Model model) {
        model.addAttribute("tables", this.tableModelServices.getTables());
        model.addAttribute("menus", this.menuService.getMenuActive());
        return "order/create_order";
    }
    @PostMapping("create-order")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        try {
            Order response = this.orderServices.createOrder(orderDto);
            return ResponseEntity
                    .status(201)
                    .body(response);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }catch (NotFoundException e){
            return ResponseEntity
                    .status(404)
                    .body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }
}
