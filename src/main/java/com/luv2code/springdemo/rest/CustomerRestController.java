package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	
		//autowire the Customer Service
		@Autowired
		private CustomerService customerService;
	
	
	
		//add mapping for Get /customers
		@GetMapping("/customers")
		public List<Customer> getCustomers() {
			
			return customerService.getCustomers();
			
		}
		
		//add mapping for Get /customer
		@GetMapping("/customers/{customerId}")
		public Customer getCustomer(@PathVariable int customerId) {
			
			
			
			Customer theCustomer =customerService.getCustomer(customerId);
			
			if (theCustomer == null ) {
				throw new CustomerNotFoundException("Customer id not found " +customerId);
				
			}
			
			return theCustomer;
		}	
		
		 // mapping for POST /customers 
		@PostMapping("/customers")
		public Customer addCustomer(@RequestBody Customer theCustomer) {
			
			//force save new customer - set the id to 0
			theCustomer.setId(0);
			customerService.saveCustomer(theCustomer);
			
		
			
			return theCustomer;
		}
		
	 // mapping for put / update the customers 
		@PutMapping("/customers")
		public Customer updateCustomer(@RequestBody Customer theCustomer) {
			
		 
			customerService.saveCustomer(theCustomer);
			
		
			
			return theCustomer;
		}
			
		
		 // mapping for delete /customers 
			@DeleteMapping("/customers/{customerId}")
			public String deleteCustomer(@PathVariable int customerId) {
				
			    // checks if id exist in customer table
				Customer thecustomer=customerService.getCustomer(customerId);
				
				if (thecustomer==null) {
					throw new CustomerNotFoundException ("Customer id not found");
				}
				
				//delete the customer
				
				customerService.deleteCustomer(customerId);
				
			
				
				return "Customer with id: "+customerId+"has been deleted";
			}
}


