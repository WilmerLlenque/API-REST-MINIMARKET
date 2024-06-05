package com.platzi.minimarket.persistence;


import com.platzi.minimarket.domain.Purchase;
import com.platzi.minimarket.domain.repository.PurchaseRepository;
import com.platzi.minimarket.persistence.crud.CompraCrudRepository;
import com.platzi.minimarket.persistence.entity.Compra;
import com.platzi.minimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;
    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(purchases->mapper.toPurchases(purchases));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra=mapper.toCompra(purchase);

        //Tenemos que garantizar de que toda esta informacion se va a guardar en cascada.
        //Para guardarse en cascada tenemos que estar seguros de que COMPRA conoces los PRODUCTOS.
        //Y los PRODUCTOS conocen a que COMPRA pertenecen.
        compra.getProductos().forEach(producto -> producto.setCompras(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }

}
