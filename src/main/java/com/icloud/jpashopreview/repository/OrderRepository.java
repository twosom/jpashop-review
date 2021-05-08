package com.icloud.jpashopreview.repository;

import com.icloud.jpashopreview.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 명세 기능을 사용하기 위해서 JpaSpecificationExecutor 를 추가로 상속받았다.
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {


    @Query(
            "SELECT o " +
            "FROM Order o " +
            "WHERE o.id = :id"
    )
    Order findOne(Long id);
}
