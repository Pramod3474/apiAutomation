package restAssuredDay2;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



/*
 * using Hashmap
 * Using external FIle
 * Json Dependncy
 * POJO
 */
public class waysTocreatePostRequestBody {
	int id;

	@Test(priority=1)

	public void GetStudentHashMap() {
		HashMap data = new HashMap();
		data.put("email", "pramod3474@gmail.com");
		data.put("first_name", "Pramod");
		data.put("last_name", "Acharya");
		data.put("Location", "Aloor");
		data.put("Phone", "9591207741");
		String CoursesArray[] = { "java", "selenium" };
		data.put("courses", CoursesArray);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).log().all().body("first_name", equalTo("Pramod"))
				.body("email", equalTo("pramod3474@gmail.com")).body("last_name", equalTo("Acharya"))
				.body("Location", equalTo("Aloor")).body("Phone", equalTo("9591207741"))
				.body("courses[0]", equalTo("java")).body("courses[1]", equalTo("selenium"))
				.header("Content-type", "application/json; charset=utf-8").header("Connection", "keep-alive").log()
				.all();

	}

	@Test(priority=2)
public void deleteStudent()
{
	given()
	.when()
	.delete("http://localhost:3000/students/7")
	.then()
	.statusCode(200);
}
	@Test(priority=1)

	public void GetStudentOrgJson() {
		
		JSONObject data=new JSONObject();
		data.put("email", "pramod3474@gmail.com");
		data.put("first_name", "Pramod");
		data.put("last_name", "Acharya");
		data.put("Location", "Aloor");
		data.put("Phone", "9591207741");
		String CoursesArray[] = { "java", "selenium" };
		data.put("courses", CoursesArray);
		System.out.println(data);
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.log().all()
		.body("first_name", equalTo("Pramod"))
		.body("email", equalTo("pramod3474@gmail.com"))
		.body("last_name", equalTo("Acharya"))
		.body("Location", equalTo("Aloor"))
		.body("Phone", equalTo("9591207741"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("selenium"))
		.header("Content-type", "application/json; charset=utf-8")
		.header("Connection", "keep-alive")
		.log().all();

	}
	@Test(priority=1)
public void createStudentExternalFIle() throws FileNotFoundException
{File file=new File(".\\body.json");
		FileReader f1=new FileReader(file);
		JSONTokener JD=new JSONTokener(f1);
		JSONObject data=new JSONObject(JD);
	given()
	.contentType("application/json")
	.body(file)
	.when()
	.post("http://localhost:3000/students")
	.then()
	.statusCode(201)
	.log().all()
	.body("first_name", equalTo("Prameela"))
	.body("email", equalTo("pramod3474@gmail.com"))
	.body("last_name", equalTo("Shanbag"))
	.body("Location", equalTo("Aloor"))
	.body("Phone", equalTo("9591207741"))
	.body("courses[0]", equalTo("java"))
	.body("courses[1]", equalTo("selenium"))
	.header("Content-type", "application/json; charset=utf-8")
	.header("Connection", "keep-alive")
	.log().all();
}
}
