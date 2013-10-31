//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vhudson-jaxb-ri-2.1-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2013.10.18 at 09:31:38 AM PYST
//

package py.una.med.base.menu.schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Menu complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="Menu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permissions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="skipThis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="items">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="menu" type="{http://sigh.med.una.py/2013/schemas/base}Menu" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(name = "menu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Menu", propOrder = { "id", "name", "url", "permission",
		"enabled", "skipThis", "order", "items" })
public final class Menu implements Comparable<Menu> {

	@XmlElement(required = true)
	protected String id;
	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	protected String url;
	@XmlElement(required = true)
	protected String permission;
	@XmlElement(required = true)
	protected String enabled;
	@XmlElement(required = true)
	private String skipThis;
	protected int order;
	@XmlElement(required = true)
	protected Menu.Items items;

	/**
	 * Gets the value of the id property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getId() {

		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setId(String value) {

		this.id = value;
	}

	/**
	 * Gets the value of the name property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getName() {

		return name;
	}

	/**
	 * Sets the value of the name property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setName(String value) {

		this.name = value;
	}

	/**
	 * Gets the value of the url property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getUrl() {

		return url;
	}

	/**
	 * Sets the value of the url property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setUrl(String value) {

		this.url = value;
	}

	/**
	 * Gets the value of the permissions property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPermission() {

		return permission;
	}

	/**
	 * Sets the value of the permissions property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPermission(String value) {

		this.permission = value;
	}

	/**
	 * Gets the value of the enabled property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEnabled() {

		return enabled;
	}

	/**
	 * Sets the value of the enabled property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEnabled(String value) {

		this.enabled = value;
	}

	/**
	 * Gets the value of the order property.
	 *
	 */
	public int getOrder() {

		return order;
	}

	/**
	 * Sets the value of the order property.
	 *
	 */
	public void setOrder(int value) {

		this.order = value;
	}

	/**
	 * Gets the value of the items property.
	 *
	 * @return possible object is {@link Menu.Items }
	 *
	 */
	public List<Menu> getItems() {

		if (items == null) {
			items = new Items();
		}

		if (items.menu == null) {
			items.menu = new ArrayList<Menu>();
		}

		return items.menu;

	}

	/**
	 * Sets the value of the items property.
	 *
	 * @param value
	 *            allowed object is {@link Menu.Items }
	 *
	 */
	public void setItems(List<Menu> value) {

		this.getItems().clear();
		this.getItems().addAll(value);
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 *
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="menu" type="{http://sigh.med.una.py/2013/schemas/base}Menu" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "menu" })
	public static class Items {

		protected List<Menu> menu;

		/**
		 * Gets the value of the menu property.
		 *
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the menu property.
		 *
		 * <p>
		 * For example, to add a new item, do as follows:
		 *
		 * <pre>
		 * getMenu().add(newItem);
		 * </pre>
		 *
		 *
		 * <p>
		 * Objects of the following type(s) are allowed in the list {@link Menu }
		 *
		 *
		 */
		public List<Menu> getMenu() {

			if (menu == null) {
				menu = new ArrayList<Menu>();
			}
			return this.menu;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Menu o) {

		int result = Integer.compare(getOrder(), o.getOrder());
		if (result == 0) {
			// Son iguales
			result = getName().compareTo(o.getName());
		}
		return result;
	}

	/**
	 * @return skipThis
	 */
	public String getSkipThis() {

		return skipThis;
	}

	/**
	 * @param skipThis
	 *            skipThis para setear
	 */
	public void setSkipThis(String skipThis) {

		this.skipThis = skipThis;
	}

}
