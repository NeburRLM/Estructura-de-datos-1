package ciutada;

import java.util.Iterator;

import estructuraLlistaDoble.LlistaDobleEncadenada;

public class CiutadaIterator<T extends Comparable<T>> implements Iterator<T> {
	private LlistaDobleEncadenada<T> llista; // nou atribut que ens guardarà una copia de la llista actual de ciutadans
	private int posicioIterator;

	/**
	 * Métode constructor el qual copiarà cada element de la llista doblement
	 * enllaçada a una altra llista axiliar
	 * 
	 * @param ll -> llista de ciutadans de la qual es vol copiar
	 */
	public CiutadaIterator(LlistaDobleEncadenada<T> ll) {
		llista = new LlistaDobleEncadenada<T>();
		for (int i = 0; i < ll.longitud(); i++) {
			llista.inserir(ll.obtenir(i));
		}
		posicioIterator = 0; // ens preparem per a retornar els elements a partir de la posicio 0
	}

	@Override
	public boolean hasNext() {
		return ((posicioIterator < llista.longitud())); // mira si existeix un element a la llista en una posició
														// determinada

	}

	@Override
	public T next() {
		T aux = llista.obtenir(posicioIterator); // obté el contingut de la llista en una posició determinada
		posicioIterator++; // actualitzem contador
		return aux; // retornem l'element obtingut de la llista
	}

}
