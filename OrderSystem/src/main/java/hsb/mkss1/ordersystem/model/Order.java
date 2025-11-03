package hsb.mkss1.ordersystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Order {

    private Integer id;
    private final List<Item> items = new ArrayList<>();
    private LocalDateTime checkoutTimestamp;
    private int lumpSum = 0;

    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
            lumpSum += item.getPrice();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setCheckoutTimestamp(LocalDateTime checkoutTimestamp) {
        this.checkoutTimestamp = checkoutTimestamp;
    }

    public LocalDateTime getCheckoutTimestamp() {
        return checkoutTimestamp;
    }

    public int getLumpSum() {
        return this.lumpSum;
    }

    public Order createCopy() {
        Order copy = new Order();
        items.stream().map(Item::createCopy).forEach(copy::addItem);
        copy.setId(id);
        copy.setCheckoutTimestamp(checkoutTimestamp);

        return copy;
    }
}
