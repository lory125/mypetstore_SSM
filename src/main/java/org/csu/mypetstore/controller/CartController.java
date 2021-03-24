package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

     @Autowired
     private CatalogService catalogService1;

    @GetMapping("/viewCart")
    public String viewCart(Model model){
        Cart cart = (Cart)model.asMap().get("cart");
        if (cart == null){
            cart = new Cart();
        }
        model.addAttribute("cart", cart);

        return "cart/Cart";
    }

    @GetMapping("/addItem")
    public String addItem(String itemId, Model model){
        Cart cart = (Cart)model.asMap().get("cart");
        if (cart != null) {
        } else {
            cart = new Cart();
        }

        if(cart.containsItemId(itemId)){
            cart.incrementQuantityByItemId(itemId);
        } else {
            Item item = catalogService1.getItem(itemId);
            item.setProduct(catalogService1.getProduct(item.getProductId()));
            boolean isInStock = catalogService1.isItemInStock(itemId);
            cart.addItem(item, isInStock);
        }

        model.addAttribute("cart", cart);

        return "cart/Cart";
    }

    @GetMapping("/removeItem")
    public String removeItem(String itemId, Model model){
        Cart cart = (Cart)model.asMap().get("cart");
        cart.removeItemById(itemId);

        model.addAttribute("cart", cart);

        return "cart/Cart";
    }

    @GetMapping("/changeCart")
    public String changeCart( String itemId,  int quantity, Model model){
        Cart cart = (Cart)model.asMap().get("cart");
        cart.setQuantityByItemId(itemId, quantity);

        model.addAttribute("cart", cart);

        return "cart/Cart";
    }

//    @GetMapping("/checkout")
//    public String checkout(Model model){
//        Account account =(Account)model.asMap().get("account");
//        if(account == null)
//            return "account/signon";
//        else
//            return "cart/Checkout";
//    }



}
