package com.thommache.sprintboot.app.evaluacion.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thommache.sprintboot.app.evaluacion.entity.Producto;
import com.thommache.sprintboot.app.evaluacion.repository.ProductRepository;


@Service
public class ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productoRepository;

	public List<Producto> getProductos() {
		
		return productoRepository.findAll();
	}

	@CacheEvict("producto")
	public void deleteProducto(Integer sku) {
		Producto producto = getProductBySku(sku);
		 productoRepository.delete(producto);
	} 

	
	
	@Cacheable("producto")
	public Producto getProductBySku(Integer sku) {
		log.info("Getting produc by sku {}" , sku);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 return productoRepository.findById(sku).orElseThrow(() -> 
		 new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Sku %s not found", sku)));
	}

	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	public Producto updateProducto(Integer sku, Producto producto) {
		Optional<Producto> result = productoRepository.findById(sku);
		if (result.isPresent()) {
			return productoRepository.save(producto);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Producto id %d doesn`t exists", sku));
		}
	}
	
	
}
