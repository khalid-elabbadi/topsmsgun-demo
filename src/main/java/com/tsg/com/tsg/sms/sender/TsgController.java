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

		String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJnaWQiOjIsIm5vbmNlIjoiNDM0OTcxZDk0OGVjMjE3NTQzMWU4OTcwNmI2NjIwNjkiLCJzYWsiOiIyOWU4OTIyNzRlMDM3OTM3ZTBmZTNmNmU4ODUzMmNiZiIsInN0ayI6IjdlZDAwY2FmZmMzZWQ1ZmQxZjk3MjlhNjI2NDgyYTE4IiwiYWt5Ijoic2FrIiwiaWQiOiI1MjRiOWNiNC00N2FlLTJhMTYtZjlmOS1lMmEwOGI5NTkxYTIiLCJqdGkiOiI1MjRiOWNiNC00N2FlLTJhMTYtZjlmOS1lMmEwOGI5NTkxYTIiLCJpc3MiOiJcL3RvcHNtc2d1bi1hcGlcL2FwaVwva2V5IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IiIsInVpZCI6ImU5M2RiMGQ5MmRiYjE0NTg3ODVlYWEyOGI1MDhlNDI4IiwiZXhwIjoxOTAxOTE1NjQ2LCJpYXQiOjE1OTA4NzU2NDYsInRva2VuX3R5cGUiOiJCZWFyZXIiLCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIn0.IM1gK7P-_7o0fJccvWaWodqYsJUVAn66nL4XybrMmDcLQiRmPfweCbOUjW5YJz8AU7avLuFBRg_dR3uHf_FlK0r5BZ9cmeRSNb1bYRNP2De59YHUXc3yKIr_-4diRXZbkotFYPBx0lbGqNDWx3qO9md5jEH-R6f7E5m2zYWv7lE";
		String url = "https://topsmsgun.com/topsmsgun-api/sms/direct/" + name + "/" + phone + "/" + text;

		HttpHeaders headers = new HttpHeaders();
		headers.clear();
		
		//headers.setAccept(new ArrayList<MediaType>());
		
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
