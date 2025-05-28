package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.models.TableModel;
import com.prototype.restaurante_el_negro.services.TableModelServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("dashboard/table/")
public class TableModelController {
    private final TableModelServices tableModelServices;
    public TableModelController(TableModelServices tableModelServices) {
        this.tableModelServices = tableModelServices;
    }


    @GetMapping
    public String tables(Model model) {
        model.addAttribute("tables", tableModelServices.getAllTables());
        return "tables/listTables";
    }

    @GetMapping("create-table")
    public String createTable(Model model) {
        return "tables/create_table";
    }
    @PostMapping("create-table")
    public String createTable(@RequestParam String name, @RequestParam Boolean status) {
        try {
            TableModel table = new TableModel();
            table.setName(name);
            table.setAvailable(status);
            tableModelServices.createTable(table);
            return "redirect:/dashboard/table/";
        }catch (Exception e) {
            return "redirect:/dashboard/table/create-table";
        }
    }
}
