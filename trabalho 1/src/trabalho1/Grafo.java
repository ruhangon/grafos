package trabalho1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

//criacao do objeto grafo para ajudar na implementação
public class Grafo {

	/*
	 * toda a manipulação do grafo é feita aqui, na classe main, só será passado as
	 * informações sobre o grafo
	 */

	private ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
	private ArrayList<Aresta> listaArestas = new ArrayList<Aresta>();
	private boolean isOrientado;
	private Boolean isValorado;

	// metodo que cadastra as arestas
	public void cadastraAresta(String valoradoSN, String orientadoSN) {
		// os métodos abaixo mudam os booleanos de acordo com a resposta
		isGrafoValorado(valoradoSN);
		isGrafoOrientado(orientadoSN);
		Scanner scan = new Scanner(System.in);
		String operacao = "";
		int i = 1;
		do {
			Aresta aresta = new Aresta();
			// Vertice vEntrada = new Vertice();
			// Vertice vSaida = new Vertice();
			mostrarVertices();
			int posMinimo = 1;
			int posMaximo = listaVertices.size();
			int pos = -1;
			// pede o vértice de entrada da aresta
			do {
				try {
					System.out.println("Digite o vértice de entrada da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					pos = scan.nextInt();
					scan.nextLine();
					if ((pos >= posMinimo) && (pos <= posMaximo)) {
						aresta.setVEntrada(listaVertices.get(pos - 1));
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					pos = -1;
				}
			} while ((pos < posMinimo) || (pos > posMaximo));
			pos = -1;
			// pede o vértice de saída da aresta
			do {
				try {
					System.out.println("Digite o vértice de saída da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					pos = scan.nextInt();
					scan.nextLine();
					if ((pos >= posMinimo) && (pos <= posMaximo)) {
						aresta.setVSaida(listaVertices.get(pos - 1));
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					pos = -1;
				}
			} while ((pos < posMinimo) || (pos > posMaximo));
			// se for valorado pede valor, se não atribui 0 ao valor
			if (isValorado == false) {
				aresta.setValorAresta(0.0);
			} else {
				if (aresta.getVEntrada() != aresta.getVSaida()) {
					// com esse if, se o vértice de entrada for igual ao de saída será atribuido
					// valor 0
					do {
						try {
							System.out.println("Digite o valor da aresta " + i + "");
							System.out.print("resposta: ");
							aresta.setValorAresta(scan.nextDouble());
							scan.nextLine();
						} catch (InputMismatchException e) {
							System.out.println("Valor inválido");
							aresta.setValorAresta(0.0);
							scan.nextLine();
						}
					} while (aresta.getValorAresta() == 0);
				} else {
					aresta.setValorAresta(0.0);
				}
			}
			listaArestas.add(aresta);
			i++;
			do {
				operacao = JOptionPane.showInputDialog("Deseja cadastrar mais arestas? (S/N)");
			} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
		} while (operacao.equalsIgnoreCase("S"));
	}

	// metodo que irá cadastrar os vértices do grafo
	public void cadastraQtdVertice() {
		Scanner scan = new Scanner(System.in);
		String operacao = "";
		do {
			System.out.println("Digite o nome do vértice");
			System.out.print("resposta: ");
			String nomeV = scan.nextLine();
			Vertice vertice = new Vertice();
			vertice.setNomeDoVertice(nomeV);
			listaVertices.add(vertice);
			operacao = JOptionPane.showInputDialog("Deseja cadastrar mais algum vértice? (S/N)");
		} while (operacao.equalsIgnoreCase("S"));
	}

	// método usado para mostrar os vértices cadastrados
	public void mostrarVertices() {
		System.out.println();
		System.out.println("Lista de vértices: ");
		int ultimoDoVertice = listaVertices.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + listaVertices.get(i).getNomeDoVertice() + ", ");
		}
		System.out.println(listaVertices.size() + " - " + listaVertices.get(ultimoDoVertice).getNomeDoVertice());
	}

	// metodo que define se o grafo é valorado e muda o booleano
	public void isGrafoValorado(String valoradoSN) {
		if (valoradoSN.equalsIgnoreCase("S")) {
			isValorado = true;
		} else {
			isValorado = false;
		}
	}

	// metodo que define se o grafo é orientado e muda o booleano
	public void isGrafoOrientado(String orientadoSN) {
		if (orientadoSN.equalsIgnoreCase("S")) {
			isOrientado = true;
		} else {
			isOrientado = false;
		}
	}

}
