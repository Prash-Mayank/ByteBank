package com.bytebank.service;

import com.bytebank.ai.GeminiClient;
import com.bytebank.ai.OllamaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Facade over GeminiClient (chatbot, spending insights, loan assistant) and
 * OllamaClient (transaction categorisation, fraud risk summaries).
 * Falls back to rule-based responses when the Gemini free-tier quota is exhausted.
 */
@Service
@RequiredArgsConstructor
public class AiService {

    private final GeminiClient geminiClient;
    private final OllamaClient ollamaClient;

    // TODO: chat(), categoriseTransaction(), assessFraudRisk(), generateSpendingInsight(),
    // loanEligibilityAssistant()
}
