package de.tschuehly.easy.spring.auth.user.management.edit;

import de.tschuehly.easy.spring.auth.user.EasyUser;
import de.tschuehly.easy.spring.auth.user.UserService;
import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

import java.util.UUID;

@ViewComponent
public class EditUserComponent {

  private final UserService userService;

  public EditUserComponent(UserService userService) { // (1)
    this.userService = userService;
  }

  public ViewContext render(UUID uuid) { // (2)
    EasyUser user = userService.findById(uuid); // (3)
    return new EditUserContext(user.uuid, user.username, user.password); // (4)
  }
  
  public record EditUserContext(UUID uuid, String username, String password) 
    implements ViewContext {

  }
}