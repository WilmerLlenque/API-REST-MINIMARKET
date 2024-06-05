package com.platzi.minimarket.persistence.mapper;

import com.platzi.minimarket.domain.PurchaseItem;
import com.platzi.minimarket.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad",target = "quatity"),
            @Mapping(source = "estado",target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto productoItem);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id.idCompra",ignore = true),
            @Mapping(target = "compras",ignore = true),
            @Mapping(target = "producto",ignore = true)
    })
    ComprasProducto toCompraProducto(PurchaseItem purchaseItem);
}
