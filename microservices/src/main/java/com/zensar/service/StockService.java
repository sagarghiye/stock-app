package com.zensar.service;

import java.util.List;

import com.zensar.dto.Stock;

public interface StockService {

	public boolean deleteAllStock();

	public boolean deleteStockById(int id);

	public Stock createStock(Stock stock);

	public Stock updateStock(int stockId, Stock stock);

	public Stock getStockById(int stockId);

	public List<Stock> getAllStocks();

	public List<Stock> getStocksByName(String stockName);

	public List<Stock> getStocksSortedByName(String sortType);

	public List<Stock> getStocksByPage(int startIndex, int records);

	public List<Stock> getStocksByNameLike(String namelike);

}
