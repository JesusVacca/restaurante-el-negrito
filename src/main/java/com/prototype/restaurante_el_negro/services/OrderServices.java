package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.dto.DetailDto;
import com.prototype.restaurante_el_negro.dto.OrderDto;
import com.prototype.restaurante_el_negro.dto.ResponseOrderDto;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.*;
import com.prototype.restaurante_el_negro.repository.MenuRepository;
import com.prototype.restaurante_el_negro.repository.OrderRepository;
import com.prototype.restaurante_el_negro.repository.RecipeRepository;
import com.prototype.restaurante_el_negro.repository.TableModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServices {
    private final OrderRepository orderRepository;
    private final RecipeRepository recipeRepository;
    private final TableModelRepository tableModelRepository;
    private final MenuRepository menuRepository;
    public OrderServices(OrderRepository orderRepository, RecipeRepository recipeRepository, TableModelRepository tableModelRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.recipeRepository = recipeRepository;
        this.tableModelRepository = tableModelRepository;
        this.menuRepository = menuRepository;
    }


    @Transactional
    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        Integer total = 0;
        if(orderDto.getDetails() == null || orderDto.getDetails().isEmpty()) {
            throw new IllegalArgumentException("Para crear una order necesitas al menos un platillo");
        }
        TableModel table = this.tableModelRepository
                .findById(orderDto.getMesa_id())
                .orElseThrow(() -> new NotFoundException("Mesa no encontrado"));
        if(!table.isAvailable()){
            throw new IllegalArgumentException("La mesa no esta disponible");
        }
        Menu menu = this.menuRepository.findById(orderDto.getMenu_id())
                .orElseThrow(() -> new NotFoundException("Menu no encontrado"));

        for(DetailDto detailDto : orderDto.getDetails()) {
            Recipe recipe = this.recipeRepository.findById(detailDto.getRecipe_id())
                    .orElseThrow(() -> new NotFoundException("Recipe no encontrado"));
            if(!recipe.isActive()){
                throw new IllegalArgumentException("Este platillo no esta disponible");
            }
            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setOrder(order);
            orderDetails.setMenu(menu);
            orderDetails.setQuantity(detailDto.getQuantity());
            orderDetails.setSubTotal(recipe.getPrice() * detailDto.getQuantity());
            total += orderDetails.getSubTotal();
            order.getDetails().add(orderDetails);

        }
        order.setTable(table);
        table.getOrder().add(order);
        table.setAvailable(false);
        order.setTotal(total);
        return this.orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<ResponseOrderDto> allOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(ResponseOrderDto::new)
                .toList();
    }

}
