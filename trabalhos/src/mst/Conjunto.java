package mst;


// classe para trabalhar com conjunto, para resolver Kruskal
public class Conjunto implements Comparable<Conjunto> {
	private String nomeEntrada;
	private String nomeSaida;
	private int valor;

	public Conjunto() {
	}

	public Conjunto(String nomeEntrada, String nomeSaida, int valor) {
		this.nomeEntrada = nomeEntrada;
		this.nomeSaida = nomeSaida;
		this.valor = valor;
	}

	public int compareTo(Conjunto outroConjunto) {
		if (this.valor < outroConjunto.valor)
			return -1;
		if (this.valor > outroConjunto.valor)
			return 1;
		return 0;
	}

	public String getNomeEntrada() {
		return nomeEntrada;
	}

	public void setNomeEntrada(String nomeEntrada) {
		this.nomeEntrada = nomeEntrada;
	}

	public String getNomeSaida() {
		return nomeSaida;
	}

	public void setNomeSaida(String nomeSaida) {
		this.nomeSaida = nomeSaida;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
