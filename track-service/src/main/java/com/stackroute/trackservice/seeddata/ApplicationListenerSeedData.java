package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.ErrorWithConnectingToTheDataBase;
import com.stackroute.trackservice.exceptions.UserAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component//spring frame work knows this class has some beans
public class ApplicationListenerSeedData implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * this creates an object automatically
     */
    @Autowired
    TrackService trackService;

    /**
     * @param contextRefreshedEvent  this method will take ApplicationArguments as arguments
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {
            Track track=trackService.saveTrack(new Track(1,"vadepulla","ok"));
            Track track1=trackService.saveTrack(new Track(2,"aramale","great"));
            Track track2=trackService.saveTrack(new Track(3,"varuviya","bad"));
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (ErrorWithConnectingToTheDataBase errorWithConnectingToTheDataBase) {
            errorWithConnectingToTheDataBase.printStackTrace();
        }
    }
}
