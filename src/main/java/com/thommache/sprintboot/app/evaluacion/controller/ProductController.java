package com.thommache.sprintboot.app.evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thommache.sprintboot.app.evaluacion.entity.Producto;
import com.thommache.sprintboot.app.evaluacion.services.ProductService;



@Controller
@RequestMapping("/product")
@CrossOrigin({"http://localhost:4200"})
public class ProductController {

	@Autowired
	private ProductService productservice;
	
	@GetMapping
	public ResponseEntity<List<Producto>> getProductos(){
		return new ResponseEntity<>(productservice.getProductos(), HttpStatus.OK);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<Producto> getProductoSku(@PathVariable("sku") Integer sku){
		return new ResponseEntity<Producto>(productservice.getProductBySku(sku), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
		return new ResponseEntity<Producto>(productservice.createProducto(producto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<Producto> updatePrducto(@PathVariable("sku") Integer sku, @RequestBody Producto producto){
		return new ResponseEntity<Producto>(productservice.updateProducto(sku,producto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<Void> deleteProducto(@PathVariable("sku") Integer sku){
		productservice.deleteProducto(sku);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	

}
