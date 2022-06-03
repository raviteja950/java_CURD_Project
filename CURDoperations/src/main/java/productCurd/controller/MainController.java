package productCurd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import productCurd.dao.MainDao;
import productCurd.model.Product;

@Controller
public class MainController {
	
	@Autowired
	private MainDao database;

	@RequestMapping("/home")
	public String home(Model m) {
		
		List<Product> product= database.getalldata();
		m.addAttribute("Allproduct", product);
		return "home";
	}

	// show add product form
	@RequestMapping("/add")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add Product");
		return "addproduct";
	}

	// addProduct (handle-product) handler for the above one

	@RequestMapping(path = "/handle-product", method = RequestMethod.POST)
	public RedirectView addProducthandler(@ModelAttribute Product product,HttpServletRequest req) {
		
		database.createproduct(product);
		RedirectView r = new RedirectView();
		r.setUrl(req.getContextPath()+"/");
		return r;
	}
	
	
	//delete handler
	@RequestMapping("/delete/{ProductId}")
	public RedirectView delete(@PathVariable("ProductId") int ProductId,HttpServletRequest req)
	{
		database.delete(ProductId);
		RedirectView r= new RedirectView();
		r.setUrl(req.getContextPath()+"/");
	    return r;
	}
	
	//update handler
	
	@RequestMapping("/update/{ProductId}")
	public String update(@PathVariable("ProductId") int productId,Model m)
	{
		Product product=database.get(productId);
		database.update(productId);
		m.addAttribute("product", product);
	    return "update";
	}
	
	
	
	
}
