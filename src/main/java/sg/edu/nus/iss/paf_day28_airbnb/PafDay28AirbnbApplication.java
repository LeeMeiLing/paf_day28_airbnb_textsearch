package sg.edu.nus.iss.paf_day28_airbnb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.iss.paf_day28_airbnb.repositories.AirbnbRepo;

@SpringBootApplication
public class PafDay28AirbnbApplication implements CommandLineRunner {

	@Autowired
	private AirbnbRepo airbnbRepo;

	public static void main(String[] args) {
		SpringApplication.run(PafDay28AirbnbApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		airbnbRepo.addTextIndex();
	}

}
