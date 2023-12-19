package fr.dawan.business.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GenericController<E extends BaseEntity, S extends GenericService<E>> {
    public final S service;

    @GetMapping
    public Page<E> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public E findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    public E saveOrUpdate(E entity) {
        return service.saveOrUpdate(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
