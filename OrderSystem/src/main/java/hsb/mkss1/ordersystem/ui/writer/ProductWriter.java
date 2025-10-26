package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.AbstractProduct;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ProductWriter implements ItemWriter<AbstractProduct> {

    @Override
    public void writeItem(AbstractProduct product) {
        IO.print(product.getQuantity() + " * " + product.getName());
        IO.println(" = " + StringFormatterUtil.formatPrice(product.getPrice()));
    }
}
