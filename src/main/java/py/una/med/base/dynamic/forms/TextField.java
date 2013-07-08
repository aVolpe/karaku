/*
 * @TextField.java 1.0 Feb 21, 2013 Sistema Integral de Gestion Hospitalaria
 */
package py.una.med.base.dynamic.forms;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import py.una.med.base.util.I18nHelper;

/**
 * 
 * @author Arturo Volpe Torres
 * @since 1.0
 * @version 1.0 Feb 21, 2013
 * 
 */
public class TextField extends LabelField {

	private HtmlInputText bind;

	public TextField() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		bind = (HtmlInputText) application.createComponent(facesContext,
				HtmlInputText.COMPONENT_TYPE, "javax.faces.Text");
		bind.setId(getId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see py.una.med.base.dynamic.forms.Field#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {

		getBind().setId(id);
		super.setId(id);
	}

	/**
	 * Retorna la longitud maxima que permite el textfield
	 * 
	 * @return maxlength
	 * @see javax.faces.component.html.HtmlInputText#getMaxlength()
	 */
	public int getMaxlength() {

		return bind.getMaxlength();
	}

	/**
	 * @return
	 * @see javax.faces.component.UIInput#isRequired()
	 */
	public boolean isRequired() {

		return bind.isRequired();
	}

	/**
	 * 
	 * @param message
	 *            key del archivo de internacionalizacion
	 * @see javax.faces.component.UIInput#setRequiredMessage(java.lang.String)
	 */
	public void setRequiredMessage(String key) {

		bind.setRequiredMessage(I18nHelper.getMessage(key));
	}

	/**
	 * @param required
	 * @see javax.faces.component.UIInput#setRequired(boolean)
	 */
	public void setRequired(boolean required) {

		bind.setRequired(required);
	}

	/**
	 * @return
	 * @see javax.faces.component.UIInput#getRequiredMessage()
	 */
	public String getRequiredMessage() {

		return bind.getRequiredMessage();
	}

	/**
	 * Asigna una longitud máxima para el componente en el lado del cliente,
	 * esta propiedad debe usarse en conjunto con un control en la logica de
	 * negocios.
	 * 
	 * @param maxlength
	 *            maxlength
	 * @see javax.faces.component.html.HtmlInputText#setMaxlength(int)
	 */
	public void setMaxlength(final int maxlength) {

		bind.setMaxlength(maxlength);
	}

	/**
	 * Tipo de este componente
	 */
	private static final String TYPE = "py.una.med.base.dynamic.forms.TextField";

	/*
	 * (non-Javadoc)
	 * 
	 * @see py.una.med.base.forms.dynamic.Field#getType()
	 */
	@Override
	public String getType() {

		return TYPE;
	}

	public void setValue(final ValueExpression expression) {

		bind.setValueExpression("value", expression);
	}

	/**
	 * @return value
	 */
	public HtmlInputText getBind() {

		return bind;
	}

	/**
	 * @param value
	 *            value para setear
	 */
	public void setBind(final HtmlInputText value) {

		this.bind = value;
	}

	@Override
	public boolean enable() {

		getBind().setDisabled(false);
		return true;
	}

	@Override
	public boolean disable() {

		getBind().setDisabled(true);
		return true;
	}

}
