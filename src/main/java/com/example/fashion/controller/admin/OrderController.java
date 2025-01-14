package com.example.fashion.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fashion.models.Category;
import com.example.fashion.models.Order;
import com.example.fashion.models.User;
import com.example.fashion.services.OrderService;
import com.example.fashion.services.UserService;

@Controller
@RequestMapping("/admin")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order")
    public String index(Model model) {
        List<Order> lstOrders = this.orderService.getAll();
        model.addAttribute("lstOrders", lstOrders);
        return "admin/order/index";
    }

    @GetMapping("/edit-order/{OrderID}")
    public String edit(Model model, @PathVariable("OrderID") Long OrderID) {
        Order order = this.orderService.findByID(OrderID);
        model.addAttribute("order", order);
        return "admin/order/edit";
    }

    @PostMapping("/edit-order")
    public String update(@ModelAttribute("order") Order order, Model model) {
       
        try{
            if(this.orderService.update(order)){
                return "redirect:/admin/order";
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/order/edit";
    }

}
