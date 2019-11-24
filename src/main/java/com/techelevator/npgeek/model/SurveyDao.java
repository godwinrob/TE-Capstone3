package com.techelevator.npgeek.model;

import java.util.List;

import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyResult;

public interface SurveyDao {
	
	public List<Survey> getAllSurveys();
	public void save(Survey survey);
	public List<SurveyResult> getSurveyResults();

}
