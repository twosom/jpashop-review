package com.icloud.jpashopreview.controller;

import com.icloud.jpashopreview.controller.dto.MovieForm;
import com.icloud.jpashopreview.controller.dto.UpdateMovieForm;
import com.icloud.jpashopreview.domain.item.Book;
import com.icloud.jpashopreview.domain.item.Item;
import com.icloud.jpashopreview.domain.item.Movie;
import com.icloud.jpashopreview.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new MovieForm());
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(MovieForm form) {
        System.out.println(form.getName());

        Movie movie = form.toEntity();
        itemService.saveItem(movie);
        return "redirect:/items";
    }

    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Movie findMovie = (Movie) itemService.findOne(itemId);
        /* Entity 를 받아서 form DTO로 변환 */
        UpdateMovieForm form = new UpdateMovieForm(findMovie);
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }


    @PostMapping("/{itemId}/edit")
    public String updateItem(@ModelAttribute(name = "form")Movie movie) {
        itemService.saveItem(movie);
        return "redirect:/items";
    }
}
