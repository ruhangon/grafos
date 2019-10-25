package mst;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * toda a manipulação do grafo é feita aqui, na classe com o main, só será
 * passado as informações sobre o grafo
 */
public class Grafo {
	// fila básica para ser usada dentro de fila dinâmica, para realizar o algoritmo
	// de Kruskal
	class FilaBasica {
		Conjunto conjunto;
		FilaBasica proximo;

		public FilaBasica(Conjunto c) {
			this.conjunto = c;
			this.proximo = null;
		}
	}

	// fila dinâmica para trabalhar com conjuntos
	class FilaDinamica {
		FilaBasica frente = null;
		FilaBasica ultimo = null;
		int tamanho;

		public FilaDinamica() {
			tamanho = 0;
		}

		public boolean isVazia() {
			return frente == null;
		}

		public void insere(Conjunto c) {
			FilaBasica novo = new FilaBasica(c);
			if (isVazia() == true) {
				frente = novo;
				ultimo = novo;
			} else {
				ultimo.proximo = novo;
				ultimo = novo;
			}
			tamanho++;
		}

		public int tamanho() {
			return tamanho;
		}

		public Conjunto retira() {
			Conjunto c = frente.conjunto;
			frente = frente.proximo;
			tamanho--;
			return c;
		}

		public void verFrente() {
			System.out.println("Frente: " + this.frente.conjunto.getNomeEntrada() + ""
					+ this.frente.conjunto.getNomeSaida() + "" + this.frente.conjunto.getValor());
		}
	}

	private ArrayList<Vertice> listaVertices = new ArrayList<Vertice>(); // irá armazenar os vértices cadastrados
	private int matriz[][]; // matriz que guarda posições e peso das arestas
	private boolean isOrientado;
	private Boolean isValorado;

	// cadastra vértices
	public void cadastraVertices() {
		Vertice v;
		System.out.println("\nCadastro de vértices");
		Scanner scan = new Scanner(System.in);
		String operacao = "N";
		do {
			System.out.println("Digite o nome do vértice a cadastrar");
			System.out.print("nome: ");
			String nomeV = scan.nextLine();
			v = new Vertice(nomeV);
			listaVertices.add(v);
			do {
				System.out.println("Deseja cadastrar mais vértices ao grafo? (S/N)");
				System.out.print("resposta: ");
				operacao = scan.nextLine();
			} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
		} while (!operacao.equalsIgnoreCase("N"));
	}

