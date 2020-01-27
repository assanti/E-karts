package com.ekarts.dto;

import com.ekarts.enumClass.KartEnumClass;


public class Kart {
	
	private int id;
	private String name; 
	private KartEnumClass enumType;
	private double priceMinute;
	private String cover;
	
	public Kart(int id) {
		super();
		this.id = id;
	}

	public Kart(int id, String name, KartEnumClass enumType, double priceMinute, String cover) {
		super();
		this.id = id;
		this.name = name;
		this.enumType = enumType;
		this.priceMinute = priceMinute;
		this.cover = cover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KartEnumClass getEnumType() {
		return enumType;
	}

	public void setEnumType(KartEnumClass enumType) {
		this.enumType = enumType;
	}

	public double getPriceMinute() {
		return priceMinute;
	}

	public void setPriceMinute(double priceMinute) {
		this.priceMinute = priceMinute;
	}
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kart other = (Kart) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kart [id=" + id + ", name=" + name + ", enumType=" + enumType + ", priceMinute=" + priceMinute
				+ ", cover=" + cover + "]";
	}


}
