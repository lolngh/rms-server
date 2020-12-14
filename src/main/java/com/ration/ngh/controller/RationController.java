package com.ration.ngh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ration.ngh.model.RationModel;
import com.ration.ngh.model.Revenue;
import com.ration.ngh.repo.RationRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class RationController {

	@Autowired
	private RationRepository repo;
	
	@GetMapping("/ration")
	public List<RationModel> listAllRationShops() {
		return this.repo.findAll();
	}
	
	@PostMapping("/ration")
	public RationModel createRationShop(@RequestBody RationModel ration) {
		return this.repo.save(ration);
	}
	
	@DeleteMapping("/ration/{id}")
	public void deleteRationShopById(@PathVariable int id ) {
		this.repo.deleteById(id);
	}
	
	@PutMapping("/change/{id}")
	public RationModel updateRation(@PathVariable int id,@RequestBody RationModel ration) {
		RationModel rat = this.repo.findById(id).orElseThrow(()-> new IllegalArgumentException("nil"));
		rat.setAddress(ration.getAddress());
		rat.setCity(ration.getCity());
		rat.setCountry(ration.getCountry());
		rat.setName(ration.getName());
		rat.setState(ration.getState());
		Revenue rev = rat.getRevenue();
		rev.setOwner(ration.getRevenue().getOwner());
		return this.repo.save(rat);
	}
	
	@GetMapping("/ration/{state}")
	public List<RationModel> listRationShopsByState(@PathVariable String state){
		return this.repo.findByState(state);
	}
	
	@GetMapping("/ration/revenue/{state}")
	public long getRevenueByState(@PathVariable String state) {
		return this.repo.findRevenueByState(state);
	}
	
	@PutMapping("/revenue/{id}")
	public void updateRevenue(@PathVariable int id,@RequestBody Revenue rev) {
		RationModel ration = this.repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Nil"));
		Revenue revenue = ration.getRevenue();
		revenue.setRevenue(rev.getRevenue());
		repo.save(ration);
	}
	
	@GetMapping("/rationshop/{id}")
	public RationModel getRationShopById(@PathVariable int id) {
		return this.repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Not Found"));
	}
	
	@GetMapping("/ration/state")
	public List<String> listRationshopsByState(){
	   return this.repo.findDistinctStates();
	}
	
}
