package com.tsg.com.tsg.sms.sender;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class TsgController {

	@Autowired
	private RestTemplate rt;

	@GetMapping(value = "/tsg/send/{name}/{phone}/{text}")
	public HttpEntity<String> sendSms(@PathVariable("name") String name, @PathVariable("phone") String phone,
			@PathVariable("text") String text) {

		String apiKey = "YOUR API KEY HERE";
		String url = "https://topsmsgun.com/topsmsgun-api/sms/direct/" + name + "/" + phone + "/" + text;

		HttpHeaders headers = new HttpHeaders();
		headers.clear();
		
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		headers.add("Accept-Language", "fr");
		headers.add("Authorization", "Bearer " + apiKey);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		HttpEntity<String> result = null;

		try {
			result = rt.exchange(url, HttpMethod.GET, entity, String.class);
		} catch (HttpClientErrorException ex) {
			String error = ex.getMessage();
			result = null;
		}

		return result;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
