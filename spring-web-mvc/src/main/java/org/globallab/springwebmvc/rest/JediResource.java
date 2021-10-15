package org.globallab.springwebmvc.rest;

import org.apache.coyote.Response;
import org.globallab.springwebmvc.exception.JediNotFoundException;
import org.globallab.springwebmvc.model.Jedi;
import org.globallab.springwebmvc.repository.JediRepository;
import org.globallab.springwebmvc.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JediResource {

    private JediService service;

    @Autowired
    public JediResource(JediService service) {
        this.service = service;
    }

    @GetMapping("/jedis")
    public List<Jedi> getAllJedi() {
        return this.service.findAll();
    }

    @GetMapping("/jedis/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id) {
        final Jedi jedi = this.service.findById(id);

        return ResponseEntity.ok(jedi);
    }

    @PostMapping("/jedis")
    public ResponseEntity<Jedi> createJedi(@Valid @RequestBody Jedi jedi) {
        this.service.save(jedi);
        return ResponseEntity.status(201).body(this.service.save(jedi));
    }

    @PutMapping("/jedis/{id}")
    public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id,
                                           @Valid @RequestBody Jedi jedi) {

        Jedi jediUpdated = this.service.update(id, jedi);
        return ResponseEntity.ok(jediUpdated);
    }

    @DeleteMapping("/jedis/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }

}