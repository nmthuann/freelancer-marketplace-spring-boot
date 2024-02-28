package com.nmt.freelancermarketplacespringboot.core.bases;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Field;
import java.util.List;

public class AbstractBaseService<T, ID> implements IBaseService<T, ID>{
    private final CrudRepository<T, ID> baseRepository;

    public AbstractBaseService(CrudRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<T> getAll() {
        return (List<T>) baseRepository.findAll();
    }

    public T getOneById(ID id) {
        // System.out.println("Hahaha Abstract");
//        return baseRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        return baseRepository.findById(id).orElse(null);
    }

    public T createOne(T data) {
        return baseRepository.save(data);
    }

    public T updateOneById(ID id, T data) {
        T existingEntity = getOneById(id);
        // Implement your logic for merging data if needed
        // For simplicity, I'm assuming it's        a full update
        return baseRepository.save(data);
//        T existingEntity = getOneById(id);

//        if (existingEntity != null) {
//            // Use reflection or any other mechanism to get the list of fields from the 'data' object
//            // and update only those fields in the 'existingEntity'
//            // For simplicity, I'll assume a simple case where 'T' has public getters and setters
//
//            for (Field field : data.getClass().getDeclaredFields()) {
//                field.setAccessible(true);
//                try {
//                    Object value = field.get(data);
//                    if (value != null) {
//                        field.set(existingEntity, value);
//                    }
//                } catch (IllegalAccessException e) {
//                    // Handle httpexceptions as needed
//                    e.printStackTrace();
//                }
//            }
//
//            // Save the updated entity
//            return baseRepository.save(existingEntity);
//        }
//
//        return null; // Or throw an httpexceptions indicating that the entity with the given ID was not found
    }

    public void deleteOneById(ID id) {
        if (baseRepository.existsById(id)) {
            baseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
    }

}
