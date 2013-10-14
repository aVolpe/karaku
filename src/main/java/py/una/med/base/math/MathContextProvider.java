/*
 * @MathContextProvider.java 1.0 Oct 8, 2013 Sistema Integral de Gestion
 * Hospitalaria
 */
package py.una.med.base.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import py.una.med.base.configuration.PropertiesUtil;
import py.una.med.base.log.Log;

/**
 * Proveedor de configuraciones matemáticas.
 * 
 * <p>
 * Singleton que provee las configuraciones del entorno para todas las
 * operaciones matemáticas.
 * </p>
 * 
 * <p>
 * Las propiedades que maneja son:
 * <ul>
 * <li>{@link #getScale()}: retorna la escala a manejar, es decir cuantos
 * dígitos a la derecha de la coma</li>
 * <li>{@link #getPrecision()}: retorna la precisión a manejar, es decir cuantos
 * dígitos tiene el número (es igual a la escala + dígitos a la izquierda de la
 * coma), ver {@link MathContext#getPrecision()}</li>
 * <li>{@link #getContext()} retorna un {@link MathContext} que puede ser
 * utilizado (en conjunto con {@link #getScale()} para operar sobre objetos del
 * tipo {@link BigDecimal}</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Este objeto es tambien un componente de Spring, que puede ser accesible
 * mediante la injección con la anotación {@link Autowired}. Pero además es un
 * singleton que debe ser accedido por los objetos que hagan matemáticas y que
 * no necesariamente son manejados por el contenedor Spring.
 * </p>
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Oct 8, 2013
 * 
 */
public final class MathContextProvider {

	/**
	 * Propiedad para definir el tipo de Round, debe ser una cadena proveída por
	 * {@link RoundingMode}.
	 * <p>
	 * <b>Por ejemplo</b>
	 * <ul>
	 * <li> {@link RoundingMode#UP}</li>
	 * <li> {@link RoundingMode#CEILING}</li>
	 * <li> {@link RoundingMode#DOWN}</li>
	 * <li> {@link RoundingMode#FLOOR}</li>
	 * <li><b>{@link RoundingMode#HALF_UP} (Por defecto)</b></li>
	 * <li>...</li>
	 * </ul>
	 * </p>
	 * 
	 * @see #getRoundingMode()
	 */
	private static final String KARAKU_MATH_ROUNDING_MODE = "karaku.math.rounding.mode";

	/**
	 * Propiedad para definir la precision.
	 * 
	 * <p>
	 * Debe poder ser parseada a un entero mediante
	 * {@link Integer#valueOf(String)}
	 * </p>
	 * 
	 * @see #getPrecision()
	 */
	private static final String KARAKU_MATH_ROUNDING_PRECISION = "karaku.math.rounding.precision";

	/**
	 * Propiedad para definir la escala.
	 * 
	 * <p>
	 * Debe poder ser parseada a un entero mediante
	 * {@link Integer#valueOf(String)}
	 * </p>
	 * 
	 * @see #getScale()
	 */
	private static final String KARAKU_MATH_ROUNDING_SCALE = "karaku.math.rounding.scale";

	/**
	 * Instancia única.
	 * 
	 * <p>
	 * Retorna una instancia de este objeto a ser compartida por el contenedor.
	 * </p>
	 */
	public final static MathContextProvider INSTANCE = new MathContextProvider();

	private int scale;
	private RoundingMode rm;
	private int precision;

	@Autowired
	private transient PropertiesUtil pu;

	@Log
	private transient Logger log;

	private MathContext mathContext;

	private MathContextProvider() {

		scale = 4;
		rm = RoundingMode.HALF_UP;
		precision = 0;
	}

	/**
	 * Contexto a ser utilizado para realizar operaciones.
	 * 
	 * <p>
	 * Retorna un {@link MathContext} que debe ser utilizado para realizar
	 * operaciones matemáticas. Este contexto tiene la precisión definida por
	 * {@link #getPrecision()} y el modo de redondeo definido por
	 * {@link #getRoundingMode()}.
	 * </p>
	 * 
	 * 
	 * @return {@link MathContext} inicializado,
	 */
	public MathContext getContext() {

		if (mathContext == null) {
			mathContext = new MathContext(getPrecision(), getRoundingMode());
		}

		return mathContext;
	}

	/**
	 * Escala de las operaciones.
	 * 
	 * <p>
	 * Retorna la escala, es decir la cantidad de dígitos después de la coma
	 * </p>
	 * 
	 * @return por defecto <code>4</code>
	 */
	public int getScale() {

		return scale;
	}

	/**
	 * Tipo de redondeo.
	 * 
	 * <p>
	 * El tipo de redondeo es como se modificaran los números para adecuar un
	 * número a la escala y precisión definida.
	 * </p>
	 * 
	 * @return por defecto {@link RoundingMode#HALF_UP}
	 */
	public RoundingMode getRoundingMode() {

		return rm;
	}

	/**
	 * Precisión de operaciones
	 * 
	 * <p>
	 * Retorna la precisión, es decir la cantidad de dígitos que componen a un
	 * número, incluidos los decimales.
	 * </p>
	 * 
	 * @return precision por defecto 0
	 */
	public int getPrecision() {

		return precision;
	}

	@PostConstruct
	void post() {

		scale = Integer.valueOf(pu.get(KARAKU_MATH_ROUNDING_SCALE, "4"));
		precision = Integer
				.valueOf(pu.get(KARAKU_MATH_ROUNDING_PRECISION, "0"));
		rm = RoundingMode.valueOf(pu.get(KARAKU_MATH_ROUNDING_MODE,
				RoundingMode.HALF_UP.toString()));
		log.info(
				"Math Context initialized with a scale of {} and a round mode: {}",
				scale, rm);
	}
}