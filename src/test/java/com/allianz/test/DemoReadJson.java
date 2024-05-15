package com.allianz.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoReadJson {

	@Test
	public void readJosn() throws IOException {
		FileInputStream file = new FileInputStream("src/test/resources/test_data/add_pet.json");

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(file);
		 int id=node.get("id").asInt(); 
		 System.out.println(id);
		 
		 System.out.println(node.get("name").asText());
		 System.out.println(node.get("category").get("name").asText());
	}

}