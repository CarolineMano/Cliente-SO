package uam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Cliente {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Cliente.pegarToken();
	}
	
	public static void pegarToken() throws IOException, InterruptedException {
		
		var values = new HashMap<String, String>() {{
			put("senha", "1234");
			put("email", "admin@email.com");
		}};
		
		var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/auth"))
				.header("Content-type", "application/json")
				.header("Authoriztion", "Bearer " + jtw)
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		
		HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.body());		
	}
}
