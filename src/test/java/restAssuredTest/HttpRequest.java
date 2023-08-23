package restAssuredTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequest {
	int id;

@Test

	public void getUsers() {
		when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 1)

	public void createUsers() {
		HashMap mp = new HashMap();
		mp.put("name", "pramod");
		mp.put("job", "leader");
		id = given().contentType("application/json").body(mp).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
//		.then()
//	.statusCode(201)
//		.log().all();
//		System.out.println(id);
		

	}

	@Test(priority = 2, dependsOnMethods = { "createUsers" })
	public void updateUser() {
		HashMap mp = new HashMap();
		mp.put("name", "pramod");
		mp.put("job", "Tester");
		given().contentType("application/json").body(mp).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}
	@Test(priority=4,dependsOnMethods= {"updateUser"})
	public void deleteUser()
	{
		given()
		.when()
		.delete("https://reqres.in/api/users/" + id)
		.then()
		.statusCode(204)
		.log().all();
	}
/*	
@Test(priority=1)
public void getStudent()
{
	given()
	.when()
	.get("http://localhost:3000/students")
	.then()
	.statusCode(200)
	.log().all();
	
}*/
@Test(priority=1)
	public void getStudent1() {
		int ide;
		given().when().get("http://localhost:3000/students/1")
		.then()
		.log().all();
		
	}

}
