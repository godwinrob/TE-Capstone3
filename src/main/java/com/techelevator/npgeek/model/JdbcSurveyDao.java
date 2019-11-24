package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyResult;

@Component
public class JdbcSurveyDao implements SurveyDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Survey> getAllSurveys() {
		List<Survey> allSurveys = new ArrayList<>();
		String sql = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			allSurveys.add(mapRowToSurvey(results));
		}
		return allSurveys;
	}

	private Survey mapRowToSurvey(SqlRowSet row) {
		Survey survey = new Survey();
		survey.setSurveyId(row.getInt("surveyid"));
		survey.setParkCode(row.getString("parkcode"));
		survey.setEmailAddress(row.getString("emailaddress"));
		survey.setState(row.getString("state"));
		survey.setActivityLevel(row.getString("activitylevel"));
		return survey;
	}

	@Override
	public void save(Survey survey) {
		String sql = "INSERT INTO survey_result( parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?);";
		jdbcTemplate.update(sql, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
	}

	@Override
	public List<SurveyResult> getSurveyResults() {
		List<SurveyResult> selectedParks = new ArrayList<SurveyResult>();
		String sql = "SELECT survey_result.parkcode, park.parkname, park.state, count(survey_result.parkcode) FROM survey_result JOIN park on park.parkcode = survey_result.parkcode GROUP BY survey_result.parkcode, park.parkname, park.state ORDER BY count DESC, parkcode ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			selectedParks.add(mapRowToSurveyResult(results));
		} 
		return selectedParks;
	}
	
	private SurveyResult mapRowToSurveyResult(SqlRowSet row) {
		SurveyResult survey = new SurveyResult();
		survey.setSurveyCount(row.getInt("count"));
		survey.setParkCode(row.getString("parkcode"));
		survey.setParkName(row.getString("parkname"));
		survey.setParkState(row.getString("state"));
		return survey;
	}
	
}
