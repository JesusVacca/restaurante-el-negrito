package com.prototype.restaurante_el_negro.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Integer mesa_id;
    private Integer menu_id;
    private List<DetailDto> details = new ArrayList<>();
    public OrderDto() {}


    public OrderDto(Integer mesa_id, Integer menu_id, List<DetailDto> details) {
        this.mesa_id = mesa_id;
        this.menu_id = menu_id;
        this.details = details;
    }

    public List<DetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<DetailDto> details) {
        this.details = details;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public Integer getMesa_id() {
        return mesa_id;
    }

    public void setMesa_id(Integer mesa_id) {
        this.mesa_id = mesa_id;
    }


}
