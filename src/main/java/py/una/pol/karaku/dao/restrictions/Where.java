/*-
 * Copyright (c)
 *
 * 		2012-2014, Facultad Politécnica, Universidad Nacional de Asunción.
 * 		2012-2014, Facultad de Ciencias Médicas, Universidad Nacional de Asunción.
 * 		2012-2013, Centro Nacional de Computación, Universidad Nacional de Asunción.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package py.una.pol.karaku.dao.restrictions;

import static py.una.pol.karaku.util.Checker.notNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.hibernate.criterion.Criterion;
import py.una.pol.karaku.dao.util.EntityExample;
import py.una.pol.karaku.dao.where.Clause;

/**
 * Clase que representa las restricciones para la busqueda.
 * 
 * <p>
 * Todo lo que corresponde al parámetro Where. <i>Notese que se puede utilizar
 * un ejemplo y Clauses para filtrar la Consulta</i>
 * </p>
 * 
 * @author Arturo Volpe
 * @param <T>
 *            Entidad
 * @version 1.1
 * @since 1.0 06/02/2013
 */
public class Where<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Criterion> criterions;

	private List<Clause> clauses;

	private EntityExample<T> example;

	private List<String> fetchJoin;

	private boolean distinct;

	/**
	 * Construye un nuevo {@link Where} y lo retorna, la intención de este
	 * método, es facilitar la craeción de consultas en una sintaxis Fluent-like
	 * 
	 * @return {@link Where}, nunca <code>null</code>
	 */
	public static <T> Where<T> get() {

		return new Where<T>();
	}

	/**
	 * Setea una entidad para ser utilizada como ejemplo, al hacer esto todas
	 * las columnas del ejemplo seran utilizadas para filtrar la consulta.
	 * 
	 * @param example
	 *            entidad a ser usada como ejemplo
	 */
	public void setExample(T example) {

		this.example = new EntityExample<T>(example);
	}

	/**
	 * Al igual que {@link Where#setExample(EntityExample)}, solo que recibe un
	 * {@link EntityExample}, el cual tiene mas atributos, y se pueden filtrar
	 * los atributos a ser utilizados como filtros
	 * 
	 * @param example
	 *            ejemplo de entidad para usar de filtro
	 */
	public void setExample(EntityExample<T> example) {

		this.example = example;
	}

	/**
	 * Retorna el {@link EntityExample} que se utiliza actualemnte para filtrar
	 * la consulta.
	 * 
	 * @return {@link EntityExample}
	 */
	public EntityExample<T> getExample() {

		return this.example;
	}

	/**
	 * Utilize {@link Where#addClause(Clause)} y
	 * {@link py.una.pol.karaku.dao.where.Clauses} para generar restricciones.
	 * 
	 * @param crit
	 *            para agregar.
	 * @deprecated utilizar {@link #addClause(Clause...)}
	 */
	@Deprecated
	public void addRestriction(Criterion crit) {

		if (this.criterions == null) {
			this.criterions = new ArrayList<Criterion>();
		}
		this.criterions.add(crit);
	}

	/**
	 * Agrega una restricción a la consulta, estos se construyen con la clase
	 * {@link py.una.pol.karaku.dao.where.Clauses} y se pueden agregar tantos
	 * como se desean. Todas las clausulas que se agregan por este método se
	 * añaden como una condición <code>and</code>.
	 * <p>
	 * Si se desea que se agregen como <code>or</code>, ver
	 * {@link py.una.pol.karaku.dao.where.Or}
	 * </p>
	 * 
	 * @param newClauses
	 *            {@link Clause} a ser añadidas, ningún elemento puede ser
	 *            <code>null</code>
	 * @return this
	 */
	@Nonnull
	public Where<T> addClause(@Nonnull Clause ... newClauses) {

		if (this.criterions == null) {
			this.criterions = new ArrayList<Criterion>(1);
		}
		for (Clause clause : newClauses) {
			this.getClauses().add(clause);
		}
		return this;
	}

	/**
	 * Retorna la lista de criterios que se utilizan actualmente en la consulta
	 * 
	 * @return Criterias utilizadas
	 * @deprecated Use {@link Where#getClauses()} para mantener la independencia
	 *             del motor
	 */
	@Deprecated
	public List<Criterion> getCriterions() {

		return this.criterions;
	}

	/**
	 * Retorna la lista de {@link Clause} que se utiliza actualmente para
	 * filtrar la consulta
	 * 
	 * @return {@link Clause}'s utilizados
	 */
	@Nonnull
	public List<Clause> getClauses() {

		if (clauses == null) {
			clauses = new ArrayList<Clause>(1);
		}
		return notNull(clauses);
	}

	/**
	 * Si retorna <code>true</code>, todos los resultados serán diferentes unos
	 * de otros.
	 * <p>
	 * Esto ocurre cuando se hacen joins del tipo:
	 * 
	 * <pre>
	 * select * from Pais p
	 * 	join p.departamento d
	 * 	join d.ciudad c
	 * 
	 * where c.descripcion like 'San%'
	 * 
	 * </pre>
	 * 
	 * Si existe un país con varias ciudades cuyo nombre empiece con
	 * <code>San</code>, entonces ese país será retornado varias veces.
	 * </p>
	 * 
	 * <p>
	 * Este valor es por defecto <code>false</code> (para mantener
	 * compatibilidad), si se desea que los resultados sean únicos, utilizar
	 * {@link #makeDistinct()}
	 * </p>
	 * 
	 * @return distinct <code>true</code> si no puede repetir,
	 *         <code>false</code> si los resultados son distintos.
	 */
	public boolean isDistinct() {

		return this.distinct;
	}

	/**
	 * Modifica la consulta Select, para que retorne solo los valores no
	 * repetidos.
	 * <p>
	 * Convierte la consulta:
	 * 
	 * <pre>
	 * select * from Pais p join p.departamento d
	 * 	join d.ciudad c  where c.descripcion like 'San%'
	 * </pre>
	 * 
	 * en:
	 * 
	 * <pre>
	 * select distinct(*) from Pais p join p.departamento d
	 * 	join d.ciudad c  where c.descripcion like 'San%'
	 * </pre>
	 * 
	 * 
	 * </p>
	 * 
	 * @see #isDistinct()
	 * @return this
	 */
	public Where<T> makeDistinct() {

		this.distinct = true;
		return this;
	}

	/**
	 * Modifica el <i>fetchmode</i> definido en la entidad para un atributo en
	 * particular.
	 * 
	 * <p>
	 * El FetchMode es la manera de realizar la consulta a la base de datos del
	 * elemento en particular, al utilizar este método, el atributo será cargado
	 * de manera inmediata, es decir no será un proxy.
	 * </p>
	 * <p>
	 * <b>Notar que cuando se realiza el <i>fetch</i> de una relación que apunta
	 * a varias otras entidades (uno a muchos, muchos a muchos), solamente se
	 * puede especificar un atributo para realizar el fetch</b>. Esta es una
	 * limitación de SQL, no de Karaku, ni de Hibernate, pues, al traer una
	 * relación, se realiza un JOIN, y en vez de retornar un registro por
	 * entidad, se retorna N registros por entidad (una por cada entidad
	 * relacionada), y realizar la consulta para dos subgrupos no es posible.
	 * </p>
	 * 
	 * @param property
	 *            propiedad para ser obtenida.
	 * @return this,
	 */
	@Nonnull
	public Where<T> fetch(@Nonnull String property) {

		if (fetchJoin == null) {
			fetchJoin = new ArrayList<String>(1);
		}
		fetchJoin.add(property);
		return this;
	}

	/**
	 * Lista de los atributos que deben ser cargados en manera Eager.
	 * 
	 * @return fetchJoin, nunca <code>null</code>
	 */
	@Nonnull
	public List<String> getFetchs() {

		List<String> toRet = fetchJoin;
		while (toRet == null) {
			toRet = Collections.emptyList();
		}
		return toRet;
	}

	public Where<T> getClone() {

		Where<T> clon = get();
		clon.clauses = cloneList(clauses);
		clon.criterions = cloneList(criterions);
		clon.distinct = distinct;
		clon.example = example;
		clon.fetchJoin = cloneList(fetchJoin);
		return clon;
	}

	private <Z> List<Z> cloneList(List<Z> list) {

		if (list == null) {
			return Collections.emptyList();
		}
		return new ArrayList<Z>(list);
	}
}
