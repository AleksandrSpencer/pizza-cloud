package ru.spencer.pizza.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import ru.spencer.pizza.entities.Ingredient;
import ru.spencer.pizza.entities.Pizza;
import ru.spencer.pizza.entities.PizzaOrder;
import javax.validation.Valid;
import org.springframework.validation.Errors;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("THIN", "Thin crust", Ingredient.Type.DOUGH),
                new Ingredient("THIK", "Thick crust", Ingredient.Type.DOUGH),
                new Ingredient("PEPR", "Peperoni", Ingredient.Type.PROTEIN),
                new Ingredient("MASH", "Mushrooms", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("ONIN", "Onion", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("MZRL", "Mozzarella", Ingredient.Type.CHEESE)

        );
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    @PostMapping
    public String processPizza(
                                @Valid Pizza pizza, Errors errors,
                                @ModelAttribute PizzaOrder pizzaOrder) {
        if (errors.hasErrors()) {
            return "design";
        } pizzaOrder.addPizza(pizza);
        log.info("Processing pizza: {}", pizza);
        return "redirect:/orders/current";
    }
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

