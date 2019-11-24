package com.techelevator.npgeek.model;

import java.util.List;

import com.techelevator.npgeek.Park;

public interface ParkDao {
	
	public List<Park> getAllParks();

	public Park getParkById(String parkId);

}
