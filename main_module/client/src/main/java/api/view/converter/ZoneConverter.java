package api.view.converter;

import api.view.UserBean;
import api.view.ZoneBean;
import models.User;
import models.Zone;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "zoneConverter")
public class ZoneConverter implements Converter<Zone> {
    @Override
    public Zone getAsObject(FacesContext ctx, UIComponent uiComponent, String s) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{ZoneBean}", ZoneBean.class);

        ZoneBean zoneBean = (ZoneBean) vex.getValue(ctx.getELContext());
        return zoneBean.findZoneById(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Zone zone) {
        return zone.getId();
    }
}
