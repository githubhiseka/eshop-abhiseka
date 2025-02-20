package id.ac.ui.cs.advprog.eshop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;  // only import GetMapping

@Controller
public class MainController {

    @GetMapping("")
    public String mainPage() {
        return "homePage";
    }
}