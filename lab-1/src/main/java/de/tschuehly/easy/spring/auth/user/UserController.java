package de.tschuehly.easy.spring.auth.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.util.UriTemplate;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

}