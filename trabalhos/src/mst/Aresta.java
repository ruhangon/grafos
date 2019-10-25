package mst;


public class Aresta {
	// vértice de entrada para a conexão com o outro vértice (saída)
	private Vertice vEntrada;
	// vértice de saída para a conexão com o outro vértice (entrada)
	private Vertice vSaida;
	// valor da aresta 
	private int valor;
	// armazena predecessor
	private int pred;

	public Aresta() {
	}

	public Vertice getVEntrada() {
		return vEntrada;
	}

	public void setVEntrada(Vertice vEntrada) {
		this.vEntrada = vEntrada;
	}

	public Vertice getVSaida() {
		return vSaida;
	}

	public void setVSaida(Vertice vSaida) {
		this.vSaida = vSaida;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPred() {
		return pred;
	}

	public void setPred(int pred) {
		this.pred = pred;
	}

}
