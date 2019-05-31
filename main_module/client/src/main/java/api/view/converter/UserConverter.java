package api.view.converter;

import api.view.UserBean;
import models.User;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter<User> {
    @Override
    public User getAsObject(FacesContext ctx, UIComponent uiComponent, String s) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{UserBean}", UserBean.class);

        UserBean userBean = (UserBean) vex.getValue(ctx.getELContext());
        return userBean.getUserById(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, User user) {
        return user.getId();
    }
}
