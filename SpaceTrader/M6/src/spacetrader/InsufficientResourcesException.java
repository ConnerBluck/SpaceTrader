package spacetrader;

/**
 * A class that defines an InsufficientFundsException, for when the player tries
 * to buy something they don't have enough credits for.
 */
public class InsufficientResourcesException extends RuntimeException {
    /**
     * Creates a new InsufficientFundsException
     * @param message the message detailing the exception
     */
    InsufficientResourcesException(String message) {
        super(message);
    }
}
