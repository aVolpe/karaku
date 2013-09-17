/*
 * @TransactionBaseTestConfiguration.java 1.0 Aug 19, 2013 Sistema Integral de
 * Gestion Hospitalaria
 */
package py.una.med.base.test.configuration;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import py.una.med.base.dao.helper.LikeExpressionHelper;
import py.una.med.base.dao.helper.NotExpressionHelper;
import py.una.med.base.dao.helper.NumberLikeExpressionHelper;
import py.una.med.base.dao.helper.OrExpressionHelper;
import py.una.med.base.dao.helper.RestrictionHelper;
import py.una.med.base.dao.util.CaseSensitiveHelper;
import py.una.med.base.exception.KarakuPropertyNotFoundException;
import py.una.med.base.exception.KarakuRuntimeException;

/**
 * Clases de persistencia para los test, sus anotaciones no se heredan.
 * <p>
 * Crea por defecto una base de datos H2, sin ningún
 * </p>
 * 
 * @author Arturo Volpe
 * @since 2.2
 * @version 1.0 Aug 19, 2013
 * 
 */
public class TransactionTestConfiguration extends BaseTestConfiguration {

	/**
	 * Cadena que representa el valor negativo, es decir, si esta cadena esta
	 * presente como valor de una propiedad, entonces será evaluada como
	 * <code>false</code>
	 */
	private static final String STRING_FALSE = "false";

	/**
	 * Crea un datasource para una base de datos embebida
	 * 
	 * @return dataSource creada o null si no se necesita un datasource
	 * @throws IOException
	 *             si no se puede crear la base de datos
	 */
	@Bean
	public DataSource dataSource() throws IOException {

		EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2);
		return edb.build();

	}

	/**
	 * Retorna un {@link SessionFactory} para los test, utilizando el
	 * {@link DataSource} definido por {@link #dataSource()}.
	 * 
	 * @return {@link SessionFactory}
	 * @throws IOException
	 *             si no puede leer el datasource
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException {

		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		if (getEntityClasses() == null) {
			bean.setPackagesToScan(getBasePackageToScan());
		} else {
			bean.setAnnotatedClasses(getEntityClasses());
		}

		bean.setDataSource(dataSource());
		Properties props = new Properties();
		try {
			// props.put("hibernate.dialect",
			// properties.get("hibernate.dialect"));
			props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			props.put("hibernate.hbm2ddl.auto", "create-drop");
			props.put("hibernate.show_sql",
					properties.get("hibernate.show_sql", STRING_FALSE));
			props.put("hibernate.format_sql",
					properties.get("hibernate.format_sql", STRING_FALSE));
			props.put("hibernate.listeners.envers.autoRegister", "false");
		} catch (KarakuPropertyNotFoundException kpnfe) {
			throw new KarakuRuntimeException(
					"Please check the properties file", kpnfe);
		}
		bean.setHibernateProperties(props);
		return bean;

	}

	/**
	 * Retorna la lista de paquetes que serán exploradas por esta
	 * configuración.
	 * <p>
	 * Por defecto utiliza la propiedad <code>base-package-hibernate</code> del
	 * archivo de propiedades.
	 * </p>
	 * 
	 * @return lista de paquetes, si retorna <code>null</code>, el método
	 *         {@link #getEntityClasses()} debe retornar algo.
	 */
	public String[] getBasePackageToScan() {

		return properties.getProperty("base-package-hibernate").split("\\s+");
	}

	/**
	 * Retorna la lista de paquetes que serán exploradas por esta
	 * configuración. Si este método no retorna <code>null</code>, entonces el
	 * método {@link #getBasePackageToScan()} es omitido.
	 * <p>
	 * Por defecto retorna null.
	 * </p>
	 * 
	 * 
	 * @return lista de entidades, si retorna <code>null</code>, el método
	 *         {@link #getBasePackageToScan()} debe retornar algo.
	 */
	public Class<?>[] getEntityClasses() {

		return null;
	}

	@Bean
	HibernateTransactionManager transactionManager() throws IOException {

		return new HibernateTransactionManager(sessionFactory().getObject());
	}

	@Bean
	CaseSensitiveHelper caseSensitiveHelper() {

		return new CaseSensitiveHelper();
	}

	@Bean
	RestrictionHelper restrictionHelper() {

		return new RestrictionHelper();
	}

	@Bean
	LikeExpressionHelper likeExpressionHelper() {

		return new LikeExpressionHelper();
	}

	@Bean
	NumberLikeExpressionHelper numberLikeExpressionHelper() {

		return new NumberLikeExpressionHelper();
	}

	@Bean
	OrExpressionHelper orExpressionHelper() {

		return new OrExpressionHelper();
	}

	@Bean
	NotExpressionHelper notExpressionHelper() {

		return new NotExpressionHelper();
	}
}