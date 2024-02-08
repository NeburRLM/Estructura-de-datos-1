package estructuraTaulaHash;

import estructuraLlistaDoble.LlistaDobleEncadenada;
import excepcions.ExcepcioNoTrobat;
import excepcions.ExcepcioPosObtenir;
import excepcions.ExepcioPosEsborrar;

/**
 * 
 * @author RUBÉN LÓPEZ MARTÍNEZ
 * 
 *         Esctructura de la taula de hash
 */
public class TaulaHash<K extends Comparable<K>, T extends Comparable<T>> implements TADTaulaHash<K, T> {

	NodeTaula<K, T>[] taula;
	private static int tamany;
	private static int nElements;

	/**
	 * Métode constructor per crear la taula. *Aquest constructor serà per modificar
	 * la mida de la taula desde el main per fer el anàlisi del cost computacional
	 * de l’operació buscar
	 * 
	 * @param tamany -> tamany que tindrà la taula
	 */
	@SuppressWarnings("unchecked") // anular warning de genèrics al crear la taula estàtica
	public TaulaHash(int tamany) {
		TaulaHash.tamany = tamany; // tamany fixe que tindrà la taula
		taula = new NodeTaula[tamany]; // taula amb el tamany fixat
		nElements = 0;
	}
	/**
	 * Métode constructor per crear la taula
	 */
	@SuppressWarnings("unchecked") // anular warning de genèrics al crear la taula estàtica
	public TaulaHash() {
		tamany = 3; // tamany fixe que tindrà la taula
		taula = new NodeTaula[tamany]; // taula amb el tamany fixat
		nElements = 0;
	}
	/**
	 * Métode per insertar un nou node amb un valor i una clau que l'identificarà
	 * 
	 * @param key  -> clau indentificativa de l'element
	 * @param data -> valor que conté l'element que volem insertar
	 */
	public void inserir(K key, T data) throws ClassCastException { // excepció per controlar que un objecte s'intenti
																	// convertir en una subclasse de la que no és una
																	// instancia

		int index = hash(key); // segons la clau passada per paràmetre, calculem la posició que li pertany a la
								// taula
		NodeTaula<K, T> temporal = taula[index];
		NodeTaula<K, T> nou = new NodeTaula<K, T>(key, data);
		if (temporal == null) { // en el cas de que no hi hagi cap node insertat en la posició en qüestió
			taula[index] = nou;
			nElements++;

		} else {
			while ((temporal.getSeguent() != null) && (temporal.getKey().compareTo(key)) != 0) {
				temporal = temporal.getSeguent();
			}
			if (temporal.getKey().compareTo(key) == 0) { // si el node ja existeix amb la mateixa clau, es modifica el
															// valor pel nou que li passa l'usuari per paràmetre
				temporal.setValor(data);

			} else if (temporal.getSeguent() == null) { // si hem arribat al final de la llista de nodes, insertem a
														// l'ultima posició de les col·lisions
				temporal.setSeguent(nou); // l'element apuntarà al nou insertat
				nou.setSeguent(null); // el nou element insertat apuntarà a null
				nElements++;
			}

		}
		// condicional per redimensionar la taula actual de hash
		if (obtenirFactorDeCarrega() > 0.75) {
			redimensionar(1);
		} else if (obtenirFactorDeCarrega() < 0.25) {
			redimensionar(0);
		}

	}

	/**
	 * Métode per obtenir una dada a partir de la clau de l'element
	 * 
	 * @param key -> clau de l'element per saber la posició on es troba a la taula
	 *            de hash
	 * @return l'element que es vol obtenir a partir de la seva clau. Si no es troba
	 *         a l'estructura, es retorna null
	 */
	public T obtenir(K key) throws ClassCastException {

		int index = hash(key); // segons la clau passada per paràmetre, calculem la posició que li pertany a la
								// taula

		NodeTaula<K, T> temporal = taula[index];
		while (temporal != null) { // mentre no arribem al final de la llista de nodes dins de la posició en
									// qüestió de la taula
			if (key.compareTo(temporal.getKey()) == 0) { // si hem trobat l'element
				return temporal.getValor(); // retornem la informació del node
			}
			temporal = temporal.getSeguent();
		}
		try {
			throw new ExcepcioPosObtenir();

		} catch (ExcepcioPosObtenir e) {
			System.out.println(e.getMessage());
		}
		return null; // si l'element no s'ha trobat, es retorna null
	}

