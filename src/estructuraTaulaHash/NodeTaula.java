package estructuraTaulaHash;

/**
 * 
 * @author RUBÉN
 *
 *         Classe node de la taula hash
 */
public class NodeTaula<K, T> {
	private K key;
	private T valor;
	private NodeTaula<K, T> seguent;

	/**
	 * Métode constructor de la classe node
	 * 
	 * @param key   -> clau que conté el node actual
	 * @param valor -> informació que conté el node actual
	 */
	public NodeTaula(K key, T valor) {
		this.key = key;
		this.valor = valor;
	}

	/**
	 * Métode get per obtenir la clau del node actual
	 * 
	 * @return key -> clau guardada en el node actual
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Métode set per modificar la clau que conté el node actual per la clau que es
	 * passa per paràmetre
	 * 
	 * @param key -> clau del node al qual es vol modificar
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Métode get per obtenir el valor del node actual
	 * 
	 * @return valor -> valor guardat en el node actual
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * Métode set per modificar el valor que conté el node actual pel valor que es
	 * passa per paràmetre
	 * 
	 * @param valor -> valor del node al qual es vol modificar
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}

	/**
	 * Métode get per obtenir el valor següent del node de la llista
	 * 
	 * @return seguent -> el node següent
	 */
	public NodeTaula<K, T> getSeguent() {
		return seguent;
	}

	/**
	 * Métode set per modificar el node següent
	 * 
	 * @param seguent -> node al qual es vol modificar el node següent actual
	 */
	public void setSeguent(NodeTaula<K, T> seguent) {
		this.seguent = seguent;
	}
}