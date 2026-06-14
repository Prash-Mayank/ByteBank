package com.bytebank.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String apiKey;

    public Map<String, Object> createPaymentIntent(BigDecimal amount, String currency) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Simulated Stripe payment intent client-secret retrieval
            String clientSecret = "pi_mock_" + UUID.randomUUID().toString() + "_secret_" + UUID.randomUUID().toString().substring(0, 8);
            response.put("clientSecret", clientSecret);
            response.put("status", "requires_payment_method");
            response.put("id", "pi_mock_" + UUID.randomUUID().toString().substring(0, 12));
            return response;
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return response;
        }
    }
}
