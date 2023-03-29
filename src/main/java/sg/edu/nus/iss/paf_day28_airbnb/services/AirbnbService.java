package sg.edu.nus.iss.paf_day28_airbnb.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day28_airbnb.models.Airbnb;
import sg.edu.nus.iss.paf_day28_airbnb.repositories.AirbnbRepo;

@Service
public class AirbnbService {

    @Autowired
    private AirbnbRepo airbnbRepo;
    
    public List<Airbnb> findByTextSearch(String textSearch){

        List<Document> results = airbnbRepo.findByTextSearch(textSearch);

        List<Airbnb> airbnbList = results.stream().map(doc-> doc.toJson()).map(Airbnb::toAirbnb).collect(Collectors.toList());

        return airbnbList;
    }
}
