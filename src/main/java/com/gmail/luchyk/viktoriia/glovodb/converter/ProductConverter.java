package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.Product;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;

public class ProductConverter {
    public static Product toProduct(ProductEntity productEntity) {
        return Product.builder()
                .name(productEntity.getName())
                .cost(productEntity.getCost())
 //               .order(OrderConverter.toOrder(productEntity.getOrder()))
                .build();
    }

    public static ProductEntity toProductEntity(Product product) {
        return ProductEntity.builder()
                .name(product.getName())
                .cost(product.getCost())
 //               .order(OrderConverter.toOrderEntity(product.getOrder()))
                .build();
    }
}
