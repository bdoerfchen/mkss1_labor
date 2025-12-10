package hsb.mkss1.order_system.adapters.cli;

import hsb.mkss1.order_system.adapters.cli.reader.ILineItemReader;
import hsb.mkss1.order_system.adapters.cli.util.Input;
import hsb.mkss1.order_system.adapters.cli.util.StringFormatterUtil;
import hsb.mkss1.order_system.usecases.OrderHandler;
import hsb.mkss1.order_system.usecases.dtos.InitializeOrderTemplate;
import hsb.mkss1.order_system.usecases.dtos.ItemDto;
import hsb.mkss1.order_system.usecases.dtos.ItemTemplate;
import hsb.mkss1.order_system.usecases.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@SuppressWarnings("java:S106") // Requirement to use System.out in CLI since it is a command line user interface
public class CLI  {


    private final ILineItemReader itemReader;

    private final OrderHandler orderService;


    public void open() {
        boolean run = true;
        System.out.println("Please enter your name: ");
        String customerName = Input.readString();

        while (run) {

            var orderTemplate = new InitializeOrderTemplate(customerName);
            OrderDto currentOrderDto = orderService.initializeOrder(orderTemplate);
            int input;
            do {
                printMenu();
                input = Input.readInt();
                ItemTemplate itemTemplate = input == 1
                    ? itemReader.readLineItem()
                    : null;

                if (itemTemplate != null) {
                    orderService.addItemToOrder(currentOrderDto.getId(), itemTemplate);
                } else if (input != 0) {
                    System.out.println("invalid");
                }
            } while (input != 0);
            printOrder(orderService.finalizeOrder(currentOrderDto.getId()));

            System.out.println("Would you like to make another order? (true | false)");
            run = Input.readBoolean();
        }

        for(OrderDto order : orderService.getAll()){
            printOrder(order);
        }
    }

    private void printMenu() {
        System.out.println("Your choice?");
        System.out.println("(0) Finish order");
        System.out.println("(1) Order LineItem");
    }

    private void printOrder(OrderDto orderDTO) {
        System.out.println("-----------------------");
        var outputText = orderDTO.getItems().stream()
                .sorted(Comparator.comparingInt(ItemDto::getPrice))
                .map(StringFormatterUtil::formatLineItem)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(outputText);
        System.out.println("-----------------------");
        System.out.println("Total price: " + StringFormatterUtil.formatPrice(orderDTO.getLumpSum()));
        System.out.println("Checkout date: " + StringFormatterUtil.formatDate(
                Objects.requireNonNull(orderDTO.getCheckoutTimestamp())));
        System.out.println("=======================");
        System.out.println();
    }

}
