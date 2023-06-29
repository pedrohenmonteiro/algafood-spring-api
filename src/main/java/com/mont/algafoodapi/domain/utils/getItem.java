package com.mont.algafoodapi.domain.utils;

public abstract class getItem<T extends BaseEntity> {

    private final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    protected T getById(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}
