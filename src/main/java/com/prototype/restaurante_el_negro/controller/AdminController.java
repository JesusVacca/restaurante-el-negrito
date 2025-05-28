package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.enums.RolEnum;
import com.prototype.restaurante_el_negro.models.Member;
import com.prototype.restaurante_el_negro.services.AdminServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/management/")
public class AdminController {

    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping("create-member")
    public ResponseEntity<Object> createMember(@RequestBody Member member) {
        try{
            return ResponseEntity
                    .status(201)
                    .body(this.adminServices.createMember(member, List.of(RolEnum.WORKER)));
        }catch (Exception e){
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }
}
