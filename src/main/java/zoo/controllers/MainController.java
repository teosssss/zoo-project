package zoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping(path = "/")
    public String mainView(Model model) {

        return "main_view";
    }

    @GetMapping(path = "/user")
    public String testView(Model model) {

        return "main_view";
    }

    @PostMapping(path = "/post")
    public String postView(String newUser) {

        return "main_view";
    }




}
