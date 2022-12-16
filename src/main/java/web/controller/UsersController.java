package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
        addUsers();
    }

    private void addUsers() {
        userService.create(new User("Leo", "Bonhart","lb@mail.ru"));
        userService.create(new User("Иван", "Сидоров","isid@mail.ru"));
        userService.create(new User("Henry", "Cavill","hcav@mail.ru"));
        userService.create(new User("Maша", "Иванова","mi@mail.ru"));
        userService.create(new User("Tom", "Cat","t@mail.ru"));
    }
    @GetMapping
    public String show(Model model) {
        return getUsersView(model);
    }

    @PostMapping
    public String show2(Model model) {
        return getUsersView(model);
    }

    private String getUsersView(Model model) {
        model.addAttribute("message", "Список пользователей");
        model.addAttribute("url", "users");
        return "index";
    }



    @GetMapping("/users/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping ("users")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:users";
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "all";
    }

    @GetMapping("users/{id}")
    public String read(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    @GetMapping("users/{id}/edit")
    public String editForm(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("users/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }


    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
