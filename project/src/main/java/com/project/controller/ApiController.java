package com.project.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.model.Manager;
import com.project.model.Result;
import com.project.repository.ManagerRepository;
import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Bean
	public PasswordEncoder passwordEncoder2() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/manreg")
	public Result managerSignup(@RequestBody Manager manager) {
		if (managerRepository.existsById(manager.getManagerId())) {
			return new Result("fail");
		}
		manager.setManagerPassword(passwordEncoder2().encode(manager.getManagerPassword()));

		managerRepository.save(manager);
		return new Result("success");
	}

//	@PostMapping("/insert")
//	public String insert(@RequestBody Product product) {
//		System.out.println(product);
//		productRepository.save(product);
//
//		return "redirect:/list";
//	}

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

		if (deleteid.isPresent()) {
			System.out.println(deleteid);
			productRepository.delete(product);
		}

	}


}