package com.example.demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Payment;

import com.example.demo.service.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	
	
	@RequestMapping("/viewp")
	public String viewPaymentList(Model model,
			@Param("keyword") Long keyword) {
		List<Payment> listPayments = service.listAll(keyword);
		model.addAttribute("listPayments", listPayments);
		model.addAttribute("keyword", keyword);
		return "paymentList";
	}
	
	@RequestMapping("/newp")
	public String showNewPaymentPage(Model model) {
	    Payment payment = new Payment();
	    model.addAttribute("payment", payment);
	     
	    return "new_payment";
	}
	
	@RequestMapping(value = "/savep", method = RequestMethod.POST)
	public String savePayment(@ModelAttribute("payment") Payment payment) {
	    service.save(payment);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/editp/{pid}")
	public ModelAndView showEditPaymentPage(@PathVariable(name = "pid") int pid) {
	    ModelAndView mav = new ModelAndView("edit_payment");
	    Payment payment = service.get(pid);
	    mav.addObject("payment", payment);
	     
	    return mav;
	}
	
	@RequestMapping("/deletep/{pid}")
	public String deletePayment(@PathVariable(name = "pid") int pid) {
	    service.delete(pid);
	    return "redirect:/";       
	}

}
