package samuel.gateway_service.shipping_service.shipping;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
