package hsb.mkss1.ordersystem.util;

public class StringFormatterUtil {

    private StringFormatterUtil() {
    }

    public static String formatPrice(int priceInCent) {
        int euro = priceInCent / 100;
        int cent = priceInCent % 100;

        return "%d.%02d EUR".formatted(euro, cent);
    }
}