	/**
	 * Métode per buscar un element dins de la taula
	 * 
	 * @param key -> clau de l'element per saber la posició on es troba a la taula
	 *            de hash
	 * @return cost -> retorna el cost (nombre d'elements que s'han accedit) de la
	 *         consulta
	 */
	public int buscar(K key) {
		boolean trobat = false;
		int cost = 0;
		int index = hash(key); // segons la clau passada per paràmetre, calculem la posició que li pertany a la
								// taula
		if (taula[index] != null) { // mirem si en la posició en qüestió hi ha elements

			NodeTaula<K, T> aux = taula[index]; // inicializa el node auxiliar amb el valor del primer de la llista (si
												// hi ha col·lisions)
			while ((aux != null) && (trobat == false)) { // mentre que no es trobi i que no s'accedeixi fora de la
															// llista
				if (key.compareTo(aux.getKey()) == 0) { // comprovem que el valor del node actual és igual al valor que
														// es busca
					trobat = true; // si és igual, s'ha trobat
				} else {
					// sino actualitzem següent node i contador de la llista
					aux = aux.getSeguent();
					cost++;
				}
			}
		}
		if (cost == 0) cost = 1;// si la posició no té elements o si s'ha trobat al primer node
		try {
			if (trobat == false) { // si no s'ha trobat
				throw new ExcepcioNoTrobat(cost);
			}
		} catch (ExcepcioNoTrobat e) {
			System.out.println(e.getMessage());
		}
		return cost;
	}

	/**
	 * Métode per saber la quantitat de nodes actuals que té la llista
	 * 
	 * @return nElements -> quntitat de nodes
	 */
	public int mida() {
		return nElements;
	}

