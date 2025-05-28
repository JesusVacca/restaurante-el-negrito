package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.exceptions.BadRequestException;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.Member;
import com.prototype.restaurante_el_negro.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServices {

    private final MemberRepository memberRepository;
    public AuthServices(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member login(String username, String password) {
        Member member = this.memberRepository.findByUsernamePassword(username)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        if (!member.getPassword().equals(password)) {
            throw new BadRequestException("No fue posible iniciar sesión");
        }
        return member;
    }
}
