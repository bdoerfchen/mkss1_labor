package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.SimpleProduct;
import hsb.mkss1.ordersystem.model.SimpleService;

import java.util.Map;

public class AvailableWriters {

    private static final Map<Class<? extends Item>, ItemWriter<? extends Item>> availableItemWriters = Map.of(
            SimpleProduct.class, new ProductWriter(),
            SimpleService.class, new ServiceWriter()
    );

    @SuppressWarnings("unchecked")
    public static ItemWriter<Item> getItemWriter(Class<? extends Item> clazz) {
        return (ItemWriter<Item>) availableItemWriters.get(clazz);
    }
}
