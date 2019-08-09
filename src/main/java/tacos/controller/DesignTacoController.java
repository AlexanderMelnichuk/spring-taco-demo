package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.dto.Ingredient;
import tacos.dto.Ingredient.Type;
import tacos.dto.Taco;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")

// TODO: Find the best way to preserve model attributes that are not included in the POST request.
// @SessionAttributes(value = {"wrap", "protein", "veggies", "cheese", "sauce"}, types = {List.class})
// This annotation can be used to retain ingredients by types in the session, and avoid adding them on every
// validation failure in processDesign. But it uses hardcoded values that don't seem flexible.

public class DesignTacoController {

    private static final String DESIGN_FORM = "designForm";
    private static final String ORDER_FORM = "redirect:/orders/current";
    private static final List<Ingredient> INGREDIENTS = Collections.unmodifiableList(Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE))
    );

    private static final Map<String, List<Ingredient>> INGREDIENTS_BY_TYPE = INGREDIENTS.stream()
            .collect(Collectors.groupingBy(
                    ingredient -> ingredient.getType().toString().toLowerCase(),
                    Collectors.toList()));


    @GetMapping
    public String showDesignForm(Model model) {

        model.addAllAttributes(INGREDIENTS_BY_TYPE);

        model.addAttribute("design", new Taco());

        return DESIGN_FORM;
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {

        log.info("Model: {}", model.asMap());

        // See TO DO at the beginning of the class
        model.addAllAttributes(INGREDIENTS_BY_TYPE);

        if (errors.hasErrors()) {
            return DESIGN_FORM;
        }
        // Save the taco design...
        log.info("Processing taco: " + design);
        return ORDER_FORM;
    }
}
