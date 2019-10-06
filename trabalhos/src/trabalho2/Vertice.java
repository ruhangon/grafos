package trabalho2;

public class Vertice {
	// nome do vértice para a listagem
	private String nomeDoVertice;
	// distância do vértice para resolução de Dijkstra
	private int distancia;
	// visitado ou não para resolução de Dijkstra
	private boolean visitado;

	public String getNomeDoVertice() {
		return nomeDoVertice;
	}

	public void setNomeDoVertice(String nomeDoVertice) {
		this.nomeDoVertice = nomeDoVertice;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

}
