package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Shopping;
import com.example.demo.service.ShoppingService;
import com.sun.xml.txw2.Document;

@Controller
public class ShoppingController {
	

	


	 @Autowired
	    private ShoppingService service;

	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        List<Shopping> listshopping = service.listAll();
	        model.addAttribute("listshopping", listshopping);
	        System.out.print("Get / ");	
	        return "index";
	    }

	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("shopping", new Shopping());
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveShopping(@ModelAttribute("shopping") Shopping std) {
	        service.save(std);
	        return "redirect:/";
	    }

	    @RequestMapping("/edit/{id}")
	    public ModelAndView showEditShoppingPage(@PathVariable(name = "id") String id) {
	        ModelAndView mav = new ModelAndView("new");
	        Shopping std = service.get(id);
	        mav.addObject("shopping", std);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{id}")
	    public String deleteshopping(@PathVariable(name = "id") String id) {
	        service.delete(id);
	        return "redirect:/";
	    }
	    
	  }
