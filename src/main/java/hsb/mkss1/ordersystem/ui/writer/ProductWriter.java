package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.Product;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ProductWriter implements ItemWriter<Product> {

    @Override
    public void writeItem(Product product) {
        IO.print(product.getQuantity() + " * " + product.getName());
        IO.println(" = " + StringFormatterUtil.formatPrice(product.getPrice()));
    }
}