	/**
	 * Métode per eliminar un node a partir de la seva clau
	 * 
	 * @param key -> clau de l'element per saber la posició on es troba a la taula
	 *            de hash
	 */
	public void esborrar(K key) throws ClassCastException {
		int index = hash(key); // segons la clau passada per paràmetre, calculem la posició que li pertany a la
								// taula
		boolean esborrat = false;
		NodeTaula<K, T> temporal = taula[index];
		if (temporal != null) {// si hi ha elements a la taula
			if (key.compareTo(temporal.getKey()) == 0) { // si les claus coindiceixen en la primera posició de la llista
															// de nodes
				NodeTaula<K, T> primer = taula[index]; // agafo el primer node de la llista
				primer = primer.getSeguent(); // el primer node será el següent al que abans era el primer
				taula[index] = primer; // guardem aquest canvi a la taula
				nElements--;
				esborrat = true;
			} else {
				NodeTaula<K, T> anterior = taula[index];
				temporal = temporal.getSeguent();
				while ((temporal != null) && (temporal.getKey() != key)) { // mentre que no es trobi i que no
																			// s'accedeixi fora de la llista
					anterior = anterior.getSeguent(); // node que anirà una posició darrere del temporal l'incrementem
														// al següent
					temporal = temporal.getSeguent(); // node que anirà una posicó davant del anterior l'incrementem al
														// següent
				}
				if ((temporal != null)) { // en cas de que s'hagi trencat el bucle a causa del haber trobat l'element a
											// esborrar
					temporal = temporal.getSeguent(); // temporal apuntarà al següent node el qual s'esborrarà
					anterior.setSeguent(temporal); // anterior apuntarà al temporal, per tant s'elimina el que hi havia
													// al mig dels nodes anterior i temporal, eliminant així l'element
					nElements--;
					esborrat = true;
				}
			}
		}
		try {
			if (esborrat == false) {
				throw new ExepcioPosEsborrar();
			}
		} catch (ExepcioPosEsborrar e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Métode per calcular la posició en la qual es trobarà l'element a partir de la
	 * seva clau
	 * 
	 * @param key -> clau de l'element per saber la posició on es trobarà a la taula
	 *            de hash
	 * @return index -> retorna la posició de l'element
	 */
	private int hash(K key) {
		int index;
		int valor = 0;
		String clau;
		
		clau = key.toString(); // converteixo la clau a String
		
		for (int j = 0; j < clau.length(); j++) { // per cada caràcter, faig la conversió a la seva correspondencia en
													// ascii i ho sumo al resultat del valor acumulat "valor" * 27
			valor = valor * 27 + (int) clau.charAt(j);
		}		
		if (valor < 0) {
			valor = -valor;
		}
			
		index = valor % tamany;
		return index;
	
	}

	/**
	 * Métode per saber el factor de càrrega que té la taula en un moment actual
	 * 
	 * @param factorCarrega -> ocupació d'elements en proporció al tamany estàtic de
	 *                      la taula actualment
	 * @return factorCarrega -> retorna el factor de càrrega
	 */
	public float obtenirFactorDeCarrega() {
		float factorCarrega = (float) nElements / tamany;
		return factorCarrega;
	}

	/**
	 * Métode per retornar una llista amb totes les claus de la taula
	 * 
	 * @return llistaClaus -> retorna una llista amb totes les claus de la taula
	 */
	public LlistaDobleEncadenada<K> obtenirClaus() {
		LlistaDobleEncadenada<K> llistaClaus = new LlistaDobleEncadenada<K>();
		NodeTaula<K, T> temporal = taula[0];

		for (int i = 0; i < taula.length; i++) {
			if (taula[i] != null) {
				temporal = taula[i];
				while (temporal != null) {
					llistaClaus.inserir(temporal.getKey());
					temporal = temporal.getSeguent();
				}
			}
		}
		return llistaClaus;
	}

	/**
	 * Métode per retornar una llista amb tots els valors de la taula
	 * 
	 * @return llistaValors -> retorna una llista amb tots els valors de la taula
	 * 
	 */
	public LlistaDobleEncadenada<T> obtenirValors() {
		LlistaDobleEncadenada<T> llistaValors = new LlistaDobleEncadenada<T>();
		NodeTaula<K, T> temporal = taula[0];

		for (int i = 0; i < taula.length; i++) {
			if (taula[i] != null) {
				temporal = taula[i];
				while (temporal != null) {
					llistaValors.inserir(temporal.getValor());
					temporal = temporal.getSeguent();
				}
			}
		}
		return llistaValors;
	}

	/**
	 * Métode per mostrar el contingut de la taula de hash
	 * 
	 * @return n -> llista amb els seus valors de cada node que es troben a la taula
	 */
	public String toString() {
		String n = "";
		NodeTaula<K, T> temporal = taula[0];
		for (int i = 0; i < taula.length; i++) {
			n = n + "[" + i + "]  ";
			if (taula[i] != null) {
				temporal = taula[i];
				while (temporal != null) {
					n = n + "[" + temporal.getValor() + "] ";
					temporal = temporal.getSeguent();
				}
			}
			n = n + "\n";
		}
		return n;
	}

	/**
	 * Métode per fer més gran o més petita en funció del factor de càrrega actual
	 * 
	 * @param mode -> estat de la taula (si mode es 1, la taula té un factor de
	 *             càrrega >0,75, per tant la taula és farà gran, en cas contrari,
	 *             si mode es 0, el factor de càrrega serà <0,25, per tant la taula
	 *             és farà més petita
	 */
	private void redimensionar(int mode) {
		NodeTaula<K, T> temporal, nouNode, aux;
		int canviMida = (getTamany() * 20) / 100; //assignació per incremenar o disminuir
		// el 20% de posicions segons el número de posicions de la taula hash actual

		//int canviMida = (getTamany()); // assignació per incremenar o disminuir el doble de posicions de la taula hash actual
		
		if (mode == 1) { // quan factorCarrega es major a 0'75
			setTamany(canviMida + tamany);
		} else { // quan factorCarrega es menor a 0'25
			setTamany(tamany - canviMida);
		}

		@SuppressWarnings("unchecked") // anular warning de genèrics al crear la taula estàtica
		NodeTaula<K, T>[] auxTaula = new NodeTaula[getTamany()];
		for (int i = 0; i < taula.length; i++) {
			if (taula[i] != null) { // si en aquesta posició, la taula no té nodes es passa a la següent posició
				temporal = taula[i];
				while (temporal != null) { // mentre temporal no arribi al final de la taula
					int index = hash(temporal.getKey()); // calculem index a partir de la funció de hashing pel node a
															// la nova taula redimensionada

					nouNode = new NodeTaula<K, T>(temporal.getKey(), temporal.getValor()); // guardo el valor del node
																							// sense cap seüent
					if (auxTaula[index] == null) { // si en la posició de la taula redimensionada on li toca al node no
													// hi ha cap nodes
						auxTaula[index] = nouNode; // inserim node (només hi haurà aquest nou node en la posició, serà
													// el primer)

					} else { // si hi ha nodes a la posicó on li ha tocat a partir de la funció de hashing

						aux = auxTaula[index]; // agafem el primer node de la posició de la taula de hash redimensionada
						while (aux.getSeguent() != null) { // mirem si no hi ha següent node (s'acaba la llista de
															// nodes)

							aux = aux.getSeguent(); // si no ha arribat al final de la llista incrementem a següent node
						}
						// quan arriba al final de la llista de nodes, insertem a l'ultima posició de la
						// llista
						aux.setSeguent(nouNode); // l'últim node de la llista apuntarà al nou node
						nouNode.setSeguent(null); // nou node serà ara l'ultim de la llista (no apuntarà a cap node)

					}

					temporal = temporal.getSeguent(); // una vegada traspassat el node a la taula redimensionada, es
														// continua amb el següent node de la llista de nodes de la
														// taula de hash original
				}
			}
		}
		taula = auxTaula; // guardem la taula redimensionada amb la seva informació
	}

	/**
	 * Métode get per obtenir el tamany actual que te la taula de hash
	 * 
	 * @return tamany
	 */
	public int getTamany() {
		return tamany;
	}

	/**
	 * Métode set per modificar el tamany de una nova taula de hash
	 * 
	 * @param tamany
	 */
	public void setTamany(int tamany) {
		TaulaHash.tamany = tamany;
	}

}