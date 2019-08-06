package com.stackroute.trackservice;

//import com.stackroute.trackservice.seeddata.ApplicationListenerSeedData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableConfigurationProperties(ApplicationListenerSeedData.class)
@EnableSwagger2 //enabling swagger2 to view the api documentation
@SpringBootApplication//this tells the dispacther servlet that this class is the main class
public class TrackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackServiceApplication.class, args);
	}

}
