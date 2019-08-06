//package com.stackroute.trackservice.seeddata;
//
//import com.stackroute.trackservice.domain.Track;
//import com.stackroute.trackservice.exceptions.ErrorWithConnectingToTheDataBase;
//import com.stackroute.trackservice.exceptions.UserAlreadyExistsException;
//import com.stackroute.trackservice.service.TrackService;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//@ConfigurationProperties(prefix = "english")// to map the key english from application listener seed data with this class
//@PropertySource("classpath:application.properties")//this annotation tells tis class as source for @ value  and @ environemnt
//@Component//spring frame work knows this class has some beans
//@Data
//public class ApplicationListenerSeedData implements ApplicationListener<ContextRefreshedEvent> {
//    /*
//     * this creates an object automatically
//     */
//    @Autowired
//    TrackService trackService;
//
//    private int Id;
//    private String Track;
//    private String Comments;
//    /**
//     * @param contextRefreshedEvent  this method will take ApplicationArguments as arguments     */
//    @Autowired
//    private Environment environment;
//    @Value("${tamilid}") //@value takes the value from the application.properties file and assings it to id
//    int id1;
//    @Value("${tamiltrack}")//@value takes the value from the application.properties file and assings it to name
//    String name1;
//    @Value("${tamilcomments}")//@value takes the value from the application.properties file and assings it to comments
//    String comments1;
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//
//        //using environemnt classes getproperty to get the values from application.propreties file
//        int id=Integer.parseInt(environment.getProperty("id"));
//        String name=environment.getProperty("track");
//        String comments=environment.getProperty("comments");
//
//
//
//        try {
//            Track track5=trackService.saveTrack(new Track(id1,name1,comments1));
//            Track track4=trackService.saveTrack(new Track(id,name,comments));
//            Track track1=trackService.saveTrack(new Track(Id,Track,Comments));
////            Track track2=trackService.saveTrack(new Track(3,"varuviya","bad"));
//        } catch (UserAlreadyExistsException e) {
//            e.printStackTrace();
//        }
////        } catch (ErrorWithConnectingToTheDataBase errorWithConnectingToTheDataBase) {
////            errorWithConnectingToTheDataBase.printStackTrace();
//
//    }
//}
