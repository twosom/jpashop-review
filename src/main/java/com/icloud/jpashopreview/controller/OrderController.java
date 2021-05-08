package com.icloud.jpashopreview.controller;


import com.icloud.jpashopreview.domain.Member;
import com.icloud.jpashopreview.domain.Order;
import com.icloud.jpashopreview.domain.OrderSearch;
import com.icloud.jpashopreview.domain.item.Item;
import com.icloud.jpashopreview.repository.OrderCustomRepository;
import com.icloud.jpashopreview.service.ItemService;
import com.icloud.jpashopreview.service.MemberService;
import com.icloud.jpashopreview.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;


    private final OrderCustomRepository customRepository;


    @GetMapping("/orders")
    public String orderList(@ModelAttribute(name = "orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = customRepository.findOrders(orderSearch);
//        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }


    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        log.info("memberId = {}, itemId = {}, count = {}", memberId, itemId, count);
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
}
