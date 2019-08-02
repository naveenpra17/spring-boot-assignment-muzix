package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component//tells the spring framework that this class has a bean
public class CommandLineRunnerSeedData implements CommandLineRunner{

   private TrackRepository trackRepository;

    /**
     * @param trackRepository it automaticalyy generates an object of track repository
     */
    @Autowired
    public CommandLineRunnerSeedData(TrackRepository trackRepository) {
        this.trackRepository=trackRepository;
    }

    /**
     * @param args  CommandLineRunner.run() accepts String array[] .
     * @throws Exception this throws an exception
     */
    @Override
    public void run(String... args) throws Exception {
        Track track=new Track(4,"vade","ok");
        Track track1=new Track(5,"kadhal","vg");
        Track track2=new Track(6,"venilavey","gg");
        trackRepository.save(track);
        trackRepository.save(track1);
        trackRepository.save(track2);
    }
}
