package uam;

import java.io.IOException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner input = new Scanner(System.in);
		String email, senha, id, nome, quantidade, valor;
		String token =  "";
		int option = 0;
        boolean endOfProgram = true;
		
		do{

			System.out.println("\nEscolha uma das opções abaixo: ");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Criar novo usuario");
            System.out.println("3 - Buscar todos os produtos");
            System.out.println("4 - Buscar produto por (ID)");
            System.out.println("5 - Adicionar novo Produto");
			System.out.println("6 - Atualizar Produto");
			System.out.println("7 - Deletar Produto");
			System.out.println("8 - Sair");
            System.out.print("Opção: ");

			option = Tool.convertStringToInt(input);

			switch (option) {
				case 1:
					System.out.print("Digite o seu e-mail a seguir: ");
					email = input.nextLine();
					System.out.print("Digite sua senha a seguir: ");
					senha = input.nextLine();
					token = Usuario.autenticar(email, senha);

					System.out.println("Token gerado: " + token);
					break;

				case 2:
					System.out.print("Digite o e-mail do novo usuario: ");
					email = input.nextLine();
					System.out.print("Digite a senha do novo usuario: ");
					senha = input.nextLine();
					System.out.print("Digite o id do perfil, sendo 1 - Admin e 2 - Comun: ");
					id = Tool.convertStringToInt(input).toString();
					
					System.out.println(Usuario.criarUsuario(token, email, senha, id));
					break;

				case 3:
					System.out.println(Produto.listarEstoque(token));
					break;

				case 4:
					System.out.print("Digite o id do produto: ");
					id = Tool.convertStringToInt(input).toString();

					System.out.println(Produto.buscarProduto(id, token));
					break;

				case 5:
					System.out.print("Digite o nome do produto: ");
					nome = input.nextLine();
					System.out.print("Digite a quantidade do produto: ");
					quantidade = input.nextLine();
					System.out.print("Digite o valor do produto: ");
					valor = Tool.convertStringToDouble(input).toString();

					System.out.println(Produto.salvarProduto(nome, quantidade, valor, token));
					break;

				case 6:
					System.out.print("Digite o id do produto: ");
					id = Tool.convertStringToInt(input).toString();
					System.out.print("Digite o nome do produto: ");
					nome = input.nextLine();
					System.out.print("Digite a quantidade do produto: ");
					quantidade = input.nextLine();
					System.out.print("Digite o valor do produto: ");
					valor = Tool.convertStringToDouble(input).toString();

					System.out.println(Produto.alterarProduto(id, nome, quantidade, valor, token));
					break;

				case 7:
					System.out.print("Digite o id do produto: ");
					id = Tool.convertStringToInt(input).toString();

					System.out.println(Produto.deletarProduto(id, token));
					break;
				
				case 8:
					endOfProgram = false;
					break;

				default:
					System.out.println("Você não digitou uma opção válida! Tente novamente!!");
					break;
			}
		}while(endOfProgram);

		input.close();
		System.out.println("Até a próxima!");

	}
}
	
