package com.aeterna.game.rest;

import com.aeterna.game.actions.BackUpManipulations;
import com.aeterna.game.beans.GlobalMap;
import org.springframework.beans.factory.annotation.Autowired;
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
}
