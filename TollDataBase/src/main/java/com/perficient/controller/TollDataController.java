package com.perficient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.model.TollData;
import com.perficient.service.TollDataService;

@RestController
@RequestMapping("/toll")
public class TollDataController {

	@Autowired
	TollDataService dataService;

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<Object> getTollData() {

		return new ResponseEntity<>(dataService.getTollData(), HttpStatus.OK);

	}

	@PostMapping( produces = { "application/json" })
	public ResponseEntity<Object> addTollData(@RequestBody TollData tollDetails) {
       List<TollData> tollDataList = new ArrayList<>();
		tollDataList.add(tollDetails);
		dataService.addTollData(tollDataList);
		return new ResponseEntity<>(tollDetails, HttpStatus.OK);

	}

}
