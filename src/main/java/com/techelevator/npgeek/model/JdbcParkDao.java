package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Park;

@Component
public class JdbcParkDao implements ParkDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Query Database for all existing parks
	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites,"
				+ " climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource,"
				+ " parkdescription, entryfee, numberofanimalspecies FROM park ORDER BY parkname asc;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			allParks.add(mapRowToPark(results));
		}
		return allParks;
	}
	
	//create park object using database query above
	public Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkcode"));
		park.setParkName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationInFeet(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		park.setInspirationalQuote(row.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		park.setParkDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		
		return park;
	}

	//select specific park with using park Id
	@Override
	public Park getParkById(String id) {
		String sql = "SELECT * FROM park WHERE parkcode = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToPark(result);
        }
        return null;
	}

}
