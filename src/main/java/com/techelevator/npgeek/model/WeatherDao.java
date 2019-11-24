package com.techelevator.npgeek.model;

import java.util.List;

import com.techelevator.npgeek.Weather;

public interface WeatherDao {
	
	public List<Weather> getWeatherForPark(String parkId);

}
