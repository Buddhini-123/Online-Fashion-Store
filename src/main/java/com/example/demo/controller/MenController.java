package com.example.demo.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.model.Men;
import com.example.demo.service.MenService;



@Controller
public class MenController {
	
	@Value("${uploadDir1}")
	private String uploadFolder1;

	@Autowired
	private MenService menService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping("addmen")
	public String addMenPage() {
		return "indexmen";
	}

	@PostMapping("/image/saveMenDetails")
	public @ResponseBody ResponseEntity<?> createMen(@RequestParam("name") String name,
			@RequestParam("price") double price, @RequestParam("description") String description,@RequestParam("quantity") int quantity, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory1 = request.getServletContext().getRealPath(uploadFolder1);
			log.info("uploadDirectory1:: " + uploadDirectory1);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory1, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			log.info("name: " + names[0]+" "+filePath);
			log.info("description: " + descriptions[0]);
			log.info("price: " + price);
			log.info("quantity: " + quantity);
			try {
				File dir = new File(uploadDirectory1);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Men men = new Men();
			men.setName(names[0]);
			men.setImage(imageData);
			men.setPrice(price);
			men.setQuantity(quantity);
			men.setDescription(descriptions[0]);
			menService.saveImage(men);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/image/displaymen/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Men> men)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		men = menService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(men.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/menDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Men> men, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				men = menService.getImageById(id);
			
				log.info("products :: " + men);
				if (men.isPresent()) {
					model.addAttribute("id", men.get().getId());
					model.addAttribute("description", men.get().getDescription());
					model.addAttribute("name", men.get().getName());
					model.addAttribute("price", men.get().getPrice());
					model.addAttribute("quantity", men.get().getQuantity());
					return "mendetails";
				}
				return "redirect:/home";
			}
		return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}	
	}

	@GetMapping("/image/showmen")
	String show(Model map) {
		List<Men> mens = menService.getAllActiveImages();
		map.addAttribute("mens", mens);
		return "mens";
	}
	@GetMapping("/image/displaymen")
	String displaymen(Model map) {
		List<Men> displaymen = menService.getAllActiveDisplay();
		map.addAttribute("displaymen", displaymen);
		return "displaymen";
	}
	
	@PostMapping("/saveMen")
	public String saveMen(@ModelAttribute("men") Men men) {
	//save product to database
	menService.saveMen(men);
	return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdatemen/{id}")
	public String showFormForUpdatemen(@PathVariable (value = "id") long id, Model model) {
		
		// get employee from the service
		Men men = menService.getProductById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("men", men);
		return "update_men";
	}
	
	
		
		@RequestMapping("/deleteMen/{id}")
		public String deleteProduct(@PathVariable(value = "id") long id) {
			menService.deleteProduct(id);
		    return "redirect:/";       
		}
}	

