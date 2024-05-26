package de.tschuehly.easy.spring.auth.user.management.table.row;

import de.tschuehly.easy.spring.auth.user.EasyUser;
import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import java.util.UUID;

@ViewComponent
public class UserRowComponent {

  public record UserRowContext(EasyUser easyUser) implements ViewContext {
    public static String htmlUserId(UUID uuid) {
      return "user-" + uuid;
    }
  }

  public ViewContext render(EasyUser easyUser) {
    return new UserRowContext(easyUser);
  }
}