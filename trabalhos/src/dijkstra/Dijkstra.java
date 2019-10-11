
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {
	private static LinkedList<No> menorCaminho;

	// m�todo chamado no main para procurar os menores caminhos, partindo de um
	// v�rtice origem
	public static void verificaMenoresCaminhos(Grafo grafo, No origem) {
		System.out.println("\nMenores caminhos usando Dijkstra");
		boolean primeiraVez = true;
		origem.setDistancia(0);
		// Set assim como List serve para armazenar lista, mas diferente de List n�o
		// aceita elementos duplicados
		Set<No> nosResolvidos = new HashSet<>();
		Set<No> nosNaoResolvidos = new HashSet<>();
		nosNaoResolvidos.add(origem);

		while (nosNaoResolvidos.size() != 0) {
			No noAtual = getNoComMenorDistancia(nosNaoResolvidos);
			if (primeiraVez == true) {
				// imprime nome do n� definido como origem
				System.out.println("Origem: " + origem.getNome());
				System.out.println("destino - caminho - dist�ncia");
			} else {
				// imprime nome do n� atual
				System.out.print(noAtual.getNome() + " - ");
				// imprime caminho menor para esse n�
				for (No no : menorCaminho) {
					System.out.print(no.getNome());
				}
				// a linha abaixo serve para que o destino apare�a no caminho
				System.out.print(noAtual.getNome() +" - ");
				// menor dist�ncia para chegar a esse n�
				System.out.println(noAtual.getDistancia());
			}
			// altera primeira vez para false, para as pr�ximas passadas
			primeiraVez = false;
			nosNaoResolvidos.remove(noAtual);
			for (Entry<No, Integer> parAdjacente : noAtual.getNosVizinhos().entrySet()) {
				No noVizinho = parAdjacente.getKey();
				Integer peso = parAdjacente.getValue();

				if (!nosResolvidos.contains(noVizinho)) {
					calculaMenorDistancia(noVizinho, peso, noAtual);
					nosNaoResolvidos.add(noVizinho);
				}
			}
			nosResolvidos.add(noAtual);
		}
	}

	public static void calculaMenorDistancia(No noDeAvaliacao, Integer peso, No origem) {
		Integer distanciaOrigem = origem.getDistancia();
		if (distanciaOrigem + peso < noDeAvaliacao.getDistancia()) {
			noDeAvaliacao.setDistancia(distanciaOrigem + peso);
			menorCaminho = new LinkedList<>(origem.getMenorCaminho());
			menorCaminho.add(origem);
			noDeAvaliacao.setMenorCaminho(menorCaminho);
		}
	}

	public static No getNoComMenorDistancia(Set<No> nosNaoResolvidos) {
		No noComMenorDistancia = null;
		int menorDistancia = Integer.MAX_VALUE;
		for (No no : nosNaoResolvidos) {
			int distanciaDoNo = no.getDistancia();
			if (distanciaDoNo < menorDistancia) {
				menorDistancia = distanciaDoNo;
				noComMenorDistancia = no;
			}
		}
		return noComMenorDistancia;
	}

}
