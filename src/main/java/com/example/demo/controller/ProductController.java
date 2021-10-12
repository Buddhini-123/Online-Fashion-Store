
package com.example.demo.controller;


import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



@Controller
public class ProductController {
	
	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ProductService productService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	

	@GetMapping("addpro")
	public String addProductPage() {
		return "indexladies";
	}
	

	@PostMapping("/image/saveImageDetails")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
			@RequestParam("price") double price, @RequestParam("description") String description,@RequestParam("quantity") int quantity, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
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
				File dir = new File(uploadDirectory);
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
			Product product = new Product();
			product.setName(names[0]);
			product.setImage(imageData);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setDescription(descriptions[0]);
			productService.saveImage(product);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/savepr", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    productService.savepr(product);
	     
	    return "redirect:/image/show";   
	}
	
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		product = productService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Product> product, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				product = productService.getImageById(id);
			
				log.info("products :: " + product);
				if (product.isPresent()) {
					model.addAttribute("id", product.get().getId());
					model.addAttribute("description", product.get().getDescription());
					model.addAttribute("name", product.get().getName());
					model.addAttribute("price", product.get().getPrice());
					model.addAttribute("quantity", product.get().getQuantity());
					return "imagedetails";
				}
				return "redirect:/home";
			}
		return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}	
	}

	@GetMapping("/image/show")
	String show(Model map, @Param("keywordp") String keywordp) {
		List<Product> images = productService.getAllActiveImages(keywordp);
		map.addAttribute("images", images);
		map.addAttribute("keywordp", keywordp);
		return "images";
	}
	@GetMapping("/image/display")
	String display(Model map) {
		List<Product> display = productService.getAllActiveDisplay();
		map.addAttribute("display", display);
		return "display";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id,Optional<Product> product, Model model) {
		
		// get product from the service
		 product = productService.getImageById(id);
		
		// set product as a model attribute to pre-populate the form
		 
		model.addAttribute("product", product);
		return "update_product";
	}

		@RequestMapping("/deleteProduct/{id}")
		public String deleteProduct(@PathVariable(value = "id") long id) {
			productService.deleteProduct(id);
			return "redirect:/image/show";       
		}
		
		@GetMapping("/generateproduct")
		public ResponseEntity<Resource> generateExcelReport() throws IOException, DocumentException {
			List<Product> product = productService.getAllActiveImages(null);

			Document document = new Document(PageSize.A4, 25, 25, 25, 25);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			PdfWriter.getInstance(document, os);

			document.open();

			Paragraph title = new Paragraph("Eleonora Online Fashionstore Product List ",
					FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD, new BaseColor(3, 3, 2)));

			document.add(title);

			PdfPTable table = new PdfPTable(2);
			table.setSpacingBefore(30);
			table.setSpacingAfter(30);
			
			

			PdfPCell c1 = new PdfPCell(new Phrase("Product Name"));
			table.addCell(c1);
			
			PdfPCell c2 = new PdfPCell(new Phrase("Description"));
			table.addCell(c2);
			


			for (Product products : product) {
				table.addCell(products.getName());
				table.addCell(products.getDescription());
				
			}

			document.add(table);
			
			document.close();

			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Eleonora Online Fashionstore.pdf");

			ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
					HttpStatus.OK);

			return response;
		}
		
		
}	

