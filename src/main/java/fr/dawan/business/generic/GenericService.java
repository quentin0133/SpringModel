package fr.dawan.business.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<E extends BaseEntity> {
    Page<E> findAll(Pageable pageable);
    E findById(long id);
    E saveOrUpdate(E article);
    void deleteById(long id);
}
