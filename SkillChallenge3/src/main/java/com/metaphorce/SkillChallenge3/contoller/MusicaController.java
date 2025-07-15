package com.metaphorce.SkillChallenge3.contoller;

import com.metaphorce.SkillChallenge3.entidades.Musica;
import com.metaphorce.SkillChallenge3.exception.MusicaNoEncontradaException;
import com.metaphorce.SkillChallenge3.services.MusicaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @GetMapping("/{id}")
    public ResponseEntity<Musica> obtenerMusica (@PathVariable Integer id){
        if(id > 3){
            throw new MusicaNoEncontradaException("La música no existe");
        }
        return ResponseEntity.ok(new Musica());
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

    @PostMapping("/musica")
    public ResponseEntity<String> crearMusica (@Valid @RequestBody Musica musica, BindingResult resultado){
        if(resultado.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Musica creada con exito");
    }

    @ControllerAdvice
    public class GlobalExceptionAdvice{
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<String> manejarValidacion(MethodArgumentNotValidException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validadcion" + exception.getMessage());
        }
    }

}
