package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.dto.MemberDto;
import com.prototype.restaurante_el_negro.enums.RolEnum;
import com.prototype.restaurante_el_negro.exceptions.BadRequestException;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.Member;
import com.prototype.restaurante_el_negro.services.AdminServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("dashboard/users")
public class UserController {
    private final AdminServices adminServices;

    public UserController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("members",this.adminServices.allMembers());
        return "users/listUser";
    }

    @GetMapping("/create-user")
    public String createUser(Model model) {
        model.addAttribute("roles", RolEnum.values());
        return "users/create";
    }
    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody MemberDto memberDto) {
        try {
            Member response = this.adminServices.createMember(memberDto.getMember(), memberDto.getRoles());
            return ResponseEntity
                    .status(201)
                    .body(response);

        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(401)
                    .body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            Member member = this.adminServices.deleteMember(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado");
        }catch (NotFoundException e){
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
        }
        catch (BadRequestException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/dashboard/users";
    }
}
