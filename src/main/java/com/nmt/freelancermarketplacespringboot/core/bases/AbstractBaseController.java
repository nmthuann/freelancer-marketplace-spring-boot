package com.nmt.freelancermarketplacespringboot.core.bases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractBaseController <T, ID> {
    private final IBaseService<T, ID> baseService;

    protected AbstractBaseController(IBaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/{id}")
    protected ResponseEntity<T> getOneById(@PathVariable ID id) {
        T findEntity = baseService.getOneById(id);
        return ResponseEntity.ok(findEntity);
    }

    @PostMapping("/create")
    protected ResponseEntity<T> createOne(@RequestBody T data) {
        T created = baseService.createOne(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateOneById(@PathVariable ID id, @RequestBody Object data) {
        // Implement logic to update an existing resource
        System.out.println(data);
        T updated = baseService.updateOneById(id, data);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable ID id) {
        baseService.deleteOneById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}



//package com.nmt.freelancermarketplacespringboot.core.bases;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//public abstract class AbstractBaseController<T, ID> {
//    private final IBaseService<T, ID> baseService;
//
//    protected AbstractBaseController(IBaseService<T, ID> baseService) {
//        this.baseService = baseService;
//    }
//
//    @GetMapping("/{id}")
//    protected ResponseEntity<T> getById(@PathVariable ID id) {
//        T findEntity = baseService.getById(id);
//        if (findEntity != null) {
//            return ResponseEntity.ok(findEntity);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/create")
//    protected ResponseEntity<T> create(@RequestBody T data) {
//        T created = baseService.create(data);
//        return new ResponseEntity<>(created, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T data) {
//        T updated = baseService.update(id, data);
//        if (updated != null) {
//            return new ResponseEntity<>(updated, HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable ID id) {
//        baseService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
//Sửa lại tham số trong update:
//
//Thay vì chấp nhận Object data, chúng ta sử dụng T data để bảo đảm kiểu dữ liệu chính xác.