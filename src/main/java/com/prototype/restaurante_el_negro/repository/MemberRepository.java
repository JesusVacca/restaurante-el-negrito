package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    public boolean existsByEmail(String email);
    @Query("SELECT m FROM Member m WHERE m.email = :username")
    public Optional<Member> findByUsernamePassword(String username);
}
