package uam;

import java.io.IOException;

public class Cliente {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String token;
		token = Usuario.autenticar("admin@email.com","1234");
		System.out.println(Produto.listarEstoque(token));
		System.out.println(Produto.buscarProduto(1L, token));
		System.out.println(Produto.deletarProduto(1L, token));
		System.out.println(Produto.listarEstoque(token));
		System.err.println(Produto.salvarProduto("nuetlla", "5", "14.20", token));
		System.out.println(Produto.listarEstoque(token));
		System.out.println(Produto.alterarProduto(4L, "nutella", "5", "14.20", token));
		System.out.println(Produto.listarEstoque(token));

	}
}
	
