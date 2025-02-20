package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// used for input validation check
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.ui.Model;
// explicit import to follow PMD rules
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
        // use BindingResult and @Valid to check correctness of input
        if (result.hasErrors()) {
            // back to the create form while showing an error message (seen in HTML template)
            return "createProduct";
        }

        // if successful, create product and back to product list page
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute("product", product);
        return "editProduct"; // slightly modified HTML template from create product page to support showing the current data
    }

    @PostMapping("/edit")
    public String editProductPost(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
        // same as create product workflow
        if (result.hasErrors()) {
            return "editProduct";
        }

        service.update(product);
        return "redirect:list";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }
}