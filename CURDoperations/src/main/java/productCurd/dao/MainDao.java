package productCurd.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productCurd.model.Product;

@Component
public class MainDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	
	//create
	@Transactional
	public void createproduct(Product product)
	{
		this.hibernateTemplate.saveOrUpdate(product);
	}
	
	
	//get all data
	public List<Product> getalldata()
	{
		List<Product> product=this.hibernateTemplate.loadAll(Product.class);
		return product;
	}
	
	//to delete an single item
	@Transactional
	public void delete(int pid)
	{
		Product p=this.hibernateTemplate.load(Product.class, pid);
		this.hibernateTemplate.delete(p);
	}
	
	//to get single item
	
	public Product get(int pid)
	{
		return this.hibernateTemplate.get(Product.class,pid);
	}
	
	
	//to update
	@Transactional
	public void update(int id)
	{
		Product p=this.hibernateTemplate.load(Product.class, id);
		this.hibernateTemplate.update(p);
	}
	
	
}
