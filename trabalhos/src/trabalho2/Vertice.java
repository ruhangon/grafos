package trabalho2;

public class Vertice {
	// nome do v�rtice para a listagem
	private String nomeDoVertice;
	// dist�ncia do v�rtice para resolu��o de Dijkstra
	private int distancia;
	// visitado ou n�o para resolu��o de Dijkstra
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
