package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.models.TableModel;
import com.prototype.restaurante_el_negro.repository.TableModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableModelServices {
    private final TableModelRepository tableModelRepository;
    public TableModelServices(TableModelRepository tableModelRepository) {
        this.tableModelRepository = tableModelRepository;
    }
    @Transactional
    public TableModel createTable(TableModel tableModel) {
        return tableModelRepository.save(tableModel);
    }
    @Transactional(readOnly = true)
    public List<TableModel> getAllTables() {
        return tableModelRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<TableModel> getTables() {
        return tableModelRepository.findAll().stream().filter(TableModel::isAvailable).collect(Collectors.toList());
    }
}
