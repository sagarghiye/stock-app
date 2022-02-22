package com.zensar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_market")
public class StockEntity {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Column(name = "market")
	private String marketName;
	private double price;

	public StockEntity() {
	}

	public StockEntity(String name, String marketName, double price) {
		this.name = name;
		this.marketName = marketName;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", market=" + marketName + ", price=" + price + "]";
	}

}
