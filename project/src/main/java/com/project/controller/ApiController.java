package com.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/insert")
	public String insert(@RequestBody Product product) {
		System.out.println(product);
		productRepository.save(product);
		
		return "redirect:/list";
	}

	@PostMapping("/login")
	public String loginSub() {
		return "list";
	}

	@PostMapping("/signup")
	public String singupSub() {
		return "redirect:/list";
	}

	@PutMapping("/update")
	public String update(@RequestBody Product product) {
		System.out.println(product.toString() + "전");
		productRepository.save(product);
		System.out.println(product.toString() + "후");
		return "redirect:/list";
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody Product product) {
		Optional<Product> deleteid = productRepository.findById(product.getId());
		
		if(deleteid.isPresent()) {
			System.out.println(deleteid);
			productRepository.delete(product);
		}
		

	}

}
