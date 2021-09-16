package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Product;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public ModelAndView home() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	@RequestMapping("/admindash")
	public ModelAndView admindash() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("admindash");
	    return modelAndView;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
	return "index";
	}
	

	
	/*@GetMapping("/")
	public String home1() {
		return "index";
	}*/
}
