package com.icloud.jpashopreview.repository;

import com.icloud.jpashopreview.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);

    @Query(
            "SELECT m " +
            "FROM Member m " +
            "WHERE m.id = :id "
    )
    Member findOne(Long id);

}
