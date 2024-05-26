package de.tschuehly.easy.spring.auth.user;

import de.tschuehly.easy.spring.auth.user.management.UserManagementComponent;
import de.tschuehly.easy.spring.auth.user.management.edit.EditUserComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
  public static final String MODAL_CONTAINER_ID = "modalContainer";
  public static final String USER_TABLE_BODY_ID = "userTableBody";

  private final UserService userService;
  private final UserManagementComponent userManagementComponent;
  private final EditUserComponent editUserComponent;

  public UserController(UserService userService, UserManagementComponent userManagementComponent, EditUserComponent editUserComponent) {
    this.userService = userService;
    this.userManagementComponent = userManagementComponent;
      this.editUserComponent = editUserComponent;
  }

  @GetMapping("/")
  public ViewContext index() {
    return userManagementComponent.render();
  }

  public static final String GET_EDIT_USER_MODAL = "/save-user/modal/{uuid}";


  @GetMapping(GET_EDIT_USER_MODAL)
  public ViewContext editUserModal(@PathVariable UUID uuid) {
    return editUserComponent.render(uuid);
  }

  public static final String POST_SAVE_USER = "/save-user";
  public static final String CLOSE_MODAL_EVENT = "close-modal";

  @PostMapping(POST_SAVE_USER)
  public String saveUser(UUID uuid, String username, String password, Model model, HttpServletResponse response) {
    EasyUser user = userService.saveUser(uuid, username, password);
    model.addAttribute("easyUser", user);
    response.addHeader("HX-Retarget", "#user-" + user.uuid);
    response.addHeader("HX-Reswap", "outerHTML");
    response.addHeader("HX-Trigger", CLOSE_MODAL_EVENT);
    return "UserRow";
  }


  public static final String GET_CREATE_USER_MODAL = "/create-user/modal";

  @GetMapping(GET_CREATE_USER_MODAL)
  public String getCreateUserModal() {
    return "CreateUserForm";
  }


  public static final String POST_CREATE_USER = "/create-user";

  @PostMapping(POST_CREATE_USER)
  public String createUser(String username, String password, Model model, HttpServletResponse response) {
    EasyUser user = userService.createUser(username, password);
    model.addAttribute("easyUser", user);

    response.addHeader("HX-Retarget", "#" + USER_TABLE_BODY_ID);
    response.addHeader("HX-Reswap", "afterbegin");
    response.addHeader("HX-Trigger", CLOSE_MODAL_EVENT);
    return "UserRow";
  }

}
