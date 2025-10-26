package hsb.mkss1.ordersystem.service;

import hsb.mkss1.ordersystem.model.AbstractProduct;
import hsb.mkss1.ordersystem.model.AbstractService;
import hsb.mkss1.ordersystem.model.SimpleProduct;
import hsb.mkss1.ordersystem.model.SimpleService;
import hsb.mkss1.ordersystem.model.dtos.ProductDTO;
import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;

public class SimpleItemFactory implements ItemFactory {

    @Override
    public AbstractProduct createProduct(ProductDTO productDTO) {
        return new SimpleProduct(
                productDTO.name(),
                productDTO.unitPrice(),
                productDTO.quantity());
    }

    @Override
    public AbstractService createService(ServiceDTO serviceDTO) {
        return new SimpleService(
                serviceDTO.name(),
                serviceDTO.persons(),
                serviceDTO.hours());
    }
}
