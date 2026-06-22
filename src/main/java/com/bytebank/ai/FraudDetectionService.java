package com.bytebank.ai;

import com.bytebank.repository.FraudAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Rule-based heuristics (large amounts, odd hours, new beneficiaries) that flag
 * suspicious transactions, then defers to OllamaClient for a natural-language risk summary.
 */
@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudAlertRepository fraudAlertRepository;
    private final OllamaClient ollamaClient;

    // TODO: evaluateTransaction(), raiseAlert(), notifyCustomer()
}
