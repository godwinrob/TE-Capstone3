package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Weather;

@Component
public class JdbcWeatherDao implements WeatherDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherForPark(String parkId) {
		List<Weather> weather = new ArrayList<>();
		String sql = "SELECT parkcode, forecast, fivedayforecastvalue, low, high FROM weather WHERE parkcode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
		while(results.next()) {
			weather.add(mapRowToWeather(results));
		}
		return weather;
	}
	
	public Weather mapRowToWeather(SqlRowSet row) {
		Weather weather = new Weather();
		String forecast = row.getString("forecast");
		String newForcast = "";
		String[] splitString = forecast.split(" ");
		newForcast += splitString[0];
		if (splitString.length > 1) {
			newForcast += splitString[1].substring(0,1).toUpperCase() + splitString[1].substring(1);
		}
		
		weather.setParkCode(row.getString("parkcode"));
		weather.setFiveDayForecastValue(row.getInt("fivedayforecastvalue"));
		weather.setLowTemp(row.getInt("low"));
		weather.setLowTempInCelcius((int)weather.convertedTemp(row.getInt("low")));
		weather.setHighTemp(row.getInt("high"));
		weather.setHighTempInCelcius((int)weather.convertedTemp(row.getInt("high")));
		weather.setForecast(newForcast);
		return weather;
	}

}
