package com.example.contract;

import com.example.contract.BaseClass;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SuppressWarnings("rawtypes")
public class ContractVerifierTest extends BaseClass {

	@Test
	public void validate_find_hat_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.get("/hat/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['id']").isEqualTo(1);
			assertThatJson(parsedJson).field("['name']").isEqualTo("Test Hat 1");
			assertThatJson(parsedJson).field("['size']").isEqualTo(10);
			assertThatJson(parsedJson).field("['color']").isEqualTo("striped");
	}

	@Test
	public void validate_find_hat_by_id2() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.get("/hat/2");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['id']").isEqualTo(2);
			assertThatJson(parsedJson).field("['name']").isEqualTo("Test Hat 2");
			assertThatJson(parsedJson).field("['size']").isEqualTo(7);
			assertThatJson(parsedJson).field("['color']").isEqualTo("green");
	}

	@Test
	public void validate_find_hat_by_id3() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.get("/hat/3");

		// then:
			assertThat(response.statusCode()).isEqualTo(404);
	}

}
