package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this is an interface which extends JpaRepository interface which has its own methods which we can use in our project
 * this is also useful for interfacing our project with the database
 */
public interface TrackRepository extends JpaRepository<Track,Integer>{

}
