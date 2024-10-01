package samuel.gateway_service.shipping_service.shipping;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "http://localhost:8030")
public interface OrderServiceClient {

    @GetMapping("/api/v1/orders/shipping/{orderId}")
    OrderRequest getOrderById(@PathVariable("orderId") Long orderId);

}

