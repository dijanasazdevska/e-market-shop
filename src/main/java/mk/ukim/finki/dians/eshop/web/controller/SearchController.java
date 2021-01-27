package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(name = "searchController",value = "/search")
public class SearchController {

    private final ProductService productService;
    //Constructor
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getSearch(Model model, HttpServletRequest request,@RequestParam  String language ){
        //Dodavanje atributi za produkti,jazik,sodrzina
        model.addAttribute("products",request.getSession().getAttribute("products"));
        model.addAttribute("language",language);
        model.addAttribute("bodyContent","search");
        if(language.equals("MK")){
            model.addAttribute("newurl","/search?language=EN");
            model.addAttribute("url","/search?language=MK");

        }
        else{
            model.addAttribute("newurl","/search?language=MK");
            model.addAttribute("url","/search?language=EN");

        }
        //Setiranje na atributot(jazikot) spored toa koj jazik e odbran/selektiran(MK ili EN)
        request.getSession().setAttribute("language",language);
        return "master-template";
    }
    @PostMapping
    public String postSearch(@RequestParam String search , HttpServletRequest request,@RequestParam  String language){
        request.getSession().setAttribute("products",productService.searchByProductsByAllNames(search));
        //Prenasocuvanje spored toa koj jazik e odbran/selektiran(MK ili EN)
        return "redirect:/search?language="+language;

    }
}
