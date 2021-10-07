package com.example.demo.controller;




import java.awt.Font;
import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;



@Controller
public class FeedbackController {

	@Autowired
    private FeedbackService service;

    @GetMapping("/indexfeedback")
    public String viewHomePage(Model model @Param("keyword") Long keyword) {
        List<Feedback> listuser = service.listAll(keyword);
        model.addAttribute("listuser", listuser);
        model.addAttribute("keyword", keyword);
        System.out.print("Get / ");	
        return "indexfeedback";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("Feedback", new Feedback());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("User") Feedback us) {
        service.save(us);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String showEditUserPage(@PathVariable(name = "id") int id,Model model) {
       
        Feedback us = service.get(id);
       model.addAttribute("Feedback",us);
        return "new";
        
    }
    @RequestMapping("/delete/{id}")
    public String deleteUserPage(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/indexfeedback";
    }
    
    @GetMapping("/Thankufd")
    public String thank() {
        return "Thankufd";
    }

    @GetMapping("/homepage")
    public String home() {
        return "homepage";
    }

   
    
    
   
    @GetMapping("/contactus")
    public String contact() {
        return "contactus";
    }
    
   
   
    
}
