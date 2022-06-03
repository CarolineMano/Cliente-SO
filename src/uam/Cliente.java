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
		String token;
		token = Cliente.autenticar("admin@email.com","1234");
		System.out.println(listarEstoque(token));
		System.out.println(buscarProduto(1L, token));
		System.out.println(deletarProduto(1L, token));
		System.out.println(listarEstoque(token));



	}
	
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
		System.out.println(jwt);
		return "Bearer " + jwt;
	}

	public static String listarEstoque(String token) throws IOException, InterruptedException {
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/estoque"))
				.header("Authorization", token)
				.build();
		
		HttpResponse<String> response = cliente.send(request,
    		HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode() + " " + response.body();
	}

	public static String buscarProduto(Long id, String token) throws IOException, InterruptedException {

		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/estoque/" + id))
				.header("Authorization", token)
				.build();
		
		HttpResponse<String> response = cliente.send(request,
    		HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode() + " " + response.body();
	}

	public static String deletarProduto(Long id, String token) throws IOException, InterruptedException {

		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/estoque/" + id))
				.header("Authorization", token)
				.DELETE()
				.build();
		
		HttpResponse<String> response = cliente.send(request,
    		HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode();
	}
}
