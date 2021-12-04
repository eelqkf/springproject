package com.project.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		model.addAttribute("category", categoryRepository.findAll());
		return "index";
	}

	@PostMapping("/view")
    public String selectCate(Model model, @RequestParam int kind) {
        //System.out.println(kind);
        List<Product> products;

        if(kind == 0) {
            products = productRepository.findAll();
            model.addAttribute("selectCategory", "ALL");
        } else {

            products = productRepository.selectCateSQL(kind);
           //System.out.println(products.size());
           
            model.addAttribute("selectCategory", products.get(0).getCa_id());
        }
        model.addAttribute("selectProduct", products);

        return "view";

    }
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("category", categoryRepository.findAll());
		model.addAttribute("products", productRepository.findAll());
		return "list";

	}

	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}

	@GetMapping("/manreg")
	public String manreg() {
		return "manreg";
	}

	@GetMapping("/insert") // 등록
	public String insert(Model model) {
		model.addAttribute("category", categoryRepository.findAll());
		return "insert";
	}

	@GetMapping("/update")
	public String update(Model model, @RequestParam(required = false) Long id) {

		if (id == null) {

			model.addAttribute("product", new Product());
		} else {
			Product product = productRepository.findById(id).orElse(null);
//			 System.out.println(product.toString());
			model.addAttribute("c_name", product.getCa_id());
			model.addAttribute("p_name", product.getP_name());
			model.addAttribute("id", product.getId());
			model.addAttribute("stock", product.getStock());
			model.addAttribute("price", product.getPrice());
			model.addAttribute("category", categoryRepository.findAll());
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
	
	@GetMapping("/upload")
		public String upload(Model model) {
		model.addAttribute( "category", categoryRepository.findAll());
		return "upload";
	}
	
	
	@PostMapping("insert")
	public String insert(Product product,Model model, MultipartFile file) throws Exception{
		
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\file"; 
		
	
		System.out.println(path);
		
		String fileName =  file.getOriginalFilename(); //이렇게하면 랜덤으로 식별자가(uuid) 붙은다음 _ 붙이고 원래 파일명이 붙는다.
		
		//2.File 이라는 class 를이용해서 빈껍데기 생성 후 file 이름(name) 지정
		File saveFile = new File(path, fileName);
		
		file.transferTo(saveFile);
		
		product.setFilename(fileName);
		product.setFilepath("file/"+fileName);
		
		System.out.println(product);
		
		productRepository.save(product);
		
		return "redirect:/insert";
	}

}
