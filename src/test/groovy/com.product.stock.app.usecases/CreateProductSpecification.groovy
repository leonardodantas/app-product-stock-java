package com.product.stock.app.usecases

import com.product.stock.app.repositories.IProductRepository
import com.product.stock.domain.Product
import spock.lang.Specification
import utils.GetMockJson

class CreateProductSpecification extends Specification {

    def productRepository = Mock(IProductRepository)
    def createProduct = new CreateProduct(productRepository)

    def getMockJson = new GetMockJson()

    def "shouldCreateProduct"() {
        given: "that when saving a product we will return a saved product"
        def productSave = getMockJson.getObject("products/product_save_success", Product.class)
        productRepository.save(_ as Product) >> productSave

        when: "the save product use case is called with a valid product"
        def productToSave = getMockJson.getObject("products/product_to_save_success", Product.class)
        def result = createProduct.execute(productToSave);

        then: "the return of the use case of saving products, must be a product that contains the id field filled in"
        assert !result.id().isBlank()

        and: "the return product name must be the same as the saved product name"
        assert result.name() == productToSave.name()
    }
}
