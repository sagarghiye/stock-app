package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Stock;
import com.zensar.service.StockService;

@RestController
@RequestMapping("/stockApp")
@CrossOrigin(origins = "*")
public class StockController {

	@Autowired
	private StockService stockService;

	@DeleteMapping(value = "/deleteStocks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteStocksById(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(stockService.deleteStockById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteStocks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteStocks() {
		return new ResponseEntity<Boolean>(stockService.deleteAllStock(), HttpStatus.OK);
	}

	@PostMapping(value = "/createStock", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		return new ResponseEntity<Stock>(stockService.createStock(stock), HttpStatus.OK);
	}

	@GetMapping(value = "/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Stock>> getStocks() {
		return new ResponseEntity<List<Stock>>(stockService.getAllStocks(), HttpStatus.OK);
	}

	@GetMapping(value = "/stocks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stock> getStocksNyId(@PathVariable("id") int id) {
		return new ResponseEntity<Stock>(stockService.getStockById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/updateStock/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock, @PathVariable("id") int id) {
		return new ResponseEntity<Stock>(stockService.updateStock(id, stock), HttpStatus.OK);

	}

	@GetMapping(value = "/stock/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Stock>> getStocksByName(@PathVariable("name") String stockName) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByName(stockName), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/sort/{sortType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Stock>> getStocksSortedByName(@PathVariable("sortType") String sortType) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksSortedByName(sortType), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/{startIndex}/{records}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Stock>> getStocksByPage(@PathVariable("startIndex") int startIndex,
			@PathVariable("records") int records) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByPage(startIndex, records), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/like/{namelike}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Stock>> getStocksByNameLike(@PathVariable("namelike") String namelike) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByNameLike(namelike), HttpStatus.OK);
	}
}
