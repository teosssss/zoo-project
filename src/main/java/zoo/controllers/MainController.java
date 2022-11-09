package zoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zoo.model.Activity;
import zoo.model.Animal;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping(path = "/")
    public String mainView(Model model) {

        return "main_view";
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





}
