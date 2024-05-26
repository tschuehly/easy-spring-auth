package de.tschuehly.easy.spring.auth.user.management.create;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

@ViewComponent
public class CreateUserComponent {
    public record CreateUserContext() implements ViewContext {
    }

    public ViewContext render() {
        return new CreateUserContext();
    }
}