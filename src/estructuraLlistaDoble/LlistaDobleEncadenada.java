package estructuraLlistaDoble;

import java.util.Iterator;

import ciutada.CiutadaIterator;
import excepcions.*;

/**
 * 
 * @author RUBÉN LÓPEZ MARTÍNEZ
 * 
 *         Esctructura de la llista doblement encadenada
 */

public class LlistaDobleEncadenada<T extends Comparable<T>> implements Iterable<T>, TADLlistaDoblementEncadenada<T> {
	private NodeLlista<T> primer, ultim;
	private int nodes;

	/**
	 * Métode constructor per crear la llista
	 */
	public LlistaDobleEncadenada() {
		this.primer = null; // variable primer que contindrà el node del principi de la llista
		this.ultim = null; // variable ultim que contindrà el node del final de la llista
		this.nodes = 0; // quantitat de nodes que hi ha a la llista (tamany de la llista)
	}

	/**
	 * Métode per saber la quantitat de nodes actuals que té la llista
	 * 
	 * @return nodes -> quntitat de nodes
	 */
	public int longitud() {
		return nodes;
	}

	/**
	 * Métode per saber si la llista és buida o no
	 * 
	 * @return primer -> si la llista és buida o no (si el primer node és null)
	 */
	public boolean esBuit() {
		return primer == null;
	}

	/**
	 * Métode per insertar un nou node amb un valor al final de la llista
	 * 
	 * @param data -> valor nou que es vol insertar en la llista
	 */
	public void inserir(T data) {
		NodeLlista<T> nou = new NodeLlista<T>(data); // creem nou node amb el valor a introduïr
		if (!esBuit()) { // si la llista no és buida
			ultim.setSeguent(nou);
			nou.setAnterior(ultim);
			ultim = nou;
		} else { // si la llista és buida
			primer = nou; // el primer node serà igual al nou valor
			ultim = nou; // l'ultim node serà igual al nou valor
		}
		nodes++; // sumem un element insertat
	}

