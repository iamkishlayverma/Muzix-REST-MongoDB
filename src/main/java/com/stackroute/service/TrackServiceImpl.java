package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NoTracksExists;
import com.stackroute.exceptions.SameCommentExists;
import com.stackroute.exceptions.TrackAlreadyExists;
import com.stackroute.exceptions.TracksNotAvailable;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExists {
        if (trackRepository.existsByName(track.getName())){
            throw new TrackAlreadyExists("Track already exists");
        }
        Track track1 = trackRepository.save(track);
        return track1;
    }

    @Override
    public List<Track> getAllTracks() throws TracksNotAvailable {
        List<Track> trackList = (List<Track>)trackRepository.findAll();
        if (trackList.isEmpty()){
            throw new TracksNotAvailable("No tracks available to show");
        }
        return trackList;
    }

    @Override
    public Track updateTrack(int id, String comment) throws SameCommentExists {
        Track track2 = trackRepository.findById(id);
        if (track2.getComment().equals(comment)){
            throw new SameCommentExists("Same comment exists already");
        }
        track2.setComment(comment);
        Track updatedTrack = trackRepository.save(track2);
        return updatedTrack;
    }

    @Override
    public void deleteTrack(int id) throws NoTracksExists {
        if (trackRepository.findById(id) == null){
            throw new NoTracksExists("No Such Track Exists");
        }
        trackRepository.deleteById(id);
    }

    @Override
    public Track findTrackById(int id) {
        Track track2 = trackRepository.findById(id);
        return track2;
    }
}
