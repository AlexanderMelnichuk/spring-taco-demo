package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.dto.Order;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String ORDER_FORM = "orderForm";
    public static final String HOME_PAGE = "redirect:/";

    @GetMapping("/current")
    public String getCurrentOrders(Model model) {
        model.addAttribute("order", new Order());
        return ORDER_FORM;
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return ORDER_FORM;
        }
        log.info("Order submitted: " + order);
        return HOME_PAGE;
    }
}
