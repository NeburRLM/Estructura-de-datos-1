package aplicacio;

import ciutada.Ciutada;
import estructuraLlistaDoble.LlistaDobleEncadenada;

/**
 * 
 * @author RUBÉN LÓPEZ MARTÍNEZ
 * 
 *         Main joc de proves
 */

public class AplicacioLlistaDoble {

	public static void main(String[] args) {
		// creem la llista
		LlistaDobleEncadenada<Ciutada> ld = new LlistaDobleEncadenada<Ciutada>();

		int cost;
		// creem els ciutadans
		Ciutada c1 = new Ciutada("Carles", "Martinez", "123456789A");
		Ciutada c2 = new Ciutada("Marc", "Perez", "987654321B");
		Ciutada c3 = new Ciutada("Abril", "Arroyo", "648394821C");
		Ciutada c4 = new Ciutada("Ruben", "Lopez", "21110451L");
		Ciutada c5 = new Ciutada("Maria", "Figueres", "793827459G");
		Ciutada c6 = new Ciutada("Alfons", "Saladie", "693826145T");
		Ciutada c7 = new Ciutada("Adria", "Martinez", "216622891D");
		Ciutada c8 = new Ciutada("Carla", "Fernandez", "432912143I");

		ld.inserir(c1);
		System.out.println(ld.toString());
		System.out.println("Longitud " + ld.longitud());

		ld.inserir(c2);
		ld.inserir(c3);
		ld.inserir(c4);
		System.out.println(ld.toString());
		System.out.println("Longitud " + ld.longitud());

		ld.inserir(c5);
		System.out.println(ld.toString());
		System.out.println("Longitud " + ld.longitud());

		ld.inserir(-1, c6);
		System.out.println(ld.toString());
		System.out.println("Longitud " + ld.longitud());

		ld.esborrar(4);
		System.out.println(ld.toString());
		System.out.println("Longitud " + ld.longitud());

		System.out.println(ld.obtenir(9));

		ld.inserir(0, c7);
		System.out.println(ld.toString());
		System.out.println(ld.longitud());

		cost = ld.buscar(c1);
		System.out.println("Cost: " + cost);

		cost = ld.buscar(c5);
		System.out.println("Cost: " + cost);

		ld.inserir(1, c8);
		System.out.println(ld.toString());
		System.out.println("Longitud " + ld.longitud());

		System.out.println(ld.mostraInversa());

		// Iterable
		for (Ciutada c : ld) {
			System.out.println(c.toString());
		}

	}
}
