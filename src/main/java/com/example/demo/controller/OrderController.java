package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Ordering;
import com.example.demo.service.OrderService;

@Controller
public class OrderController {
	
	 @Autowired
	 private OrderService service;
	 
	 @RequestMapping("/viewo")
	 public String viewHomePage(Model model) {
	     List<Ordering> listOrders = service.listAll();
	     model.addAttribute("listOrders", listOrders);
	      
	     return "vieworderlist";
	 }
	 
	 @RequestMapping("/newo")
	 public String showNewOrderPage(Model model) {
		 Ordering ordering = new Ordering();
		 model.addAttribute("ordering",ordering);
		 
		 return "new_order";
	 }
	 
	 @RequestMapping(value = "/saveo", method = RequestMethod.POST )
	 public String saveOrder(@ModelAttribute("ordering") Ordering ordering) {
		 service.save(ordering);
		 
		 return "redirect:/newp";
	 }
	 
	 @RequestMapping("/edito/{oid}")
	 public ModelAndView showEditOrderPage(@PathVariable(name = "oid")int oid) {
		ModelAndView mav = new ModelAndView("edit_order");
		Ordering ordering = service.get(oid);
		mav.addObject("ordering", ordering);
		
		return mav;
	 }
	 
	 @RequestMapping("/deleteo/{oid}")
	 public String deleteOrder(@PathVariable(name = "oid")int oid) {
		 service.delete(oid);
		 return "redirect:/viewo";
	 }
}
