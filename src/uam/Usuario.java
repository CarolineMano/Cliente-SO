package uam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Usuario {
    
    public static String autenticar(String email, String senha) throws IOException, InterruptedException {
		
		var values = new HashMap<String, String>() {{
			put("senha", senha);
			put("email", email);
		}};
		
		var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/auth"))
				.header("Content-type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		
		HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());

		String jwt = response.body();
		jwt = jwt.substring(10,jwt.length()-2);
		return "Bearer " + jwt;
	}

    public static String criarUsuario(String token, String email, String senha, String perfil) throws IOException, InterruptedException {
        
        var values = new HashMap<String, String>() {{
			put("senha", senha);
			put("email", email);
            put("perfilId", perfil);
		}};
		
		var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/auth/novo"))
				.header("Content-type", "application/json")
                .header("Authorization", token)
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		
		HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode() + " " + response.body();
    }
}
