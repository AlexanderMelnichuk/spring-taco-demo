package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderCurrentController {
    @GetMapping("/current")
    public String getCurrentOrders(Model model) {
        model.addAttribute("model", new Order());
        return "orderForm";
    }
}
