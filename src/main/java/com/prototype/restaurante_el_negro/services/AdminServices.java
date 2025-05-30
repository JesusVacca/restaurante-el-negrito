package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.admin.InitialAdmin;
import com.prototype.restaurante_el_negro.enums.RolEnum;
import com.prototype.restaurante_el_negro.exceptions.BadRequestException;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.Member;
import com.prototype.restaurante_el_negro.models.Rol;
import com.prototype.restaurante_el_negro.repository.MemberRepository;
import com.prototype.restaurante_el_negro.repository.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServices {
    private final MemberRepository memberRepository;
    private final RolRepository rolRepository;
    public AdminServices(MemberRepository memberRepository, RolRepository rolRepository) {
        this.memberRepository = memberRepository;
        this.rolRepository = rolRepository;
    }

    @Transactional
    public Member createMember(Member member, List<RolEnum> roles) {
        if (this.memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("Es posible que ese correo electrónico ya este en uso");
        }
        if(roles.isEmpty()){
            throw new IllegalArgumentException("Debe contener almenos un rol");
        }
        for (RolEnum aux : roles) {
            Rol rol = this.rolRepository.findById(aux)
                    .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
            member.addRol(rol);
        }
        return memberRepository.save(member);
    }

    @Transactional
    public Member managementMember(Integer id) {
        Member member = this.memberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        for (RolEnum aux : RolEnum.values()) {
            Rol rol = this.rolRepository.findById(aux)
                    .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
            member.addRol(rol);
        }
        this.memberRepository.save(member);
        return member;
    }

    @Transactional(readOnly = true)
    public List<Member> allMembers() {
        return this.memberRepository.findAll();
    }

    @Transactional
    public Member deleteMember(Integer id) {
        Member member = this.memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        if (member.getEmail().equals(new InitialAdmin().getEmail())) {
            throw new BadRequestException("No puedes eliminar este usuario, ya que tiene privilegios de super usuario");
        }
        this.memberRepository.delete(member);
        return member;
    }
}
