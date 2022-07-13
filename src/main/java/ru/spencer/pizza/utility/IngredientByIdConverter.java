package ru.spencer.pizza.utility;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.spencer.pizza.entities.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    public IngredientByIdConverter() {
        ingredientMap.put("THIN ",new Ingredient("THIN", "Thin crust", Ingredient.Type.DOUGH));
        ingredientMap.put("THIK", new Ingredient("THIK", "Thick crust", Ingredient.Type.DOUGH));
        ingredientMap.put("PEPR", new Ingredient("PEPR", "Peperoni", Ingredient.Type.PROTEIN));
        ingredientMap.put("MASH", new Ingredient("MASH", "Mushrooms", Ingredient.Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("ONIN", new Ingredient("ONIN", "Onion", Ingredient.Type.VEGGIES));
        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("MZRL", new Ingredient("MZRL", "Mozzarella", Ingredient.Type.CHEESE));
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}