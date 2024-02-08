package estructuraLlistaDoble;

/**
 * 
 * @author RUBÉN
 * 
 *         TAD de la estructura de dades (llista doblement encadenada)
 */
public interface TADLlistaDoblementEncadenada<T> {
	void inserir(T data);

	void inserir(int posicio, T data);

	T obtenir(int posicio);

	int longitud();

	void esborrar(int posicio);

	int buscar(T data);
}
