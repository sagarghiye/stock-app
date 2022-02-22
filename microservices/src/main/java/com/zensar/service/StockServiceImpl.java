package com.zensar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean deleteAllStock() {
		repository.deleteAll();
		return true;
	}

	@Override
	public boolean deleteStockById(int id) {
		Optional<StockEntity> findById = repository.findById(id);
		if (findById.isPresent()) {
			StockEntity stockEntity = findById.get();
			repository.delete(stockEntity);
			return true;
		}
		return false;
	}

	@Override
	public Stock createStock(Stock stock) {
		StockEntity stockEntity = new StockEntity(stock.getName(), stock.getMarket(), stock.getPrice());
		StockEntity save = repository.save(stockEntity);
		stock.setId(save.getId());
		return stock;
	}

	@Override
	public Stock updateStock(int stockId, Stock stock) {
		Optional<StockEntity> findById = repository.findById(stockId);
		if (findById.isPresent()) {
			StockEntity stockEntity = findById.get();
			stockEntity.setMarketName(stock.getMarket());
			stockEntity.setName(stock.getName());
			stockEntity.setPrice(stock.getPrice());
			StockEntity save = repository.save(stockEntity);
			stock.setId(save.getId());
			return stock;
		}
		return null;
	}

	@Override
	public Stock getStockById(int stockId) {
		StockEntity byId = repository.getById(stockId);
		Stock stock = convertToStock(byId);
//		Stock stock = new Stock(byId.getId(), byId.getName(), byId.getMarketName(), byId.getPrice());
		return stock;
	}

	@Override
	public List<Stock> getAllStocks() {
		List<StockEntity> findAll = repository.findAll();
		List<Stock> collect = findAll.stream()
				.map(stock -> new Stock(stock.getId(), stock.getName(), stock.getMarketName(), stock.getPrice()))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		List<StockEntity> findByName = repository.findByName(stockName);
		List<Stock> collect = findByName.stream()
				.map(stock -> new Stock(stock.getId(), stock.getName(), stock.getMarketName(), stock.getPrice()))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {

		// both below works for sorting by name
		repository.findByOrderByName();
		repository.findByOrderByName();
		repository.orderBySqlQuery();

		Sort.Order order;
		// other way to sort
		if ("ASC".equals(sortType)) {
			order = new Order(Direction.ASC, "name");
		} else {
			order = new Order(Direction.DESC, "name");
		}
		Sort sort = Sort.by(order);
		List<StockEntity> findAll = repository.findAll(sort);
		List<Stock> collect = findAll.stream()
				.map(stock -> new Stock(stock.getId(), stock.getName(), stock.getMarketName(), stock.getPrice()))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		Pageable pageable = PageRequest.of(startIndex, records);
		Page<StockEntity> findAll = repository.findAll(pageable);
		List<StockEntity> content = findAll.getContent();
		List<Stock> collect = content.stream()
				.map(stock -> new Stock(stock.getId(), stock.getName(), stock.getMarketName(), stock.getPrice()))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<Stock> getStocksByNameLike(String namelike) {
		List<StockEntity> findByName = repository.getByNameLikeSqlQuery(namelike);
		List<Stock> collect = findByName.stream()
				.map(stock -> new Stock(stock.getId(), stock.getName(), stock.getMarketName(), stock.getPrice()))
				.collect(Collectors.toList());
		return collect;
	}

	public Stock convertToStock(StockEntity stockEntity) {

		TypeMap<StockEntity, Stock> typeMap = mapper.typeMap(StockEntity.class, Stock.class);
		typeMap.addMapping(StockEntity::getMarketName, Stock::setMarket);
		Stock map = mapper.map(stockEntity, Stock.class);
		return map;
	}

}
