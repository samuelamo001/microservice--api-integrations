package samuel.gateway_service.shipping_service.shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;
    private final OrderServiceClient orderServiceClient;
    private final ShippingMapper shippingMapper;

    private final Logger logger = LoggerFactory.getLogger(ShippingService.class);

    public ShippingService(ShippingRepository shippingRepository, OrderServiceClient orderServiceClient, ShippingMapper shippingMapper) {
        this.shippingRepository = shippingRepository;
        this.orderServiceClient = orderServiceClient;
        this.shippingMapper = shippingMapper;
    }

    public ShippingResponse createShipping(Long orderId,String customerName, String address, LocalDate deliveryDate) {
        if (orderId == null || customerName == null || address == null || deliveryDate == null) {
            throw new InvalidOrderException("Invalid input: All fields are required.");
        }

        OrderRequest orderedProduct = orderServiceClient.getOrderById(orderId);


        logger.info("Order received from order service {}", orderedProduct.name());

        Shipping shipping = Shipping.builder()
                .customerName(customerName)
                .address(address)
                .deliveryDate(deliveryDate)
                .productId(orderedProduct.productId())
                .productName(orderedProduct.name())
                .productDescription(orderedProduct.description())
                .productPrice(orderedProduct.price())
                .quantity(orderedProduct.quantity())
                .build();

        shippingRepository.save(shipping);


        String message = customerName +
                ", your order for " +
                orderedProduct.name() +
                " has been shipped successfully to " +
                address +
                ". The delivery is scheduled for " +
                deliveryDate +
                ".";

        return ShippingResponse
                .builder()
                .message(message)
                .build();
    }

    public List<ShippingResponse> getAllShipping() {
        List<Shipping> shipping = shippingRepository.findAll();
        return shipping.stream()
                .map(shippingMapper::convertToShippingResponse)
                .collect(Collectors.toList());
    }
}
