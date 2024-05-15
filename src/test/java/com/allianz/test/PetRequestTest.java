package com.allianz.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;

public class PetRequestTest {
	public static String baseUrl="https://petstore.swagger.io/v2";
	
	@Test
	public void findValidPetTest()
	{
		int petId=10;
		String resource="/pet/"+petId;
		
		String jsonString=RestAssured
		.given()
		.when().get(baseUrl+resource)
		.then().statusCode(200).extract().asString();
		
		Assert.assertTrue(jsonString.contains(":10"));
	}
	
	@Test
	public void findInvalidPetTest()
	{
		int petId=-102222;
		String resource="/pet/"+petId;
		
		String jsonString=RestAssured
		.given()
		.when().get(baseUrl+resource)
		.then().statusCode(404).extract().asString();
		
		Assert.assertTrue(jsonString.contains("Pet not found"));
	}

	@Test
	public void findPetDetailsByValidStatus()
	{
		String resource="/pet/findByStatus";
		
		String jsonString=RestAssured
		.given().queryParam("status", "pending")
		.when().get(baseUrl+resource)
		.then().statusCode(200).extract().asString();
		
		System.out.println(jsonString);
	}
	@Test
	public void findValidPetProperTest() throws JsonMappingException, JsonProcessingException
	{
		int petId=10;
		String resource="/pet/"+petId;
		
		String jsonString=RestAssured
		.given()
		.when().get(baseUrl+resource)
		.then().statusCode(200).extract().asString();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonString);
		
		Assert.assertEquals(node.get("id").asInt(), 10);
	}
}