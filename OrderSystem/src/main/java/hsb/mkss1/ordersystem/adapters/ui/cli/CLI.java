package hsb.mkss1.ordersystem.adapters.ui.cli;

import hsb.mkss1.ordersystem.usecases.OrderService;
import hsb.mkss1.ordersystem.adapters.OrderUserInterface;
import hsb.mkss1.ordersystem.adapters.ui.cli.reader.ILineItemReader;
import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;
import hsb.mkss1.ordersystem.usecases.dtos.OrderDTO;
import hsb.mkss1.ordersystem.adapters.ui.util.Input;
import hsb.mkss1.ordersystem.adapters.ui.util.StringFormatterUtil;

import java.util.Comparator;
import java.util.stream.Collectors;

public class CLI implements OrderUserInterface {

    private ILineItemReader itemReader;
    private OrderService orderService;

    private OrderDTO currentOrderDto;

    public void open() {
        boolean run = true;
        while (run) {
            orderService.initializeOrder(dto ->
                    currentOrderDto = dto);
            int input;
            do {
                printMenu();
                input = Input.readInt();
                LineItemDTO lineItemDTO = switch (input) {
                    case 1 -> itemReader.readLineItem();
                    default -> null;
                };
                if (lineItemDTO != null) {
                    orderService.addItemToOrder(currentOrderDto.getId(), lineItemDTO, _ -> {});
                } else if (input != 0) {
                    IO.println("invalid");
                }
            } while (input != 0);
            orderService.finalizeOrder(currentOrderDto.getId(), dto -> dto.ifPresent(this::printOrder));
            IO.println("Would you like to make another order? (true | false)");
            run = Input.readBoolean();
        }

        orderService.getAll(list -> list.forEach(this::printOrder));
    }

    private void printMenu() {
        IO.println("Your choice?");
        IO.println("(0) Finish order");
        IO.println("(1) Order LineItem");
    }

    private void printOrder(OrderDTO orderDTO) {
        IO.println("-----------------------");
        var outputText = orderDTO.getLineItems().stream()
                .sorted(Comparator.comparingInt(LineItemDTO::price))
                .map(StringFormatterUtil::formatLineItem)
                .collect(Collectors.joining(System.lineSeparator()));
        IO.println(outputText);
        IO.println("-----------------------");
        IO.println("Total price: " + StringFormatterUtil.formatPrice(orderDTO.getLumpSum()));
        IO.println("Checkout date: " + StringFormatterUtil.formatDate(orderDTO.getCheckoutTimestamp()));
        IO.println("=======================");
        IO.println("");
    }

    public void setItemReader(ILineItemReader itemReader) {
        this.itemReader = itemReader;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
