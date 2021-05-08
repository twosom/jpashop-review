package com.icloud.jpashopreview.repository;

import com.icloud.jpashopreview.domain.Order;
import com.icloud.jpashopreview.domain.OrderSearch;
import com.icloud.jpashopreview.domain.OrderStatus;
import com.icloud.jpashopreview.domain.QOrder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.icloud.jpashopreview.domain.QOrder.*;

@Repository
public class OrderCustomRepository {

    private final JPAQueryFactory queryFactory;

    public OrderCustomRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<Order> findOrders(OrderSearch orderSearch) {
        return queryFactory
                .select(order)
                .from(order)
                .where(nameLike(orderSearch.getMemberName()), statusEq(orderSearch.getOrderStatus()))
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus orderStatus) {
        return orderStatus != null ? order.status.eq(orderStatus)  :null;
    }

    private BooleanExpression nameLike(String memberName) {
        return StringUtils.hasText(memberName) ? order.member.name.like('%' + memberName + '%') : null;
    }
}
