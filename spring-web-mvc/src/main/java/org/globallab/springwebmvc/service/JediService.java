package org.globallab.springwebmvc.service;

import org.globallab.springwebmvc.exception.JediNotFoundException;
import org.globallab.springwebmvc.model.Jedi;
import org.globallab.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    private final JediRepository repository;

    @Autowired
    public JediService(JediRepository repository) {
        this.repository = repository;
    }
    public List<Jedi> findAll() {
        return this.repository.findAll();
    }

    public Jedi findById(Long id) {
        Optional<Jedi> jedi = this.repository.findById(id);

        if (jedi.isPresent()) return jedi.get();
        else throw new JediNotFoundException();
    }

    public Jedi save(Jedi jedi) {
        return this.repository.save(jedi);
    }

    public Jedi update(Long id, Jedi jediInfo) {
        Optional<Jedi> jediEntity = this.repository.findById(id);

        if (jediEntity.isPresent()) {
            Jedi jedi = jediEntity.get();
            jedi.setLastName(jediInfo.getLastName());
            jedi.setName(jediInfo.getName());
            return this.repository.save(jedi);
        } else throw new JediNotFoundException();
    }

    public void delete(Long id) {
        Jedi jedi = this.findById(id);
        this.repository.delete(jedi);
    }
}
