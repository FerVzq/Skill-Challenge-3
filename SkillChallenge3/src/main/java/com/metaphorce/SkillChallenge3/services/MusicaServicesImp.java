package com.metaphorce.SkillChallenge3.services;

import com.metaphorce.SkillChallenge3.entidades.Musica;
import com.metaphorce.SkillChallenge3.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MusicaServicesImp implements MusicaServices{
    //Inyeccion de dependencias
    @Autowired
    MusicaRepository musicaRepository;
    @Override
    public List<Musica> getMusica() {
        return musicaRepository.findAll();
    }
    public Musica guardar(Musica musica){
        return musicaRepository.save(musica);
    }
    public Optional<Musica> obtenerPorId(Integer id){
        return musicaRepository.findById(id);
    }
    public void eliminar(Integer id){
        musicaRepository.deleteById(id);
    }
}
