package order.microservice.orderservice.order;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.ecommerce.grpc.ProductProto;
import com.microservices.ecommerce.grpc.ProductServiceGrpc;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;
    private final OrderRepository orderRepository;
    private final OrderedProductMapper orderedProductMapper;

    public OrderService(ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub, OrderRepository orderRepository, OrderedProductMapper orderedProductMapper) {
        this.productServiceBlockingStub = productServiceBlockingStub;
        this.orderRepository = orderRepository;
        this.orderedProductMapper = orderedProductMapper;
    }

    public ProductResponse getProduct(Long productId) {

        ProductProto.ProductRequest productRequest = ProductProto.ProductRequest
                .newBuilder()
                .setProductId(productId)
                .build();

            ProductProto.ProductResponse productResponse = productServiceBlockingStub.getProduct(productRequest);

            OrderedProduct product = OrderedProduct.builder()
                    .productId(productResponse.getProductId())
                    .name(productResponse.getName())
                    .price(productResponse.getPrice())
                    .description(productResponse.getDescription())
                    .availableQuantity(productResponse.getQuantity())
                    .build();
            orderRepository.save(product);
            return orderedProductMapper.convertToProductResponse(product);
    }

    // The shipping service calls this method
    public OrderedProductResponse getOrderedProduct(Long productId) {
        OrderedProduct orderedProduct = orderRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        return orderedProductMapper.convertToOrderedProductResponse(orderedProduct);
    }

    public List<OrderedProductResponse> getOrderedProducts() {
        List<OrderedProduct> orderedProducts = orderRepository.findAll();
        return orderedProducts
                .stream()
                .map(orderedProductMapper::convertToOrderedProductResponse)
                .collect(Collectors.toList());
    }
}
