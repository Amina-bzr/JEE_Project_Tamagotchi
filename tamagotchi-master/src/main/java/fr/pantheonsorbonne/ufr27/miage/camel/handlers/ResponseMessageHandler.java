package fr.pantheonsorbonne.ufr27.miage.camel.handlers;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class ResponseMessageHandler {
    @Handler
    public void responseMessageHandler(Exchange exchange) throws Exception {
        boolean available = exchange.getMessage().getHeader("available", Boolean.class);

        if (!available) {
            exchange.getMessage().setBody("Product is unavailable.");
        } else {
            boolean sufficient = exchange.getMessage().getHeader("sufficient", Boolean.class);
            if (sufficient) {
                exchange.getMessage().setBody("Product bought successfully.");
            } else {
                exchange.getMessage().setBody("You can't afford the required price.");
            }
        }
    }

}
