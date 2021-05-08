package com.icloud.jpashopreview.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class OrderSpec {

    public static Specification<Order> memberNameLike(final String memberName) {
        return (o, cq, cb) -> {
            if (StringUtils.isEmpty(memberName)) {
                return null;
            }
            Join<Order, Member> m = o.join("member", JoinType.INNER);
            return cb.like(m.get("name"), "%" + memberName + "%");
        };
    }

    public static Specification<Order> orderStatusEq(final OrderStatus orderStatus) {
        return (o, cq, cb) -> {
            if (orderStatus == null) {
                return null;
            }

            return cb.equal(o.get("status"), orderStatus);
        };
    }

}
