package dijkstra;
import java.util.ArrayList;

public class Vertice {
	private String nome;

	public void listaVertices(ArrayList<Vertice> lista) {
		System.out.println("Lista de vértices");
		for (int i=0; i<lista.size(); i++) {
			System.out.println("Pos: "+ (i+1) +" - nome: "+ (lista.get(i).getNome()));
		}
	}

	public String getNome(int pos, ArrayList<Vertice> lista) {
		return lista.get(pos).nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
