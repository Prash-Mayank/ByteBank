package com.bytebank.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Spring @Async service that calls Ollama after each transaction is saved
 * to assign a category (Food, Utilities, Transfer, Loan, etc.).
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final OllamaClient ollamaClient;

    @Async
    public void categoriseAsync(Long txnId) {
        // TODO: build prompt from transaction details, call ollamaClient, persist category
    }
}
