package com.prototype.restaurante_el_negro.dto;

import com.prototype.restaurante_el_negro.enums.RolEnum;
import com.prototype.restaurante_el_negro.models.Member;

import java.util.List;

public class MemberDto {
    private Member member;
    List<RolEnum> roles;

    public MemberDto() {}
    public MemberDto(Member member, List<RolEnum> roles) {
        this.member = member;
        this.roles = roles;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<RolEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolEnum> roles) {
        this.roles = roles;
    }
}
