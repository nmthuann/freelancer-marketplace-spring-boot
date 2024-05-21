package com.nmt.freelancermarketplacespringboot.core.bases;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBaseService<T, ID> implements IBaseService<T, ID>{
    private final CrudRepository<T, ID> baseRepository;

    public AbstractBaseService(CrudRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<T> getAll() {
        return (List<T>) baseRepository.findAll();
    }

    public T getOneById(ID id) {
//        return Optional.ofNullable(baseRepository.findById(id).orElse(null))
//                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        // return baseRepository.findById(id).orElse(null);
        return baseRepository.findById(id).orElse(null);
    }

    public T createOne(T data) {
        return baseRepository.save(data);
    }

    public T updateOneById(ID id, Object data) {

        T findEntity = getOneById(id);

        if (findEntity != null) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                JsonNode originalNode = objectMapper.convertValue(findEntity, JsonNode.class);
                JsonNode updateNode = objectMapper.convertValue(data, JsonNode.class);

                ((ObjectNode) originalNode).setAll((ObjectNode) updateNode);

                T updatedEntity =
                        objectMapper.treeToValue(originalNode, (Class<T>) findEntity.getClass());

                System.out.println(updatedEntity);
                findEntity = baseRepository.save(updatedEntity);
            } catch (IOException e) {
                e.printStackTrace(); // or handle the exception appropriately
            }
            return findEntity;
        }
        return null;

//        return getOneById(id).map(findEntity -> {
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                JsonNode originalNode = objectMapper.convertValue(findEntity, JsonNode.class);
//                JsonNode updateNode = objectMapper.convertValue(data, JsonNode.class);
//
//                ((ObjectNode) originalNode).setAll((ObjectNode) updateNode);
//
//                T updatedEntity = objectMapper.treeToValue(originalNode, (Class<T>) findEntity.getClass());
//
//                return baseRepository.save(updatedEntity);
//            } catch (IOException e) {
//                e.printStackTrace(); // or handle the exception appropriately
//                return findEntity;
//            }
//        });
    }

    public void deleteOneById(ID id) {
        if (baseRepository.existsById(id)) {
            baseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
    }

}





//        T findEntity = getOneById(id);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        if(findEntity != null){
//            try {
//                JsonNode originalNode = objectMapper.readTree(objectMapper.writeValueAsString(findEntity));
//                JsonNode updateNode = objectMapper.readTree(objectMapper.writeValueAsString(data));
//
//                Iterator<Map.Entry<String, JsonNode>> fields = originalNode.fields();
//                while (fields.hasNext()) {
//                    Map.Entry<String, JsonNode> field = fields.next();
//                    String fieldName = field.getKey();
//
//                    if (updateNode.has(fieldName)) {
//                        // Update the value of the field in originalJsonNode
//                        JsonNode updatedValue = updateNode.get(fieldName);
//                        ((ObjectNode) originalNode).set(fieldName, updatedValue);
//                    }
//                }
//
//                String updatedJson = objectMapper.writeValueAsString(originalNode);
//
//                T updatedEntity = (T) objectMapper.readValue(updatedJson, findEntity.getClass());
//
//                System.out.println(updatedEntity);
//                findEntity = baseRepository.save(updatedEntity);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return findEntity;
//        }
//        return null;