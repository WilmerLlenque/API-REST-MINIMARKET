package com.platzi.minimarket.persistence.crud;

import com.platzi.minimarket.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra,Integer> {

    //QUERY METHODS
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
