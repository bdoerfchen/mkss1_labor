package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Product;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ProductWriter implements ItemWriter {

    @Override
    public void writeItem(Item item) {
        if (item instanceof Product product) {
            IO.print(product.getQuantity() + " * " + product.getName());
            IO.println(" = " + StringFormatterUtil.formatPrice(product.getPrice()));
        } else {
            throw new IllegalStateException("Item is not a Product");
        }
    }
}
