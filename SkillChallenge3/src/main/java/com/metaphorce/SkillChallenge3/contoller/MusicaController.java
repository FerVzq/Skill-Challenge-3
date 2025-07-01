package com.metaphorce.SkillChallenge3.contoller;

import com.metaphorce.SkillChallenge3.entidades.Musica;
import com.metaphorce.SkillChallenge3.services.MusicaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reproductor")
public class MusicaController {
    @Autowired
    MusicaServices musicaServices;
    @GetMapping("/musica")
    public List<Musica> getMusica(){
        return musicaServices.getMusica();
    }

    @GetMapping("/musica/{id}")
    public Musica getMusicaPorId(@PathVariable Integer id){
        return musicaServices.obtenerPorId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    @PostMapping("/musicaGuardar")
    public Musica guardar(@RequestBody Musica musica){
        return  musicaServices.guardar(musica);
    }

    @DeleteMapping("/musicaEliminar/{id}")
    public void eliminar(@PathVariable Integer id){

    }
}
