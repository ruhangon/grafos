package mst;

public class Main {
	public static void main(String[] args) {
		System.out.println("Atividade 3 de grafos (algoritmos de Kruskal e de Prim)");
		System.out.println("Árvore geradora mínima");

		Grafo grafo = new Grafo();

		grafo.isGrafoOrientado();
		grafo.isGrafoValorado();

		// não-orientado / valorado

		grafo.cadastraVertices();
		grafo.cadastraArestas();

		grafo.resolveKruskal();
		grafo.resolvePrim();

		System.out.println("\n\nFim do programa");

	}
}
