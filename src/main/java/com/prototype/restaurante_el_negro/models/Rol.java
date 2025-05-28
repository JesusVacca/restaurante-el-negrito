package com.prototype.restaurante_el_negro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prototype.restaurante_el_negro.enums.RolEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @Enumerated(EnumType.STRING)
    private RolEnum role;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Member> members = new ArrayList<>();

    public Rol() {}

    public Rol(RolEnum role, List<Member> members) {
        this.role = role;
        this.members = members;
    }



    public RolEnum getRole() {
        return role;
    }

    public void setRole(RolEnum role) {
        this.role = role;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        if(!this.members.contains(member)) {
            this.members.add(member);
        }
    }
}
