package ru.dsoccer1980.web.dish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.service.DishService;


@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String REST_URL = "/rest/dish/";

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("id") int id) {
        return dishService.get(id);
    }


}