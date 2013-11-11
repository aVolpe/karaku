package py.una.med.base.menu.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import py.una.med.base.menu.schemas.Menu;
import py.una.med.base.menu.schemas.MenuRequest;
import py.una.med.base.menu.schemas.MenuResponse;
import py.una.med.base.services.server.WebServiceDefinition;

/**
 *
 *
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Oct 18, 2013
 *
 */
@WebServiceDefinition(xsds = { "/META-INF/schemas/karaku/menu/menu.xsd",
		"/META-INF/schemas/karaku/menu/menuMessages.xsd" })
public class MenuServiceEndpoint {

	public static final String TARGET_NAMESPACE = "http://sigh.med.una.py/2013/schemas/base";

	@Autowired
	private transient MenuServerLogic menuServerLogic;

	@PayloadRoot(localPart = "menuRequest", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public MenuResponse menuRequest(@RequestPayload MenuRequest request) {

		MenuResponse toRet = new MenuResponse();
		List<Menu> menus = menuServerLogic.getCurrentSystemMenu();

		if (menus.size() == 1) {
			toRet.setMenu(menus.get(0));
		} else {
			Menu newRoot = new Menu();
			newRoot.setSkipThis("true");
			newRoot.setItems(menus);
			toRet.setSkipRoot("true");
			toRet.setMenu(newRoot);
		}
		return toRet;
	}
}
