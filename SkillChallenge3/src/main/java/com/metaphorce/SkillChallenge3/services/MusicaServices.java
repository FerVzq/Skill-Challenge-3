package com.metaphorce.SkillChallenge3.services;

import com.metaphorce.SkillChallenge3.entidades.Musica;

import java.util.List;
import java.util.Optional;

public interface MusicaServices {
    List<Musica> getMusica();
    Optional<Musica> obtenerPorId(Integer id);
    Musica guardar (Musica musica);
    void eliminar(Integer id);
}
