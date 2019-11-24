package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.SurveyDao;
import com.techelevator.npgeek.model.WeatherDao;

@Controller
public class NpgeekController {
	
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;
	@Autowired
	private SurveyDao surveyDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String getIndexPage(ModelMap modelMap) {
		modelMap.put("parks", parkDao.getAllParks());
		return "index";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String getDetailPage(ModelMap modelMap, @RequestParam String id) {
		Park park = parkDao.getParkById(id);
		List<Weather> weatherList = weatherDao.getWeatherForPark(id);
		
		modelMap.put("park", park);
		modelMap.put("weather", weatherList);
		
		return "parkDetail";
	}
	
	@RequestMapping(path="/tempSelection", method=RequestMethod.POST)
	public String getTempSelection( @RequestParam String temperature,
			 						@RequestParam String park,
			 						HttpSession session
			 ) {
		
		session.setAttribute("temperature", temperature);
		
		return "redirect:/parkDetail?id=" + park;
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String getSurveyPage(ModelMap modelMap) {
		
		if (modelMap.containsAttribute("survey") == false) {
			Survey empty = new Survey();
			modelMap.put("survey", empty);
		}
		
		modelMap.put("states", Survey.STATES);
		modelMap.put("parks", parkDao.getAllParks());
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String getSurveyResults(@Valid @ModelAttribute Survey surveySubmit, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			
			redirectAttributes.addFlashAttribute("survey", surveySubmit);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}
		
		surveyDao.save(surveySubmit);
		return "redirect:/surveyResults";
	}
	
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String getSurveyResultsPage(ModelMap modelMap) {
		modelMap.put("survey", surveyDao.getSurveyResults());
		return "surveyResults";
	}

}
