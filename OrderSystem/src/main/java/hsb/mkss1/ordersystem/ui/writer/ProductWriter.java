package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.AbstractProduct;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ProductWriter implements ItemWriter<AbstractProduct> {

    @Override
    public String writeItem(AbstractProduct product) {
        return "%s - %s (%dx à %s)".formatted(
                StringFormatterUtil.formatPrice(product.getPrice()),
                product.getName(),
                product.getQuantity(),
                StringFormatterUtil.formatPrice(product.getUnitPrice())
        );
    }
}
