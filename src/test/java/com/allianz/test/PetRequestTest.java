package com.allianz.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PetRequestTest {

	public static String baseUrl="https://petstore.swagger.io/v2";
	@Test
	public void findValidPetTest()
	{
		int petId=10;
		String resource = "/pet/"+petId;
		
		String jsonString = RestAssured
		.given()
		.when().get(baseUrl+resource)
		.then().statusCode(200).extract().asString();
		
		Assert.assertTrue(jsonString.contains(":10"));
	}
	
	@Test
	public void findInvalidPetTest()
	{
		int petId=1000;
		String resource = "/pet/"+petId;
		
		String jsonString = RestAssured
		.given()
		.when().get(baseUrl+resource)
		.then().statusCode(404).extract().asString();
		
		Assert.assertTrue(jsonString.contains("Pet not found"));
	}
}
