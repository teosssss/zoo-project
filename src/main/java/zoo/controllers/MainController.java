package zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zoo.model.Activity;
import zoo.model.Animal;
import zoo.model.User;
import zoo.model.UserRole;
import zoo.repositories.UserRepository;
import zoo.services.UserDetailsServiceImpl;
import zoo.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;




    @GetMapping(path = "/")
    public String mainView(Model model) {

        return "index";
    }

    @GetMapping(path = "/catalog")
    public String testView(Model model) {
        List<Animal> animals=new ArrayList<>();
        Animal lion=new Animal();
        
        lion.species="LION";
        lion.name="marco";
        lion.description="lorem sidjosfdiodsnfiodsf";
        Animal tiger=new Animal();
        tiger.species="LION";
        tiger.name="marco";
        tiger.description="lorem sidjosfdiodsnfiodsf";
        animals.add(lion);
        animals.add(tiger);
        model.addAttribute("animals", animals);
        return "catalog";
    }

    @GetMapping(path = "/activities")
    public String activityView(Model model) {
        List<Activity> activities=new ArrayList<>();
        Activity acquapark=new Activity();
        acquapark.title="acquapark";

        acquapark.description="lorem sidjosfdiodsnfiodsf";
        Activity visit=new Activity();
        visit.title="visit animals";
        visit.description="lorem sidjosfdiodsnfiodsf";
        activities.add(acquapark);
        activities.add(visit);
        model.addAttribute("activities", activities);
        return "activity";
    }

    @GetMapping(path="/register")
    public String registerView(Model model,User user){
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           @RequestParam String passwordRepeat) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "redirect:register?duplicate_email";
        }
        if (userRepository.findByName(user.getName()) != null) {
            return "redirect:register?duplicate_name";
        }
        if (user.getPassword().equals(passwordRepeat)) {
            user.setRole(UserRole.CUSTOMER);
            userService.register(user);
        } else {
            return "redirect:register?passwords";
        }
        return "redirect:login?registered";
    }


    @GetMapping(path = "/login")
    public String loginForm(Model model) {
        return "login";
    }










}







