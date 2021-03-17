package com.icloud.jpashopreview.service;

import com.icloud.jpashopreview.domain.Address;
import com.icloud.jpashopreview.domain.Member;
import com.icloud.jpashopreview.domain.Order;
import com.icloud.jpashopreview.domain.OrderStatus;
import com.icloud.jpashopreview.domain.item.Item;
import com.icloud.jpashopreview.domain.item.Movie;
import com.icloud.jpashopreview.exception.NotEnoughStockException;
import com.icloud.jpashopreview.repository.OrderRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Movie movie = createMovie("TENET", 100_000, 20_000);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), movie.getId(), orderCount);

        //then
        Order findOrder = orderRepository.findOne(orderId);
        Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(orderCount * movie.getPrice());
        Assertions.assertThat(movie.getStockQuantity()).isEqualTo(100_000 - orderCount);

    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createMovie("TENET", 10, 10000);
        int orderCount = 11;


        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        });
    }

    @Test
    void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createMovie("TENET", 10, 10000);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order findOrder = orderRepository.findOne(orderId);

        Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(item.getStockQuantity()).isEqualTo(10);
    }




    private Movie createMovie(String name, int stockQuantity, int price) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setStockQuantity(stockQuantity);
        movie.setPrice(price);
        em.persist(movie);

        return movie;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }


}