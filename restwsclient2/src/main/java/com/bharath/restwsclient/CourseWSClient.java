package com.bharath.restwsclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bharath.restwsclient.model.Course;

public class CourseWSClient {

	private static final String COURSEZ = "/coursedetails";
	private static final String COURSE_SERVICE_URL = "http://localhost:8080/restws/services/courses";

	public static void main(String[] args)
	{
		Client client = ClientBuilder.newClient(); //communication channel between server application and restful client
		
		WebTarget target = client.target(COURSE_SERVICE_URL).path("/coursedetails").path("/{id}").resolveTemplate("id", 123);
		
		Builder request = target.request();
		
		Course course = request.get(Course.class);
		
		System.out.println(course.getId());
		System.out.println(course.getName());
		System.out.println(course.getPrice());
		System.out.println(course.getRating());
		System.out.println(course.getTaughtBy());
		
		course.setName("Java");
		course.setPrice(5000);
		course.setRating(3);
		course.setTaughtBy("Mr. Sharma");
		
		WebTarget putTarget = client.target(COURSE_SERVICE_URL).path("/coursedetails");
		Response updateResponse = putTarget.request().put(Entity.entity(course, MediaType.APPLICATION_XML));
		System.out.println(updateResponse.getStatus());
		updateResponse.close();
		
		Course newDetails = new Course();
		newDetails.setName("Python");
		newDetails.setPrice(4000);
		newDetails.setRating(2);
		newDetails.setTaughtBy("Mr. Singh");
		
		WebTarget postTarget = client.target(COURSE_SERVICE_URL).path(COURSEZ);
		Course createdCourse = postTarget.request().post(Entity.entity(course, MediaType.APPLICATION_XML), Course.class);
		System.out.println("Created Patient ID "+createdCourse.getId());
		client.close();
		
	}
	
}
