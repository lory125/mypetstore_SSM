package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CatalogService  catalogService;

    @GetMapping("/view")
    public String view()
    {
        return "catalog/main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model)
    {
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            model.addAttribute("category",category);
            model.addAttribute("productList",productList);
            return "catalog/Category";
        }
        else return "catalog/main";
    }

    @GetMapping("/viewProduct")
    public String viewProduct(String productId,Model model) {
        if (productId != null) {
            Product product = catalogService.getProduct(productId);
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            model.addAttribute("product",product);
            model.addAttribute("itemList",itemList);
            return "catalog/Product";
        }
        else return "catalog/main";

    }

    @GetMapping("viewItem")
    public String viewItem(@RequestParam("itemId") String itemId, Model model){
        if (itemId != null){
            Item item =  catalogService.getItem(itemId);
            Product product = item.getProduct();
            model.addAttribute("product", product);
            model.addAttribute("item", item);
            return "catalog/Item";
        }
        else return "catalog/main";

    }
}
