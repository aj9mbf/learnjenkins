package studentclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;

public class StudentClient {

	public static final String STUDENT_URI="http://localhost:8080/crudtrial/webapi/students";
	
	public static String testGetStudentAll() {
		ClientConfig config= new ClientConfig();
		Client client= ClientBuilder.newClient(config);
		WebTarget target=client.target(STUDENT_URI);
		String res=target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return res;
	}
	
	public static void main(String[] args) {
		
		System.out.println(StudentClient.testGetStudentAll());
		
	}
	
	
}
