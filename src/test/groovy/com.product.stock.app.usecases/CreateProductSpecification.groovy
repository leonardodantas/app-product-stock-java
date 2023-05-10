package com.product.stock.app.usecases

import com.product.stock.app.repositories.IProductRepository
import spock.lang.Specification

class CreateProductSpecification extends Specification {

    def productRepository = Mock(IProductRepository)

    def createProduct = new CreateProduct(productRepository)

    def "shouldCreateProduct"() {

    }
}