	/**
	 * Métode per insertar un nou node amb un valor a una posició determinada de la
	 * llista
	 * 
	 * @param posicio -> posició de la llista a la qual és vol insertar l'element
	 * @param data    -> valor nou que es vol insertar en la llista
	 */
	public void inserir(int posicio, T data) {
		NodeLlista<T> nou = new NodeLlista<T>(data); // creem nou node amb el valor a introduïr
		try {
			if (posicio < 0 || posicio > nodes) { // posició incorrecte si l'index està fora de rang
				throw new ExepcioPosInsercio();
			}

			if (esBuit() || posicio == 0) { // si la llista es buida o l'index és 0 s'inserta al principi
				if (nodes == 0) { // buida
					primer = nou; // el primer node serà igual al nou valor
					ultim = nou; // l'ultim node serà igual al nou valor
				} else { // si no és buida
					nou.setSeguent(primer); // el nou node apuntarà al següent que hi havia al principi
					primer.setAnterior(nou); // el node que hi havia al principi apuntarà anterior al nou node
					primer = nou; // el nou node serà el primer de la llista

				}
			} else {
				if (nodes == posicio) { // si l'índex és igual a la quantitat de nodes ho inserta a l'última posició
					nou.setAnterior(ultim); // el anterior del nou node serà el que estava anteriorment últim
					ultim.setSeguent(nou); // el següent node de l'últim node que hi havia serà el nou insertat
					ultim = nou; // el nou node insertat serà l'últim de la llista
				} else {
					if (nodes != posicio && posicio != 0) { // si no es compleix cap cas especial
						NodeLlista<T> aux = primer; // nou node començarà pel primer de la llista
						for (int i = 0; i < posicio - 1; i++) { // va buscant l'índex on es vol insertar el nou node
							aux = aux.getSeguent(); // aux augmenta a un node (va recorrent la llista)
						}

						NodeLlista<T> aux2 = aux.getSeguent(); // una vegada trobat l'índex aux2, agafem l'altra node
																// superior

						// canvi nodes (inserció)
						nou.setAnterior(aux); // l'anterior al nou node serà el node inferior (aux)
						nou.setSeguent(aux2); // el següent al nou node serà el node superior (aux2)
						aux2.setAnterior(nou); // l'anterior al node superior serà el nou node
						aux.setSeguent(nou); // el següent al node inferior serà el nou node
					}
				}
			}
			nodes++; // sumem un element insertat
		} catch (ExepcioPosInsercio e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Métode per obtenir una dada a partir d'un índex corresponent de la llista
	 * 
	 * @param posicio -> posició de la llista el qual es vol obtenir el valor
	 * @return valor.getValor() -> valor que conté el node en la posició determinada
	 *         introduïda per paràmetre
	 */
	public T obtenir(int posicio) {
		NodeLlista<T> valor = null;
		int i = 0; // contador bucle
		try {
			if (posicio < 0 || posicio >= nodes) { // posició incorrecta si l'index està fora de rang
				throw new ExcepcioPosObtenir();
			}
			NodeLlista<T> aux = primer; // inicializa el node auxiliar amb el valor del primer de la llista

			while (i != posicio) { // bucle fins la posició determinada a consultar
				aux = aux.getSeguent(); // aux augmenta a un node (va recorrent la llista)
				i++;
			}
			valor = aux;

		} catch (ExcepcioPosObtenir e) {
			System.out.println(e.getMessage());
		}
		if (valor == null) {
			return null;
		}
		return valor.getValor();

	}

	/**
	 * Métode per eliminar una dada a partir d'un índex corresponent de la llista
	 * 
	 * @param posicio -> posició de la llista en la qual es vol eliminar el valor
	 */
	public void esborrar(int posicio) {
		try {
			if (posicio < 0 || posicio >= nodes) { // posició incorrecta si l'index està fora de rang
				throw new ExepcioPosEsborrar();
			}

			if (posicio == 0) { // si es vol eliminar el node de la primera posició
				if (primer.getSeguent() == null) { // si només hi ha un node a la llista
					primer = null;
					ultim = null;
				} else { // si hi ha més d'un node
					primer = primer.getSeguent(); // el node de la posició següent serà el nou primer de la llista
					primer.setAnterior(null); // eliminem al que apuntava (a l'anterior) el node nou primer de la llista
				}

			} else {
				if (posicio == nodes - 1) { // si es vol eliminar el node de l'última posició
					ultim = ultim.getAnterior(); // l'últim node serà l'anterior a aquest últim
					ultim.setSeguent(null); // eliminem al que apuntava (al següent) el nou node últim

				} else { // si no es compleix cap cas especial
					NodeLlista<T> aux = primer; // inicializa el node auxiliar amb el valor del primer de la llista
					for (int i = 0; i < posicio - 1; i++) { // recorre la llista fins que arriba a la posició inferior
															// de
															// la determinada (index)
						aux = aux.getSeguent();
					}
					NodeLlista<T> aux2 = aux.getSeguent();
					aux2 = aux2.getSeguent(); // agafem l'altra node superior (aux+2)
					aux.setSeguent(aux2); // el node següent de la posició inferior (aux) será el del node superior
											// (aux2)
					aux2.setAnterior(aux); // el node anterior de la posició superior (aux2) serà el node inferior (aux)
				}
			}
			nodes--;
		} catch (ExepcioPosEsborrar e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Métode per buscar un element dins de la llista
	 * 
	 * @param p -> valor el qual es busca a la llista
	 * @return trobat -> retorna el cost (nombre d'elements que s'han accedit) de la
	 *         consulta
	 */
	public int buscar(T p) {
		int cost = 1;
		boolean trobat = false;
		NodeLlista<T> aux = primer; // inicializa el node auxiliar amb el valor del primer de la llista
		while ((cost < nodes) && (trobat == false)) { // mentre que no es trobi i que no s'accedeixi fora de la llista
			if (p.compareTo(aux.getValor()) == 0) { // comprovem que el valor del node actual és igual al valor que es
													// busca
				trobat = true; // si és igual, s'ha trobat
			} else {
				// sino actualitzem següent node i contador de la llista
				aux = aux.getSeguent();
				cost++;
			}
		}
		try {
			if (trobat == false) {
				throw new ExcepcioNoTrobat(cost);
			}
		} catch (ExcepcioNoTrobat e) {
			System.out.println(e.getMessage());
		}

		return cost;
	}

	/**
	 * Métode per mostrar el contingut de la llista d'esquerra a dreta (de la
	 * primera a l'última posició)
	 * 
	 * @return llista -> llista amb els seus valors de cada node
	 */
	public String toString() {
		NodeLlista<T> aux = primer; // inicialitza un node aux amb el valor del primer node de la llista
		String llista = "";
		for (int i = 0; i < nodes; i++) { // recorrem la llista
			llista += "[" + aux.getValor() + "] "; // agafa la dada de l'auxiliar y l'afegeix a la cadena
			aux = aux.getSeguent(); // s'agafa el següent valor de la llista

		}
		return llista;
	}

	/**
	 * Métode per mostrar el contingut de la llista de dreta a esquerra (de la
	 * última a la primera posició)
	 * 
	 * @return llista -> llista amb els seus valors de cada node
	 */
	public String mostraInversa() {
		NodeLlista<T> aux = ultim; // inicialitza un node aux amb el valor de l'últim node de la llista
		String llista = "";
		for (int i = nodes - 1; 0 <= i; i--) { // recorrem la llista
			llista += "[" + aux.getValor() + "] "; // agafa la dada de l'auxiliar y l'afegeix a la cadena
			aux = aux.getAnterior(); // s'agafa el anterior valor de la llista
		}
		return llista;
	}

	@Override
	public Iterator<T> iterator() {
		CiutadaIterator<T> cI = new CiutadaIterator<T>(this);
		return cI;
	}

}
