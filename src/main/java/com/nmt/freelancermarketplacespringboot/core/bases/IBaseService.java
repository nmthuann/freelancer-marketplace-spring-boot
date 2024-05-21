package com.nmt.freelancermarketplacespringboot.core.bases;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface IBaseService<T, ID> {
    List<T> getAll();

    T getOneById(ID id);  // Change the type of id as needed

    T createOne(T data);

    T updateOneById(ID id, Object data);  // Change the type of id as needed

    void deleteOneById(ID id);
}
