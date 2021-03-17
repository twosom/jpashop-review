package com.icloud.jpashopreview.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter @Setter @NoArgsConstructor
public class Movie extends Item{

    private String director;
    private String actor;


    @Builder
    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setDirector(director);
        setActor(actor);
    }
}
