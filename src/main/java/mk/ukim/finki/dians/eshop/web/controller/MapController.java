package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.service.MarketLocationService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/мапа")
public class MapController {
    private final MarketLocationService marketLocationService;
    private final ProductService productService;


    public MapController(MarketLocationService marketLocationService, ProductService productService) {
        this.marketLocationService = marketLocationService;
        this.productService = productService;
    }

    @GetMapping
    public String getPage( Model model){
        model.addAttribute("marketlocs",marketLocationService.findAll());
model.addAttribute("language","MK");
        return "map";
    }
    @PostMapping
    public String postPage(@RequestParam String  search, HttpServletRequest request){
        request.getSession().setAttribute("products",productService.searchByProducts(search));
        return "redirect:/searchMK";
    }


}
