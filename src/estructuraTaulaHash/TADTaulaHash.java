package estructuraTaulaHash;

import estructuraLlistaDoble.LlistaDobleEncadenada;

/**
 * 
 * @author RUBÉN
 * 
 *         TAD de la estructura de dades (taula de hash)
 */
public interface TADTaulaHash<K extends Comparable<K>, T extends Comparable<T>> {

	void inserir(K key, T value);

	T obtenir(K key);

	int buscar(K key);

	int mida();

	void esborrar(K key);

	LlistaDobleEncadenada<T> obtenirValors();

	LlistaDobleEncadenada<K> obtenirClaus();

	float obtenirFactorDeCarrega();

}