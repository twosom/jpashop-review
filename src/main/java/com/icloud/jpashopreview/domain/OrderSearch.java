package com.icloud.jpashopreview.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static com.icloud.jpashopreview.domain.OrderSpec.memberNameLike;
import static com.icloud.jpashopreview.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specification.*;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Order> toSpecification() {
        return where(memberNameLike(memberName))
                .and(orderStatusEq(orderStatus));
    }
}
