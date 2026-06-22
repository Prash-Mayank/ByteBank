package com.bytebank.ai;

import com.bytebank.config.AiConfig;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

/**
 * HTTP client for the Google Gemini free-tier REST API (gemini-1.5-flash, 1,500 req/day).
 * Used for the customer chatbot, spending insight one-liners, and the loan eligibility assistant.
 */
@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final AiConfig aiConfig;
    private final OkHttpClient httpClient;

    // TODO: chatCompletion(String systemPrompt, String userMessage), handle 429 quota -> fallback
}
