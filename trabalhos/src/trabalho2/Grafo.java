package trabalho2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//cria��o do objeto grafo para ajudar na implementa��o
public class Grafo {

	/*
	 * toda a manipula��o do grafo � feita aqui, na classe com o main, s� ser�
	 * passado as informa��es sobre o grafo
	 */

	private ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
	private ArrayList<Aresta> listaArestas = new ArrayList<Aresta>();
	private boolean isOrientado;
	private Boolean isValorado;

	// metodo que cadastra as arestas
	public void cadastraAresta() {
		Scanner scan = new Scanner(System.in);
		char operacao = 's';
		int i = 1;
		System.out.println("\nCadastro de arestas");
		/*
		 * Os dois v�rtices aabaixo pegar�o o mesmo valor dos v�rtices inseridos no la�o
		 * de repeti��o abaixo Dessa forma � poss�vel fazer uma compara��o usando um
		 * m�todo para saber se j� existe na lista a aresta em quest�o
		 */
		Vertice vEnt = new Vertice();
		Vertice vSai = new Vertice();
		do {
			Aresta aresta = new Aresta();
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
						vEnt = aresta.getVEntrada();
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
						vSai = aresta.getVSaida();
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
			// o if abaixo verifica se a aresta j� existe, se n�o existir a� sim insere ela
			if (existeAresta(vEnt, vSai) == false) {
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
				// caso n�o seja orientado � preciso adicionar o caminho contr�rio, isso � feito
				// abaixo
				if (isOrientado == false) {
					if (vEnt != vSai) {
						Vertice vAux = new Vertice();
						Aresta aAux = new Aresta();
						vAux = vEnt;
						vEnt = vSai;
						vSai = vAux;
						aAux.setVEntrada(vEnt);
						aAux.setVSaida(vSai);
						aAux.setValorAresta(aresta.getValorAresta());
						listaArestas.add(aAux);
					}
				}
				i++;
				do {
					System.out.println("Deseja cadastrar mais arestas? (s/n)");
					System.out.print("resposta: ");
					operacao = scan.nextLine().charAt(0);
				} while ((operacao != 's') && (operacao != 'n'));
			} else {
				System.out.println("A aresta j� existe no grafo. \nInsira outra aresta.");
			}
		} while (operacao != 'n');
	}

	// metodo que ir� cadastrar os v�rtices do grafo
	public void cadastraVertice() {
		Scanner scan = new Scanner(System.in);
		char operacao = 's';
		System.out.println("\nCadastro de v�rtices");
		do {
			System.out.println("Digite o nome do v�rtice");
			System.out.print("resposta: ");
			String nomeV = scan.nextLine();
			Vertice vertice = new Vertice();
			vertice.setNomeDoVertice(nomeV);
			listaVertices.add(vertice);
			do {
				System.out.println("Deseja cadastrar mais algum v�rtice? (s/n)");
				System.out.print("resposta: ");
				operacao = scan.nextLine().charAt(0);
			} while ((operacao != 's') && (operacao != 'n'));
		} while (operacao != 'n');
	}

	// verifica se aresta j� existe no grafo e retorna um booleano
	public boolean existeAresta(Vertice vEnt, Vertice vSai) {
		for (int i = 0; i < listaArestas.size(); i++) {
			if (listaArestas.get(i).getVEntrada() == vEnt) {
				if (listaArestas.get(i).getVSaida() == vSai) {
					return true;
				}
			}
		}
		return false;
	}

	// m�todo usado para mostrar os v�rtices cadastrados
	public void mostrarVertices() {
		System.out.println("Lista de v�rtices: ");
		int ultimoDoVertice = listaVertices.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + listaVertices.get(i).getNomeDoVertice() + ", ");
		}
		System.out.println(listaVertices.size() + " - " + listaVertices.get(ultimoDoVertice).getNomeDoVertice());
	}

	// metodo que define se o grafo � valorado
	public void isGrafoValorado() {
		Scanner scan = new Scanner(System.in);
		char valorado;
		do {
			System.out.println("O grafo ser� valorado? (s/n)");
			System.out.print("resposta: ");
			valorado = scan.nextLine().charAt(0);
		} while ((valorado != 's') && (valorado != 'n'));
		if (valorado == 's') {
			isValorado = true;
		} else {
			isValorado = false;
		}
	}

	// metodo que define se o grafo � orientado
	public void isGrafoOrientado() {
		Scanner scan = new Scanner(System.in);
		char orientado;
		do {
			System.out.println("O grafo ser� orientado? (s/n)");
			System.out.print("resposta: ");
			orientado = scan.nextLine().charAt(0);
		} while ((orientado != 's') && (orientado != 'n'));
		if (orientado == 's') {
			isOrientado = true;
		} else {
			isOrientado = false;
		}
	}

	// metodo que faz a lista de arestas, somente para conferir se o cadastro de
	// arestas ficou correto
	public void listaDeArestas() {
		System.out.println();
		System.out.println("Lista de arestas: ");
		for (int i = 0; i < listaArestas.size(); i++) {
			if (isValorado == true) {
				System.out.println(listaArestas.get(i).getVEntrada().getNomeDoVertice().toUpperCase() + ""
						+ listaArestas.get(i).getVSaida().getNomeDoVertice().toUpperCase() + ""
						+ listaArestas.get(i).getValorAresta());
			} else {
				System.out.println(listaArestas.get(i).getVEntrada().getNomeDoVertice().toUpperCase() + ""
						+ listaArestas.get(i).getVSaida().getNomeDoVertice().toUpperCase());
			}
		}
	}

}