package com.aeterna.game.rest;

import com.aeterna.game.actions.BackUpManipulations;
import com.aeterna.game.beans.GlobalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/backup")
public class BackUpController {
    @Autowired
    BackUpManipulations manipulations;

    @PostMapping ("/save")
    public ResponseEntity<?> saveActivity(@RequestBody GlobalMap globalMap) {
      return manipulations.saveState(globalMap);
    }

    @GetMapping("/restore")
    public ResponseEntity<?> restore () {
        return manipulations.restoreState();
    }
    @GetMapping("/create")
    public ResponseEntity<?> createSaveFile(){
        return manipulations.createFile();
    }
    @GetMapping("/check")
    public ResponseEntity<?> checkForOldGame(){
        return manipulations.checkForOldGame();
    }
    @GetMapping("/lastline")
    public ResponseEntity<?> getLastLine(){
        return manipulations.lastLine(0);
    }
    @GetMapping("/defineprevstate")
    public ResponseEntity<?> definePrevState(){
        return manipulations.lastLine(1);
    }
    @GetMapping("/newgame")
    public ResponseEntity<?> makeNewFile(){
        return manipulations.makeNewFile();
    }

    @GetMapping("/cnncheck")
    public ResponseEntity<?> connectionCheck(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
