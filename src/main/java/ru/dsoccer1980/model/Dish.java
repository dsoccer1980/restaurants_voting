package ru.dsoccer1980.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public Dish() {
    }

    public Dish(Dish r) {
        this(r.getId(), r.getName(), r.getPrice(), r.getRestaurant(), r.getDate());
    }

    public Dish(Integer id, String name, Integer price, Restaurant restaurant, @NotNull LocalDate date) {
        super(id, name);
        Objects.requireNonNull(price, "price cannot be null");
        Objects.requireNonNull(restaurant, "restaurant cannot be null");
        Objects.requireNonNull(date, "date cannot be null");
        this.price = price;
        this.restaurant = restaurant;
        this.date = date;
    }

    public Dish(String name, Integer price, Restaurant restaurant, @NotNull LocalDate date) {
        this(null, name, price, restaurant, date);
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + id +
                ", restaurantId=" + restaurant.getId() +
                ", name='" + name +
                "', price=" + (price / 100) + "." + (price % 100) +
                ", date=" + date +
                '}';
    }
}