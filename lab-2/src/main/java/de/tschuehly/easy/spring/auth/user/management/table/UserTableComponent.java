package de.tschuehly.easy.spring.auth.user.management.table;

import de.tschuehly.easy.spring.auth.user.UserService;
import de.tschuehly.easy.spring.auth.user.management.table.row.UserRowComponent;
import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import java.util.List;

@ViewComponent
public class UserTableComponent {
  private final UserService userService;
  private final UserRowComponent userRowComponent; // (1)

  public UserTableComponent(UserService userService, UserRowComponent userRowComponent) {
    this.userService = userService;
    this.userRowComponent = userRowComponent; // (1)
  }

  public record UserTableContext(List<ViewContext> userTableRowList) // (2)
      implements ViewContext{

  }
  public static final String USER_TABLE_BODY_ID = "userTableBody";

  public ViewContext render(){
    List<ViewContext> rowList = userService.findAll() // (3)
        .stream().map(userRowComponent::render).toList(); // (4)
    return new UserTableContext(rowList);
  }
}