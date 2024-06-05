package com.platzi.minimarket.persistence;

import com.platzi.minimarket.domain.Product;
import com.platzi.minimarket.domain.repository.ProductRepository;
import com.platzi.minimarket.persistence.crud.ProductoCrudRepository;
import com.platzi.minimarket.persistence.entity.Producto;
import com.platzi.minimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    //LISTA QUE RECUPERA TODOS LOS PRODUCTOS DE LA BD
    @Override
    public List<Product> getAll(){
        List<Producto> productos= (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }


    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> producto=productoCrudRepository.findById(productId);
        return producto.map(pro->mapper.toProduct(pro));
    }

    @Override
    public Product save(Product product) {
        Producto producto=mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    //IMPLEMENTANDO QUERY METHODS

    //RECUPERA TODA LA LISTA DE PRODUCTOS QUE PERTENECEN A UNA CATEGORIA EN ESPECIFICO
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos=productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }
    //RECUPERA LOS PRODUCTOS ESCAZOS
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos=productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods->mapper.toProducts(prods));
    }

}
