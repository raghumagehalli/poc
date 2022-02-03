package com.perficient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.model.AudienceChoice;
import com.perficient.model.Genere;
import com.perficient.repository.AudienceChoiceRepository;
import com.perficient.repository.GenereRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/feedback")
@Tag(name = "Feedback")
public class AudienceChoiceController {

	@Autowired
	AudienceChoiceRepository repository;
	@Autowired
	GenereRepository genereRepository;

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	@Operation(description = "Record feedback", summary = "Add your feedback to get more movies on this genere")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	public ResponseEntity<String> recordAudienceChoice(
			@Parameter(description = "Your Own Choice") @RequestBody AudienceChoice choice) {
		Genere genere = genereRepository.findByName(choice.getGenere());
		if (genere == null)
			genere = genereRepository.save(new Genere(choice.getGenere()));

		choice.setGenereId(genere.getGenereId());
		if (choice.getDoYouLike().equalsIgnoreCase("Yes"))
			choice.setLikes(choice.getLikes() + 1);
		else
			choice.setDislikes(choice.getDislikes() + 1);

		repository.save(choice);
		return new ResponseEntity<>("Well Done", HttpStatus.OK);

	}

}
