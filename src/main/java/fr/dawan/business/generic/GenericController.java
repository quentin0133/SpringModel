package fr.dawan.business.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class GenericController<
        D,
        S extends GenericService<D>
        > {
    protected final S service;

    @GetMapping
    public Page<D> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id:[1-9]+}")
    public Optional<D> findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    public D saveOrUpdate(@RequestBody D entity) {
        return service.saveOrUpdate(entity);
    }

    @DeleteMapping("/{id:[1-9]+}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}