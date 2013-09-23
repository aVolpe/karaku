/*
 * @StringUtilsTest.java 1.0 Sep 19, 2013 Sistema Integral de Gestion
 * Hospitalaria
 */
package py.una.med.base.test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import py.una.med.base.test.base.BaseTest;
import py.una.med.base.test.configuration.BaseTestConfiguration;
import py.una.med.base.util.StringUtils;

/**
 * 
 * @author arturo
 * @since 1.0
 * @version 1.0 Sep 19, 2013
 * 
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class StringUtilsTest extends BaseTest {

	@Configuration
	static class ContextConfiguration extends BaseTestConfiguration {

	}

	String NULL = null;

	@Test
	public void testIsValid() {

		assertFalse(StringUtils.isValid("        "));
		assertFalse(StringUtils.isValid(NULL));
		assertTrue(StringUtils.isValid("HOLA"));

		assertFalse(StringUtils.isValid(" a ", NULL));
		assertFalse(StringUtils.isValid(" a ", " "));
		assertTrue(StringUtils.isValid(" a ", " b "));
	}

	@Test
	public void testIsInvalid() {

		assertTrue(StringUtils.isInvalid("        "));
		assertTrue(StringUtils.isInvalid(NULL));
		assertFalse(StringUtils.isInvalid("HOLA"));

	}

	@Test
	public void testJoin() {

		assertEquals(StringUtils.join(",", "hola", "chau"), "hola,chau");
		assertEquals(StringUtils.join("'''", "hola", "chau"), "hola'''chau");
		assertEquals(StringUtils.join(" ", "hola", "chau"), "hola chau");

		List<String> cadenas = new ArrayList<String>();

		cadenas.add("hola");
		cadenas.add("chau");

		assertEquals(StringUtils.join(",", cadenas), "hola,chau");
		assertEquals(StringUtils.join("'''", cadenas), "hola'''chau");
		assertEquals(StringUtils.join(" ", cadenas), "hola chau");

		assertEquals(StringUtils.join(",", -1, 2, "hola", "chau"), "hola,chau");
		assertEquals(StringUtils.join("'''", 0, 2, "hola", "chau"),
				"hola'''chau");
		assertEquals(StringUtils.join(" ", 0, 2, "hola", "chau", "ches"),
				"hola chau ches");

	}

	@Test
	public void testSplit() {

		List<String> cadenas = new ArrayList<String>();

		cadenas.add("hola");
		cadenas.add("Chau");
		assertTrue(comparareList(cadenas, StringUtils.split("holaChau")));
	}

	@Test
	public void testPluralize() {

		List<String> cadenas = new ArrayList<String>();

		cadenas.add("hola");
		cadenas.add("Chau");
		assertEquals("holas", StringUtils.pluralize("hola"));
		assertEquals("Chaus", StringUtils.pluralize("Chau"));
		assertEquals("peces", StringUtils.pluralize("pez"));
		assertEquals("telares", StringUtils.pluralize("telar"));
		assertEquals("mamboses", StringUtils.pluralize("mambos"));
		assertEquals("madams", StringUtils.pluralize("madam"));
		assertEquals("Lapices", StringUtils.pluralize("Lapiz"));
		assertEquals("Paises", StringUtils.pluralize("Pais"));

		assertEquals("holas Chaus", StringUtils.pluralize(cadenas));
		assertEquals("holas-Chaus", StringUtils.pluralize("-", cadenas));

	}

	private <T> boolean comparareList(Collection<T> l1, Collection<T> l2) {

		if (l1 == null && l2 != null) {
			return false;
		}
		if (l2 == null && l1 != null) {
			return false;
		}
		if (l1 == null && l2 == null) {
			return true;
		}
		if (l1.size() != l2.size()) {
			return false;
		}
		boolean flag = true;
		Object[] a1 = l1.toArray(new Object[l1.size()]);
		Object[] a2 = l2.toArray(new Object[l2.size()]);
		for (int i = 0; i < l1.size(); i++) {
			flag = a1[i].equals(a2[i]);
			if (!flag) {
				return false;
			}
		}
		return true;
	}
}