package com.example.demo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.export.OrderPDFExporter;
import com.example.demo.model.Ordering;
import com.example.demo.service.OrderService;
import com.itextpdf.text.DocumentException;

@Controller
public class OrderController {
	
	 @Autowired
	 private OrderService service;
	 
	 @RequestMapping("/viewo")
	 public String viewHomePage(Model model,@Param("keyword")String keyword) {
	     List<Ordering> listOrders = service.listAll(keyword);
	     model.addAttribute("listOrders", listOrders);
	     model.addAttribute("keyword", keyword); 
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
	 
	 
	 @GetMapping("/orders/export/pdf")
	    public void exportToPDF(HttpServletResponse response,@Param("keyword")String keyword) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=shippingdetails_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Ordering> listOrder = service.listAll(keyword);
	         
	        OrderPDFExporter exporter = new OrderPDFExporter(listOrder);
	        exporter.export(response);
	 }
}
