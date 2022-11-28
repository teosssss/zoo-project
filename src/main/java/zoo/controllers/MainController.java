package zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.model.Animal;
import zoo.model.ScheduledActivity;
import zoo.model.User;
import zoo.model.UserRole;
import zoo.repositories.ScheduledActivityRepository;
import zoo.repositories.UserRepository;
import zoo.services.RegisterService;
import zoo.services.UserDetailsServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {
    @Autowired
    private RegisterService<User> userService;

    @Autowired
    private RegisterService<ScheduledActivity> activityRegisterService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduledActivityRepository activityRepository;


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

    @GetMapping(path = "/activity")
    public String activityView(Model model) {

/*
        Activity visit = new Activity();
        visit.title = "visit";
        visit.description = "lorem sidjosfdiodsnfiodsf";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        try {
            date = formatter.parse("31/03/2019 09:01:02");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        ScheduledActivity scheduledVisit = new ScheduledActivity();
        scheduledVisit.setDate(date);
        scheduledVisit.setActivity(visit);
        scheduledVisit.setPlaces(100);

        activityRegisterService.register(scheduledVisit);*/


        model.addAttribute("activities", activityRepository.findAll());
        return "activity";
    }

    @GetMapping(path = "/activity/{id}")
    public String reservationView(@PathVariable Long id,Model model){
        ScheduledActivity activity= activityRepository.findById(id);
        if (activity==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found insert a valid title");
        }
        model.addAttribute("activity",activity);
        return "reservation";
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







