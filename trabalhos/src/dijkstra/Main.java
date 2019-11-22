package dijkstra;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// classe que será usada para testar parques diversos
// vértices serão os locais, como banheiro, tirolesa, lanchonete, dentre outros
// arestas serão os caminhos e seus pesos serão de acordo com a acessibilidade do caminho
public class Main {
	private static ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
	int[] vertices;

	private static int leConsole(String tipo, Scanner entrada) {
		while (true) {
			System.out.println(tipo + ":");
			String verticeInformado = entrada.nextLine().trim();
			if (verticeInformado.isEmpty()) {
				System.out.println("Programa encerrado!");
				System.exit(0);
			}
			try {
				int vertInf = Integer.parseInt(verticeInformado);
				if (vertInf >= 1) {
					return vertInf - 1;
				}
			} catch (NumberFormatException e) {
			}
			System.out.println("Vértice inválido!");
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("  Dijkstra");
		System.out.println();

		boolean grafoOrientado = false;
		int op = -1;

		// define se grafo será orientado ou não
		do {
			try {
				System.out.println("O grafo será orientado ou não orientado? \n1. Orientado, 2. Não Orientado");
				System.out.print("resposta: ");
				op = scan.nextInt();
				scan.nextLine();
				if ((op < 1) || (op > 2))
					System.out.println("opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("opção inválida");
				scan.nextLine();
				op = -1;
			}
		} while ((op < 1) || (op > 2));

		if (op == 1)
			grafoOrientado = true;
		if (op == 2)
			grafoOrientado = false;

		System.out.println();

		String novoVertice = "";
		Vertice v;
		String resposta = "S";
		// cadastra os vértices
		do {
			System.out.println("Diga o nome do vértice que você quer cadastrar");
			System.out.print("resposta: ");
			novoVertice = scan.nextLine();
			v = new Vertice();
			v.setNome(novoVertice);
			listaVertices.add(v);
			do {
				System.out.println("Você deseja cadastrar mais vértices? (S/N)");
				System.out.print("resposta: ");
				resposta = scan.nextLine();
			} while ((!resposta.equalsIgnoreCase("S")) && (!resposta.equalsIgnoreCase("N")));
		} while (!resposta.equalsIgnoreCase("N"));

		System.out.println();

		// cria grafo
		Grafo grafo = new Grafo(listaVertices.size());

		int arestaOrigem = 0;
		int arestaDestino = 0;
		int valorAresta = 0;
		resposta = "S";
		// cria lista de arestas
		do {
			v.listaVertices(listaVertices);
			// origem
			do {
				try {
					System.out.println("Digite a posição do vértice de origem, segundo a lista acima");
					System.out.print("posição: ");
					arestaOrigem = scan.nextInt();
					scan.nextLine();
					if ((arestaOrigem < 1) || (arestaOrigem > listaVertices.size()))
						System.out.println("opção inválida");
				} catch (InputMismatchException e) {
					System.out.println("opção inválida");
					scan.nextLine();
					arestaOrigem = 0;
				}
			} while ((arestaOrigem < 1) || (arestaOrigem > listaVertices.size()));
			// destino
			do {
				try {
					System.out.println("Digite a posição do vértice de destino, segundo a lista acima");
					System.out.print("posição: ");
					arestaDestino = scan.nextInt();
					scan.nextLine();
					if ((arestaDestino < 1) || (arestaDestino > listaVertices.size()))
						System.out.println("opção inválida");
				} catch (InputMismatchException e) {
					System.out.println("opção inválida");
					scan.nextLine();
					arestaDestino = 0;
				}
			} while ((arestaDestino < 1) || (arestaDestino > listaVertices.size()));
			// define peso da aresta
			do {
				try {
					System.out.println("Digite o peso dessa aresta");
					System.out.print("peso: ");
					valorAresta = scan.nextInt();
					scan.nextLine();
					if (valorAresta < 0)
						System.out.println("opção inválida");
				} catch (InputMismatchException e) {
					System.out.println("opção inválida");
					scan.nextLine();
					valorAresta = -1;
				}
			} while (valorAresta < 0);
			grafo.criaAresta(arestaOrigem - 1, arestaDestino - 1, valorAresta, grafoOrientado);
			do {
				System.out.println("Você deseja cadastrar mais arestas? (S/N)");
				System.out.print("resposta: ");
				resposta = scan.nextLine();
			} while ((!resposta.equalsIgnoreCase("S")) && (!resposta.equalsIgnoreCase("N")));
			System.out.println();
		} while (!resposta.equalsIgnoreCase("N"));

		// SOLICITA PONTO INICIAL
		v.listaVertices(listaVertices);
		System.out.println("Informe a posição do vértice onde você está");
		int origem = leConsole("Vértice atual", scan);
		System.out.println();

		// informa os melhores caminhos
		System.out.println("Caminho mais curto, partindo do vértice -> " + (v.getNome(origem, listaVertices)));

		for (int a = 0; a < listaVertices.size(); a++) {
			for (Integer i : grafo.caminho(origem, a)) {
				// se origem for diferente de destino e o i atual for igual ao destino, então
				// mostra print sem o -> e sai do laço
				if ((i == a) && (origem != a)) {
					System.out.println(v.getNome((i), listaVertices));
					break;
				}
				if (origem != a) {
					System.out.print(v.getNome((i), listaVertices) + " -> ");
				}
			}
		}

	}
}
