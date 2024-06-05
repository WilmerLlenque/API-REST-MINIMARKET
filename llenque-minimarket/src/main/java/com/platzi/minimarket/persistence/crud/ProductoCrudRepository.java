package com.platzi.minimarket.persistence.crud;

import com.platzi.minimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {

    //IMPLEMENTANDO QUERY METHODS

    //RECUPERA TODA LA LISTA DE PRODUCTOS QUE PERTENECEN A UNA CATEGORIA EN ESPECIFICO
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //RECUPERA LOS PRODUCTOS ESCAZOS
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
