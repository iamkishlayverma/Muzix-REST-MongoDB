package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track, Integer> {

    Track save(Track track);

    void deleteById(int id);

    List<Track> findAll();

    Track findById(int id);

    boolean existsByName(String name);
}
