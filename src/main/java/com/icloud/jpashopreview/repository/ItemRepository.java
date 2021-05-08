package com.icloud.jpashopreview.repository;

import com.icloud.jpashopreview.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(
            "SELECT i " +
            "FROM Item i " +
            "WHERE i.id = :id"
    )
    Item findOne(Long id);
}
