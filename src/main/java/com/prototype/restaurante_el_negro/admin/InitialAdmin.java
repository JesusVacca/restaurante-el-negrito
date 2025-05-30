package com.prototype.restaurante_el_negro.admin;

import com.prototype.restaurante_el_negro.enums.RolEnum;
import java.util.List;

public class InitialAdmin {
    public Integer getId() {
        return null;
    }

    public String getName() {
        return "Luis Felipe Oliveros";
    }

    public String getEmail() {
        return "felipe@gmail.com";
    }

    public String getPhone() {
        return "3117984622";
    }

    public String getAddress() {
        return "Cra 21 #1A-53";
    }

    public String getPassword() {
        return "123456";
    }

    public List<RolEnum> getRoles() {
        return List.of(RolEnum.values());
    }
}
