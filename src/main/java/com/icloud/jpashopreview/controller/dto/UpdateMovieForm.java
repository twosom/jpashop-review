package com.icloud.jpashopreview.controller.dto;

import com.icloud.jpashopreview.domain.item.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UpdateMovieForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String director;
    private String actor;

    public Movie toEntity() {
        return Movie.builder()
                .name(getName())
                .price(getPrice())
                .stockQuantity(getStockQuantity())
                .director(getDirector())
                .actor(getActor())
                .build();
    }

    public UpdateMovieForm(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.price = movie.getPrice();
        this.stockQuantity = movie.getStockQuantity();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
    }



}
