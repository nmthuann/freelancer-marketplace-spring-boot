package com.nmt.freelancermarketplacespringboot.core.bases;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IBaseController<T, ID> {

    @GetMapping("/{id}")
    ResponseEntity<T> getOneById(@PathVariable ID id);

    @PostMapping("/create")
    ResponseEntity<T> createOne(@RequestBody T data);

    @PutMapping("/{id}")
    ResponseEntity<T> updateOneById(@PathVariable ID id, @RequestBody Object data);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOneById(@PathVariable ID id);

}
