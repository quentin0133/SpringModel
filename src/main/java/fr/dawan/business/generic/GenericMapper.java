package fr.dawan.business.generic;

public interface GenericMapper<E extends BaseEntity,D> {
    D toDto(E entity);
    E toEntity(D dto);
}