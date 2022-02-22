package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer> {

	List<StockEntity> findByName(String name);

	List<StockEntity> findByMarketName(String name);

	// all below queries work same for like property for column name
	List<StockEntity> findByNameContains(String name);

	List<StockEntity> findByNameContaining(String name);

	List<StockEntity> findByNameIsContaining(String name);

	@Query(value = "select se from StockEntity as se where se.name=:name")
	List<StockEntity> getByNameLike(String name);

	@Query(value = "select * from stock_market where name LIKE %:name%", nativeQuery = true)
	List<StockEntity> getByNameLikeSqlQuery(String name);

	// ordered by asc by default
	List<StockEntity> findByOrderByName();

	// ordered by desc
	List<StockEntity> findByOrderByNameDesc();
	
	// jpql order by
	@Query(value = "select se from StockEntity se order by se.name desc")
	List<StockEntity> orderBySqlQuery();
	
}
