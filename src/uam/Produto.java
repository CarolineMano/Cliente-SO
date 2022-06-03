package uam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Produto {

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

	public static String buscarProduto(String id, String token) throws IOException, InterruptedException {

		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/estoque/" + id))
				.header("Authorization", token)
				.build();
		
		HttpResponse<String> response = cliente.send(request,
    		HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode() + " " + response.body();
	}

	public static String deletarProduto(String id, String token) throws IOException, InterruptedException {

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

	public static String salvarProduto(String nome, String quantidade, String valor, String token) throws IOException, InterruptedException {
		
		var values = new HashMap<String, String>() {{
			put("nome", nome);
			put("quantidade", quantidade);
			put("valorUnitario", valor);
		}};
		
		var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/estoque"))
				.header("Content-type", "application/json")
				.header("Authorization", token)
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		
		HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode() + " " + response.body();
	}

	public static String alterarProduto(String id, String nome, String quantidade, String valor, String token) throws IOException, InterruptedException {
		
		var values = new HashMap<String, String>() {{
			put("nome", nome);
			put("quantidade", quantidade);
			put("valorUnitario", valor);
		}};
		
		var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/v1/estoque/" + id))
				.header("Content-type", "application/json")
				.header("Authorization", token)
				.PUT(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		
		HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());

		return "Status: " + response.statusCode() + " " + response.body();
	}
}

