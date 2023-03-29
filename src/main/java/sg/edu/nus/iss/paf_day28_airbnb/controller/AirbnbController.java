package sg.edu.nus.iss.paf_day28_airbnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.paf_day28_airbnb.models.Airbnb;
import sg.edu.nus.iss.paf_day28_airbnb.services.AirbnbService;

@Controller
@RequestMapping("/")
public class AirbnbController {

    @Autowired
    AirbnbService airbnbService;
    
    @GetMapping
    public String getSearch(){

        return "search";
    }

    @GetMapping("/search")
    public String search(@RequestParam String textSearch, Model model){
        
        List<Airbnb> airbnbList = airbnbService.findByTextSearch(textSearch);
        model.addAttribute("textSearch", textSearch);
        model.addAttribute("airbnbList", airbnbList);
        return "result";
    }
}
