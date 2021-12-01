package com.codegym.service;

import com.codegym.model.Song;

import java.util.Optional;

public interface ISongService {
    Iterable<Song> findAll();
    Optional<Song>findById(Long id);
    void save(Song song);
    void delete(Long id);
}
