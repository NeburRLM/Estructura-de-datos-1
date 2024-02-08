package aplicacio;

import ciutada.Ciutada;
import estructuraTaulaHash.TaulaHash;

public class AplicacioTaulaHash {
	public static void main(String args[]) {

		TaulaHash<String, Ciutada> th = new TaulaHash<String, Ciutada>();

		int cost;
		Ciutada c1 = new Ciutada("Carlos", "Martinez", "21134567K");
		Ciutada c2 = new Ciutada("Ruben", "Lopez", "74839019A");
		Ciutada c3 = new Ciutada("Laura", "Arroyo", "35109083B");
		Ciutada c4 = new Ciutada("Carla", "Fernandez", "14250912C");
		Ciutada c5 = new Ciutada("Marc", "Figueres", "09098122D");
		Ciutada c6 = new Ciutada("Aleix", "Saladie", "55678125E");
		Ciutada c7 = new Ciutada("Abril", "Martinez", "77382712F");
		Ciutada c8 = new Ciutada("Laia", "Sanchez", "87778223G");
		Ciutada c9 = new Ciutada("Anastasia", "Sanchez", "14250912C");

		th.inserir(c1.getDni(), c1);
		th.inserir(c2.getDni(), c2);
		th.inserir(c3.getDni(), c3);
		th.inserir(c9.getDni(), c9);
		System.out.println(th.toString());
		th.inserir(c4.getDni(), c4);
		System.out.println(th.toString());
		th.inserir(c5.getDni(), c5);
		th.inserir(c6.getDni(), c6);
		th.inserir(c7.getDni(), c7);
		th.inserir(c8.getDni(), c8);
		System.out.println(th.toString());

		System.out.println(th.obtenir("14250912C"));
		th.esborrar("14250912C");
		System.out.println(th.toString());
		System.out.println(th.obtenir("14250912C"));
		th.esborrar("14250912C");

		cost = th.buscar("35109083B");
		System.out.println("Cost: " + cost);
		cost = th.buscar("35109083BA");
		System.out.println("Cost: " + cost);

		System.out.println(th.obtenirClaus().toString());
		System.out.println(th.obtenirValors().toString());

	}
}