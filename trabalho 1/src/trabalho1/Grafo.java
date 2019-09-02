package trabalho1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

//criacao do objeto grafo para ajudar na implementa��o
public class Grafo {

	/*
	 * toda a manipula��o do grafo � feita aqui, na classe main, s� ser� passado as
	 * informa��es sobre o grafo
	 */

	private ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
	private ArrayList<Aresta> listaArestas = new ArrayList<Aresta>();
	private boolean isOrientado;
	private Boolean isValorado;

	// metodo que cadastra as arestas
	public void cadastraAresta(String valoradoSN, String orientadoSN) {
		// os m�todos abaixo mudam os booleanos de acordo com a resposta
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
			// pede o v�rtice de entrada da aresta
			do {
				try {
					System.out.println("Digite o v�rtice de entrada da " + i + "� aresta (posi��o na lista)");
					System.out.print("resposta: ");
					pos = scan.nextInt();
					scan.nextLine();
					if ((pos >= posMinimo) && (pos <= posMaximo)) {
						aresta.setVEntrada(listaVertices.get(pos - 1));
						break;
					} else {
						System.out.println("op��o inv�lida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inv�lido");
					scan.nextLine();
					pos = -1;
				}
			} while ((pos < posMinimo) || (pos > posMaximo));
			pos = -1;
			// pede o v�rtice de sa�da da aresta
			do {
				try {
					System.out.println("Digite o v�rtice de sa�da da " + i + "� aresta (posi��o na lista)");
					System.out.print("resposta: ");
					pos = scan.nextInt();
					scan.nextLine();
					if ((pos >= posMinimo) && (pos <= posMaximo)) {
						aresta.setVSaida(listaVertices.get(pos - 1));
						break;
					} else {
						System.out.println("op��o inv�lida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inv�lido");
					scan.nextLine();
					pos = -1;
				}
			} while ((pos < posMinimo) || (pos > posMaximo));
			// se for valorado pede valor, se n�o atribui 0 ao valor
			if (isValorado == false) {
				aresta.setValorAresta(0.0);
			} else {
				if (aresta.getVEntrada() != aresta.getVSaida()) {
					// com esse if, se o v�rtice de entrada for igual ao de sa�da ser� atribuido
					// valor 0
					do {
						try {
							System.out.println("Digite o valor da aresta " + i + "");
							System.out.print("resposta: ");
							aresta.setValorAresta(scan.nextDouble());
							scan.nextLine();
						} catch (InputMismatchException e) {
							System.out.println("Valor inv�lido");
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

	// metodo que ir� cadastrar os v�rtices do grafo
	public void cadastraQtdVertice() {
		Scanner scan = new Scanner(System.in);
		String operacao = "";
		do {
			System.out.println("Digite o nome do v�rtice");
			System.out.print("resposta: ");
			String nomeV = scan.nextLine();
			Vertice vertice = new Vertice();
			vertice.setNomeDoVertice(nomeV);
			listaVertices.add(vertice);
			operacao = JOptionPane.showInputDialog("Deseja cadastrar mais algum v�rtice? (S/N)");
		} while (operacao.equalsIgnoreCase("S"));
	}

	// m�todo usado para mostrar os v�rtices cadastrados
	public void mostrarVertices() {
		System.out.println();
		System.out.println("Lista de v�rtices: ");
		int ultimoDoVertice = listaVertices.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + listaVertices.get(i).getNomeDoVertice() + ", ");
		}
		System.out.println(listaVertices.size() + " - " + listaVertices.get(ultimoDoVertice).getNomeDoVertice());
	}

	// metodo que define se o grafo � valorado e muda o booleano
	public void isGrafoValorado(String valoradoSN) {
		if (valoradoSN.equalsIgnoreCase("S")) {
			isValorado = true;
		} else {
			isValorado = false;
		}
	}

	// metodo que define se o grafo � orientado e muda o booleano
	public void isGrafoOrientado(String orientadoSN) {
		if (orientadoSN.equalsIgnoreCase("S")) {
			isOrientado = true;
		} else {
			isOrientado = false;
		}
	}

}
