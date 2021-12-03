package com.project.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.repository.ProductRepository;

@Controller
public class WebController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "index";
	}

	@GetMapping("/list")
	public String list(Model model) {

		model.addAttribute("products", productRepository.findAll());
		return "list";

	}

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute( "category", categoryRepository.findAll());
		return "insert";
	}

	@GetMapping("/update")
	public String update(Model model, @RequestParam(required = false) Long id) {
		
		if (id == null) {
			
			model.addAttribute("product", new Product());
		} else {
			Product product = productRepository.findById(id).orElse(null);
//			 System.out.println(product.toString());
			model.addAttribute("c_name", product.getC_name());
			model.addAttribute("p_name", product.getP_name());
			model.addAttribute("id", product.getId());
			model.addAttribute("stock", product.getStock());
			model.addAttribute("price", product.getPrice());
			model.addAttribute( "category", categoryRepository.findAll());
			System.out.println(id);
		}

		return "update";
	}
	


	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

}
