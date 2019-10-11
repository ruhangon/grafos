import java.util.ArrayList;

/*
* descrição: procura resolver a busca pelos menores caminhos usando o algoritmo de Dijkstra
*/

public class Main {
	public static void main(String[] args) {
		ArrayList<No> nosCadastrados = new ArrayList<No>();
		System.out.println("Atividade 2 de grafos (algoritmo de Dijkstra)");

		Grafo grafo = new Grafo();
		grafo.isGrafoOrientado();
		grafo.isGrafoValorado();

		grafo.cadastraVertices(nosCadastrados);
		grafo.cadastraArestas(nosCadastrados);

		No origem=grafo.defineOrigem(nosCadastrados);

		grafo.adicionaNosAoGrafo(nosCadastrados);

		Dijkstra.verificaMenoresCaminhos(grafo, origem);

		System.out.println("\n\nFim do programa");


	}
}