	// metodo que cadastra as arestas
	public void cadastraArestas() {
		Scanner scan = new Scanner(System.in);
		matriz = new int[(listaVertices.size())][(listaVertices.size())];
		String operacao = "S";
		int i = 1;
		System.out.println("\nCadastro de arestas");
		int posEnt;
		int posSai;
		do {
			Aresta aresta = new Aresta();
			mostrarVertices();
			int posMinimo = 1;
			int posMaximo = listaVertices.size();
			posEnt = -1;
			posSai = -1;
			// pede o vértice de entrada da aresta
			do {
				try {
					System.out.println("Digite o vértice de entrada da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					posEnt = scan.nextInt();
					scan.nextLine();
					if ((posEnt >= posMinimo) && (posEnt <= posMaximo)) {
						aresta.setVEntrada(listaVertices.get(posEnt - 1));
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					posEnt = -1;
				}
			} while ((posEnt < posMinimo) || (posEnt > posMaximo));
			// pede o vértice de saída da aresta
			do {
				try {
					System.out.println("Digite o vértice de saída da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					posSai = scan.nextInt();
					scan.nextLine();
					if ((posSai >= posMinimo) && (posSai <= posMaximo)) {
						aresta.setVSaida(listaVertices.get(posSai - 1));
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					posSai = -1;
				}
			} while ((posSai < posMinimo) || (posSai > posMaximo));
			// o if abaixo verifica se a aresta já existe, se não existir aí sim insere ela
			if (existeAresta((posEnt - 1), (posSai - 1)) == false) {
				// se for valorado pede valor, se não cadastra como não valorado
				// No caso dessa atividade os grafos serão valorados
				if (isValorado == false) {
					if (aresta.getVEntrada() != aresta.getVSaida())
						aresta.setValor(1);
					if (aresta.getVSaida() != aresta.getVEntrada())
						aresta.setValor(1);
					if (aresta.getVEntrada() == aresta.getVSaida())
						aresta.setValor(0);
				} else {
					if (aresta.getVEntrada() != aresta.getVSaida()) {
						// com esse if, se o vértice de entrada for igual ao de saída será atribuido
						// valor 0
						do {
							try {
								System.out.println("Digite o valor da aresta " + i + "");
								System.out.print("resposta: ");
								aresta.setValor(scan.nextInt());
								scan.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Valor inválido");
								aresta.setValor(0);
								scan.nextLine();
							}
						} while (aresta.getValor() == 0);
					} else {
						aresta.setValor(0);
					}
				}
				matriz[posEnt - 1][posSai - 1] = aresta.getValor();
				// faz o caminho contrário na matriz
				matriz[posSai - 1][posEnt - 1] = aresta.getValor();
				i++;
				do {
					System.out.println("Deseja cadastrar mais arestas? (s/n)");
					System.out.print("resposta: ");
					operacao = scan.nextLine();
				} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
			} else {
				System.out.println("A aresta já existe no grafo. \nInsira outra aresta.");
			}
		} while (!operacao.equalsIgnoreCase("N"));
	}

	// verifica se aresta já existe no grafo e retorna um booleano
	public boolean existeAresta(int posEnt, int posSai) {
		if (matriz[posEnt][posSai] != 0)
			return true;
		return false;
	}

	// método usado para mostrar os vértices cadastrados
	public void mostrarVertices() {
		System.out.println("Lista de vértices: ");
		int ultimoDoVertice = listaVertices.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + listaVertices.get(i).getNome() + ", ");
		}
		System.out.println(listaVertices.size() + " - " + listaVertices.get(ultimoDoVertice).getNome());
	}

	// passa lista de arestas para lista de conjunto, para auxiliar em Kruskal
	public ArrayList<Conjunto> retornaConjuntos() {
		ArrayList<Conjunto> novosConjuntos = new ArrayList<Conjunto>();
		Conjunto conjAux;
		for (int i = 0; i < listaVertices.size(); i++) {
			for (int j = 0; j < listaVertices.size(); j++) {
				conjAux = new Conjunto();
				if (matriz[i][j] != 0) {
					conjAux.setNomeEntrada(mostraNome(i));
					conjAux.setNomeSaida(mostraNome(j));
					conjAux.setValor(matriz[i][j]);
					novosConjuntos.add(conjAux);
				}
			}
		}
		return novosConjuntos;
	}

	// método para resolver Kruskal com as arestas obtidas, usa conjuntos unitários
	public void resolveKruskal() {
		System.out.println("\nAlgoritmo de Kruskal");
		ArrayList<Conjunto> listaConjuntos = retornaConjuntos(); // lista de conjuntos que será ordenada
		FilaDinamica filaConjuntos = new FilaDinamica(); // receberá a lista de conjuntos ordenada para uma fila
		// abaixo ordena os conjuntos por peso, em ordem crescente
		listaConjuntos.sort((a, b) -> a.compareTo(b));
		// adiciona conjuntos a uma fila
		for (Conjunto c : listaConjuntos) {
			filaConjuntos.insere(c);
		}
		String[] pai = new String[listaVertices.size()];
		for (int i = 0; i < listaVertices.size(); i++) {
			pai[i] = listaVertices.get(i).getNome();
		}
		ArrayList<Conjunto> arvoreGeradoraMinima = new ArrayList<>();
		int indice = 0;
		while (indice < (listaVertices.size() - 1)) {
			Conjunto conj = filaConjuntos.retira();
			int posVEntrada = retornaPosVertice(conj.getNomeEntrada());
			String pegaX = procura(pai, conj.getNomeEntrada(), posVEntrada);
			int posVSaida = retornaPosVertice(conj.getNomeSaida());
			String pegaY = procura(pai, conj.getNomeSaida(), posVSaida);
			if (pegaX.equalsIgnoreCase(pegaY)) {
				// ignorar, irá criar ciclo
			} else {
				arvoreGeradoraMinima.add(conj);
				indice++;
				uniao(pai, pegaX, pegaY);
			}
		}
		if (arvoreGeradoraMinima.size() == (listaVertices.size() - 1)) {
			mostraArvoreKruskal(arvoreGeradoraMinima);
		} else {
			System.out.println("Não foi possível formar uma árvore");
		}
	}

	// busca a posição de um vértice no grafo, para auxiliar a resolução de Kruskal
	public int retornaPosVertice(String nome) {
		int posNome = 0;
		for (int pos = 0; pos < listaVertices.size(); pos++) {
			if (nome.equalsIgnoreCase(listaVertices.get(pos).getNome())) {
				posNome = pos;
				break;
			}
		}
		return posNome;
	}

	public String procura(String[] pai, String vertice, int posV) {
		if (!pai[posV].equalsIgnoreCase(vertice))
			return procura(pai, pai[posV], posV);
		return vertice;
	}

	public void uniao(String[] pai, String x, String y) {
		int posX = retornaPosVertice(x);
		String pegaXPai = procura(pai, x, posX);
		int posY = retornaPosVertice(y);
		String pegaYPai = procura(pai, y, posY);
		int posYPai = retornaPosVertice(pegaYPai);
		pai[posYPai] = pegaXPai;
	}

	// mostra a árvore que se formou, usado para auxiliar na resolução de kruskal
	public void mostraArvoreKruskal(ArrayList<Conjunto> arvore) {
		int somaCaminho = 0;
		System.out.println("Modelo para resultado");
		System.out.println("ida, chegada = valor do caminho");
		for (int i = 0; i < arvore.size(); i++) {
			System.out.println(arvore.get(i).getNomeEntrada() + ", " + arvore.get(i).getNomeSaida() + " = "
					+ arvore.get(i).getValor());
			somaCaminho += arvore.get(i).getValor();
		}
		System.out.println("Soma total: " + somaCaminho);
	}

	// método para resolver Prim
	public void resolvePrim() {
		System.out.println("\nAlgoritmo de Prim");
		boolean[] arvoreGeradoraMinima = new boolean[listaVertices.size()];
		Aresta[] arvore = new Aresta[listaVertices.size()];
		int[] distancia = new int[listaVertices.size()];
		for (int i = 0; i < listaVertices.size(); i++) {
			distancia[i] = Integer.MAX_VALUE;
			arvore[i] = new Aresta();
		}
		distancia[0] = 0;
		arvore[0] = new Aresta();
		arvore[0].setPred(-1);
		// organiza resultados acima
		// cria árvore geradora mínima
		for (int i = 0; i < listaVertices.size(); i++) {
			int vertice = retornaVerticeComMenorPeso(arvoreGeradoraMinima, distancia);
			arvoreGeradoraMinima[vertice] = true;
			for (int j = 0; j < listaVertices.size(); j++) {
				if (matriz[vertice][j] > 0) {
					if (arvoreGeradoraMinima[j] == false && matriz[vertice][j] < distancia[j]) {
						distancia[j] = matriz[vertice][j];
						arvore[j].setPred(vertice);
						arvore[j].setValor(distancia[j]);
					}
				}
			}
		}
		if (arvorePrimIsFull(arvore) == true) {
			mostraArvorePrim(arvore);
		} else {
			System.out.println("Não foi possível formar uma árvore");
		}
	}

	// encontra o vértice adjacente com o menor peso
	public int retornaVerticeComMenorPeso(boolean[] arvoreGeradoraMinima, int[] distancia) {
		int distanciaMinima = Integer.MAX_VALUE;
		int vertice = -1;
		for (int i = 0; i < listaVertices.size(); i++) {
			if (arvoreGeradoraMinima[i] == false && distanciaMinima > distancia[i]) {
				distanciaMinima = distancia[i];
				vertice = i;
			}
		}
		return vertice;
	}

	// retorna se a árvore de Prim foi completada
	public boolean arvorePrimIsFull(Aresta[] arvore) {
		for (int i = 1; i < listaVertices.size(); i++) {
			if (arvore[i].getValor() == 0)
				return false;
		}
		return true;
	}

	// mostraárvore formada por Prim, para auxiliar Prim
	public void mostraArvorePrim(Aresta[] arvore) {
		int somaFinal = 0;
		System.out.println("Modelo para resultado");
		System.out.println("ida, chegada = valor do caminho");
		for (int i = 1; i < listaVertices.size(); i++) {
			System.out.println(mostraNome(arvore[i].getPred()) + ", " + mostraNome(i) + " = " + arvore[i].getValor());
			somaFinal += arvore[i].getValor();
		}
		System.out.println("Soma total: " + somaFinal);
	}

	// mostra o nome do vértice da posição, para auxiliar Prim
	public String mostraNome(int pos) {
		return listaVertices.get(pos).getNome();
	}

	// metodo que define se o grafo é orientado
	public void isGrafoOrientado() {
		isOrientado = false;
	}

	// metodo que define se o grafo é valorado
	public void isGrafoValorado() {
		isValorado = true; // grafos serão valorados
		// Scanner scan = new Scanner(System.in);
		// String valorado;
		// do {
		// System.out.println("O grafo será valorado? (S/N)");
		// System.out.print("resposta: ");
		// valorado = scan.nextLine();
		// } while ((!valorado.equalsIgnoreCase("S")) &&
		// (!valorado.equalsIgnoreCase("N")));
		// if (valorado.equalsIgnoreCase("S")) {
		// isValorado = true;
		// } else {
		// isValorado = false;
		// }
	}

}
