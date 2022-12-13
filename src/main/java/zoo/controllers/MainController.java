package zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.model.*;
import zoo.repositories.ActivityRepository;
import zoo.repositories.AnimalRepository;
import zoo.repositories.ScheduledActivityRepository;
import zoo.repositories.UserRepository;
import zoo.services.RegisterService;
import zoo.services.UserDetailsServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class MainController {
    @Autowired
    private RegisterService<User> userService;

    @Autowired
    private RegisterService<ScheduledActivity> activityRegisterService;

    @Autowired
    private RegisterService<Reservation> reservationRegisterService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduledActivityRepository scheduledActivityRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private AnimalRepository animalRepository;


    @GetMapping(path = "/")
    public String mainView(Model model, Principal principal) {
        if (principal!=null) {
            User user = userRepository.findByEmail(principal.getName());

            model.addAttribute("user", user);
        }
        List<ScheduledActivity> mainScheduledActivities=scheduledActivityRepository.findMainActivities();
        model.addAttribute("activities",mainScheduledActivities);
        return "index";
    }

    @GetMapping(path = "/catalog")
    public String testView(Model model) {
        model.addAttribute("animals", animalRepository.findAll());
        return "catalog";

    }

    @GetMapping(path = "/animal/{id}")
    public String AnimalView(@PathVariable Integer id,Model model){
        Optional<Animal> animal= animalRepository.findById(id);
        if (!animal.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found insert a valid title");
        }
        model.addAttribute("animal",animal.get());
        return "animal";
    }

    @GetMapping(path = "/activity")
    public String activityView(Model model,Principal principal) throws ParseException {
        Iterable<ScheduledActivity> scheduledActivities=scheduledActivityRepository.findAll();
        Date today = new Date();


        for (ScheduledActivity scheduledActivity : scheduledActivities) {
            if (scheduledActivity.getDate().before(today)){
                scheduledActivity.setActivityStatus(ActivityStatus.EXPIRED);
                activityRegisterService.register(scheduledActivity);

            };
        }




        model.addAttribute("activities", scheduledActivityRepository.findAll());

        if (principal!=null) {
            User user = userRepository.findByEmail(principal.getName());

            model.addAttribute("user", user);
        }
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


    @GetMapping(path = "/schedule")
    public String scheduleView(Model model){
        model.addAttribute("activities",activityRepository.findAll());
        return "scheduleActivity";
    }


    @PostMapping(path = "/schedule")
    public String scheduleActivity(@RequestParam Long activityId, @RequestParam String date
            , @RequestParam Integer duration, @RequestParam int places,@RequestParam(defaultValue = "false") boolean checkbox)  {
        ScheduledActivity scheduledActivity=new ScheduledActivity();
        Activity activity = activityRepository.findById(activityId);

        scheduledActivity.setActivity(activity);

        try {
            scheduledActivity.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(date));
        } catch(ParseException e){
            e.printStackTrace();
        }



        scheduledActivity.setDuration(duration);
        scheduledActivity.setPlaces(places);

        if (checkbox){
            scheduledActivity.setActivityType(ActivityType.MAIN);
        } else {
            scheduledActivity.setActivityType(ActivityType.SECONDARY);

        }

        scheduledActivity.setActivityStatus(ActivityStatus.AVAILABLE);



        activityRegisterService.register(scheduledActivity);

        return "redirect:schedule?successful";
    }

    @GetMapping(path = "/create")
    public String createView(Model model){
        return "createActivity";
    }



    @PostMapping(path = "/create")
    public String createActivity( @RequestParam String title
            , @RequestParam String description) {
        Activity activity=new Activity();
        activity.setTitle(title);
        activity.setDescription(description);
        activityRepository.save(activity);
        return "redirect:create?successful";
    }

    @GetMapping(path = "/activity/{id}")
    public String reservationView(@PathVariable Long id,Model model,Principal principal){
        ScheduledActivity activity= scheduledActivityRepository.findById(id);
        if (activity==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found insert a valid title");
        }
        model.addAttribute("activity",activity);

        if (principal!=null) {
            User user = userRepository.findByEmail(principal.getName());

            model.addAttribute("user", user);
        }
        return "reservation";
    }

    @PostMapping(path = "/setSecondary/activity/{id}")
    public String setSecondary(@PathVariable Long id,Model model){
        ScheduledActivity activity= scheduledActivityRepository.findById(id);
        activity.setActivityType(ActivityType.SECONDARY);
        scheduledActivityRepository.save(activity);
        return "redirect:/";
    }


    @PostMapping(path = "/setMain/activity/{id}")
    public String setMain(@PathVariable Long id,Model model){
        ScheduledActivity activity= scheduledActivityRepository.findById(id);
        activity.setActivityType(ActivityType.MAIN);
        scheduledActivityRepository.save(activity);

        return "redirect:/";
    }


    @PostMapping(path = "/activity/{id}")
    public String reserveActivity(@PathVariable String id,@RequestParam int places,Principal principal){
        Reservation reservation=new Reservation();

        ScheduledActivity activity= scheduledActivityRepository.findById(Long.valueOf(id));
        User user=userRepository.findByEmail(principal.getName());
        reservation.setScheduledActivity(activity);
        reservation.setNumPlaces(places);
        reservation.setUser(user);
        try {
            reservationRegisterService.register(reservation);
        } catch(Exception e) {
            return "redirect:{id}?failed";
        }

        List<Reservation> reservations=new ArrayList<>(user.getReservations());
        reservations.add(reservation);
        user.setReservations(reservations);
        userRepository.save(user);

        return "redirect:/reservation";



    }

    @GetMapping(path="/reservation")
    public String reservationView(Principal principal,Model model){
        User user=userRepository.findByEmail(principal.getName());
        System.err.println(user);
        System.err.println(user.getReservations());
        model.addAttribute("reservations",user.getReservations());



        return "myReservation";
    }



}







