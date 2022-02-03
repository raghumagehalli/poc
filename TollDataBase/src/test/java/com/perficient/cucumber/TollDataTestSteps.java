package com.perficient.cucumber;

import com.perficient.TollDataBaseApplication;
import com.perficient.model.TollData;
import com.perficient.repository.TollDataRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.is;

@CucumberContextConfiguration
@SpringBootTest(classes = {TollDataBaseApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TollDataTestSteps {

    static ResponseResults latestResponse = null;
    RestTemplate restTemplate = new RestTemplateBuilder().build();
    @Autowired
    private TollDataRepository dataRepository;

    @Before
    public void setup() {
        dataRepository.deleteAll();
    }


    @When("^the user invokes /get$")
    public void userInvokesGetMethod() throws Throwable {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate.execute("http://localhost:8081/toll", HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.hadError) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }

    @Then("^the user receives response code of (\\d+)$")
    public void the_user_receives_response_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " +
                latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^receives all the toll data$")
    public void receives_all_the_toll_data() throws Throwable {
        assertThat(latestResponse.getBody(), is(Matchers.notNullValue()));
    }

    @When("^a user saves the new toll data for (.*) with registration number (.*) with a toll price of ([0-9][0-9]) in the toll (.*)$")
    public void whenTheUserSavesNewTollData(final String vehicleType,
                                            final String registartionNumber, final int tollPrice,
                                            final String tollId) throws JsonProcessingException {
     final TollData input = new TollData(vehicleType,tollPrice,registartionNumber,tollId);
        ObjectMapper Obj = new ObjectMapper();
       String jsonString =  Obj.writeValueAsString(new ArrayList<TollData>().add(input));
        System.out.println(jsonString);
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);

        restTemplate.postForEntity("http://localhost:8081/toll",input,TollData.class);

    }

    @Then("^the data with registration number (.*) get saves in the database$")
    public void newTollDataSavedToDataBase(final String registartionNumber)
    {
       List<TollData> tollData = dataRepository.findAll();

       assertThat(tollData.get(0).getRegistraionNumber(),is(registartionNumber));
    }



    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }

}
