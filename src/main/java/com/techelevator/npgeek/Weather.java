package com.techelevator.npgeek;

public class Weather {
	
	private String parkCode;
	private int fiveDayForecastValue;
	private int lowTemp;
	private int lowTempInCelcius;
	private int highTemp;
	private int highTempInCelcius;
	private String forecast;
	private boolean celcius;
	
	public Weather() {
		
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}

	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

	public int getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}

	public int getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public boolean isCelcius() {
		return celcius;
	}

	public void setCelcius(boolean celcius) {
		this.celcius = celcius;
	}
	
	public double convertedTemp(int temperature) {
		return (temperature - 32) * 0.5556;
	}

	public int getLowTempInCelcius() {
		return lowTempInCelcius;
	}

	public void setLowTempInCelcius(int lowTempInCelcius) {
		this.lowTempInCelcius = lowTempInCelcius;
	}

	public int getHighTempInCelcius() {
		return highTempInCelcius;
	}

	public void setHighTempInCelcius(int highTempInCelcius) {
		this.highTempInCelcius = highTempInCelcius;
	}

}
