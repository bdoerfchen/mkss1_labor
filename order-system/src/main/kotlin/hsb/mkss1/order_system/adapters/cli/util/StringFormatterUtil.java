package hsb.mkss1.order_system.adapters.cli.util;

import de.hsbremen.mkss.shared.dtos.ItemDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringFormatterUtil {

    private StringFormatterUtil() {
    }

    public static String formatPrice(int priceInCent) {
        int euro = priceInCent / 100;
        int cent = priceInCent % 100;

        return "%d.%02d EUR".formatted(euro, cent);
    }

    public static String formatDate(LocalDateTime dateTime) {
        var format = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss", Locale.GERMAN);
        return dateTime.format(format);
    }

    public static String formatLineItem(ItemDto dto) {
        return "%15s - %s (%dx à %s)".formatted(
                StringFormatterUtil.formatPrice(dto.getPrice()),
                dto.getName(),
                dto.getQuantity(),
                StringFormatterUtil.formatPrice(dto.getPrice())
        );
    }
}
