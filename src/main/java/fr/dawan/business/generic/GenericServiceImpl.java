package fr.dawan.business.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<
        E extends BaseEntity,
        R extends JpaRepository<E, Long>
    > implements GenericService<E> {

    private final R repository;

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public E findById(long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public E saveOrUpdate(E entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
