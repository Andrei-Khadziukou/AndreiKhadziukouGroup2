package com.epam.mentor.domain;

import com.epam.mentor.repository.ICrud;

import java.util.UUID;

public class Entity {
    private String id;

    private ICrud crudRepository;

    protected void setRepository(ICrud repository) {
        crudRepository = repository;
    }

    public void save() {
        if (id == null) {
            id = UUID.randomUUID().toString();
            crudRepository.create(this);
        } else {
            crudRepository.update(this);
        }
    }

    public Entity get() {
        return crudRepository.read(id);
    }

    public void del() {
        crudRepository.delete(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (!id.equals(entity.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
