package com.demo;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.jayway.jsonpath.JsonPath;

public class PayloadExtractor implements ResultHandler {

	private MvcResult result;
	private ObjectMapper jsonMapper;

	public PayloadExtractor(ObjectMapper jsonMapper) {
		this.jsonMapper = jsonMapper;
	}

	@Override
	public void handle(MvcResult result) throws Exception {
		this.result = result;
	}
	
	public <T> T as(Class<T> payloadClass) throws Exception {
		return jsonMapper.convertValue(extractDataPayloadFromHttpBody(), payloadClass);
	}
	
	public <T> List<T> asListOf(Class<T> payloadClass) throws UnsupportedEncodingException {
		CollectionType listType = jsonMapper.getTypeFactory().constructCollectionType(List.class, payloadClass);
		return jsonMapper.convertValue(extractDataPayloadFromHttpBody(), listType);
	}
	
	private Object extractDataPayloadFromHttpBody() throws UnsupportedEncodingException {
		String body = result.getResponse().getContentAsString();
		JsonPath jsonPath = JsonPath.compile("$");
		return jsonPath.read(body);
	}
	
}
