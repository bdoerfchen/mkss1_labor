package hsb.mkss1.ordersystem.service;

import hsb.mkss1.ordersystem.model.AbstractProduct;
import hsb.mkss1.ordersystem.model.AbstractService;
import hsb.mkss1.ordersystem.model.dtos.ProductDTO;
import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;

public interface ItemFactory {

     AbstractProduct createProduct(ProductDTO productDTO);

     AbstractService createService(ServiceDTO serviceDTO);

}
