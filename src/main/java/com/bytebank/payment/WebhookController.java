package com.bytebank.payment;

<<<<<<< HEAD
import com.bytebank.model.Payment;
import com.bytebank.repository.PaymentRepository;
import com.bytebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class WebhookController {

    @Autowired
    private RazorpayService razorpayService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/webhook/razorpay")
    public ResponseEntity<?> handleRazorpayWebhook(
            @RequestHeader("X-Razorpay-Signature") String signature,
            @RequestBody Map<String, Object> payload) {
        
        // Payload processing simulation
        try {
            // Under normal circumstances we parse order_id and payment_id from the JSON payload.
            // For a demo/mock sandbox, we can check the verification and mark it.
            if (payload.containsKey("payload")) {
                Map<?, ?> paymentPayload = (Map<?, ?>) payload.get("payload");
                Map<?, ?> paymentEntity = (Map<?, ?>) paymentPayload.get("payment");
                Map<?, ?> entity = (Map<?, ?>) paymentEntity.get("entity");
                
                String orderId = (String) entity.get("order_id");
                String paymentId = (String) entity.get("id");
                
                Optional<Payment> paymentOpt = paymentRepository.findByOrderId(orderId);
                if (paymentOpt.isPresent()) {
                    Payment payment = paymentOpt.get();
                    if (razorpayService.verifySignature(orderId, paymentId, signature)) {
                        payment.setStatus("SUCCESS");
                        payment.setPaymentId(paymentId);
                        paymentRepository.save(payment);

                        // Credit account balance (Fund Load Simulation)
                        // In actual deployment we might match it to account mapping
                        return ResponseEntity.ok("Signature verified & payment logged.");
                    }
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Webhook verification failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body("Signature match failed.");
=======
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Receives and verifies Razorpay / Stripe webhook callbacks. */
@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final RazorpayService razorpayService;

    @PostMapping("/razorpay")
    public ResponseEntity<Void> handleRazorpayWebhook(@RequestBody String payload,
                                                        @RequestHeader("X-Razorpay-Signature") String signature) {
        // TODO: verify signature, update Payment status, trigger downstream actions
        return ResponseEntity.ok().build();
>>>>>>> 093ee2d (ByteBank V2 project stucture)
    }
}
