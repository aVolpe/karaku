package py.una.med.base.util;

/**
 * Clase que contiene cadenas constantes de expresiones regulares que se pueden
 * utilizar para realizar validaciones en atributos de la capa de dominio. En el
 * parámetro <code>regex</code> de la anotación <code>@Pattern</code> por
 * ejemplo. <br>
 * Las constantes que incluyen caracteres utilizados por el idioma Guaraní,
 * contienen el prefijo <code>GN_</code> y los mismos incluyen también
 * caracteres utilizados también por el idioma castellano
 * 
 * @author Héctor Martínez
 * @since 1.0
 * @version 1.0 Feb 28, 2013
 * 
 */
public class ValidationConstants {

	private static final String lc_grave = "áéíóú";
	private static final String uc_grave = "ÁÉÍÓÚ";
	private static final String grave = lc_grave + uc_grave;

	private static final String lc_nasal = "ãẽĩõũỹäëïöüÿâêîôûŷ";
	private static final String uc_nasal = "ÃẼĨÕŨỸÄËÏÖÜŸÂÊÎÔÛŶ";
	private static final String nasal = lc_nasal + uc_nasal;

	private static final String special = "\\s\\(\\),\\.\\+\\-\\*\\$\\/\\\\";

	/**
	 * Expresión que admite caracteres del castellano y guaraní (incluído el ')
	 * tanto en mayúsculas o minúsculas
	 */
	public static final String GN_WORDS = "[a-zA-Z'ñÑ" + grave + nasal + " ]*";
	/**
	 * Expresión que admite caracteres del castellano y guaraní (incluído el ')
	 * sólo en mayúsculas, de ahí el sufijo UC (UpperCase)
	 */
	public static final String GN_WORDS_UC = "[A-Z'Ñ" + uc_grave + uc_nasal
			+ " ]*";
	/**
	 * Expresión que admite caracteres del castellano y guaraní (incluído el ')
	 * sólo en minúsculas, de ahí el sufijo LC (LowerCase)
	 */
	public static final String GN_WORDS_LC = "[A-Z'Ñ" + lc_grave + lc_nasal
			+ " ]*";
	/**
	 * Expresión que admite caracteres del castellano y guaraní (incluído el ')
	 * tanto en mayúsculas o minúsculas además de caracteres especiales como por
	 * ejemplo [<code>, . + - * $ / \</code>]
	 * 
	 */
	public static final String GN_WORDS_SPE = "[a-zA-Z'ñÑ" + grave + nasal
			+ special + " ]*";

	/**
	 * Expresión que admite caracteres del castellano tanto en mayúsculas o
	 * minúsculas
	 */
	public static final String WORDS = "[a-zA-ZñÑ" + grave + " ]*";
	/**
	 * Expresión que admite caracteres del castellano sólo en mayúsculas
	 */
	public static final String WORDS_UC = "[A-ZÑ" + uc_grave + " ]*";
	/**
	 * Expresión que admite caracteres del castellano sólo en minúsculas
	 */
	public static final String WORDS_LC = "[A-ZÑ" + lc_grave + " ]*";
	/**
	 * Expresión que admite caracteres del castellano tanto en mayúsculas o
	 * minúsculas además de caracteres especiales como por ejemplo [
	 * <code>, . + - * $ / \</code>]
	 * 
	 */
	public static final String WORDS_SPE = "[a-zA-ZñÑ" + grave + special
			+ " ]*";

	/**
	 * Expresión que admite una cantidad arbitraria de dígitos
	 */
	public static final String DIGITS = "[0-9]*";

	/**
	 * Expresión que admite una cadena arbitraria de dígitos y guiones medios
	 */
	public static final String DIGITS_HYPHEN = "[0-9\\-]*";

	/**
	 * Expresión que admite caracteres del castellano tanto en mayúsculas o
	 * minúsculas además de dígitos
	 */
	public static final String ALPHANUMERIC = "[a-zA-Z0-9ñÑ" + grave + " ]*";

	/**
	 * Expresión que admite caracteres del castellano tanto en mayúsculas o
	 * minúsculas además de dígitos y caracteres especiales como por ejemplo [
	 * <code>, . + - * $ / \</code>]
	 * 
	 */
	public static final String ALPHANUMERIC_SPE = "[a-zA-Z0-9ñÑ" + grave
			+ special + " ]*";

	public static final String GN_WORDS_SPE_DIG = "[a-zA-Z0-9'ñÑ" + grave
			+ nasal + special + " ]*";

}
