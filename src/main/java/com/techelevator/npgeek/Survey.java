package com.techelevator.npgeek;

import java.util.Arrays;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class Survey {
	
	public static final String[] STATES = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", 
			"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", 
			"ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
			};

	
	private int surveyId;
	
	@NotBlank
	private String parkCode;
	
	@NotBlank(message="Email address is required.")
	@Email(message="Please enter a valid email address.")
	@Length(max=100, message="Last name can be no longer than 20 characters.")
	private String emailAddress;
	
	private String state;
	
	@NotBlank
	private String activityLevel;
	
	public Survey() {
		
	}
	
	@AssertTrue(message = "That isn't a state!")
	public boolean isValidState() {
		boolean result = false;
		if (state != null) {
				result= Arrays.stream(STATES).anyMatch(getState()::equals);
		}
		return result;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	

}
