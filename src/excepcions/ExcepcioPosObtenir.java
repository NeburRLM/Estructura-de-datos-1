package excepcions;

public class ExcepcioPosObtenir extends Exception {
	private static final long serialVersionUID = 1L;

	public ExcepcioPosObtenir() {
		super("No s'ha pogut fer la consulta d'obtenir l'element correctament ja que la posició és invàlida");
	}
}
