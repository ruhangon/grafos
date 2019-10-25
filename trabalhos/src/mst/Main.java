package mst;

public class Main {
	public static void main(String[] args) {
		System.out.println("Atividade 3 de grafos (algoritmos de Kruskal e de Prim)");
		System.out.println("�rvore geradora m�nima");

		Grafo grafo = new Grafo();

		grafo.isGrafoOrientado();
		grafo.isGrafoValorado();

		// n�o-orientado / valorado

		grafo.cadastraVertices();
		grafo.cadastraArestas();

		grafo.resolveKruskal();
		grafo.resolvePrim();

		System.out.println("\n\nFim do programa");

	}
}
