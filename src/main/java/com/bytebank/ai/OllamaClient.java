package com.bytebank.ai;

import com.bytebank.config.AiConfig;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

/**
 * HTTP client for the local Ollama Docker container (llama3.2 / mistral, completely free).
 * Used for transaction categorisation and fraud-alert natural language risk assessment.
 */
@Component
@RequiredArgsConstructor
public class OllamaClient {

    private final AiConfig aiConfig;
    private final OkHttpClient httpClient;

    // TODO: generate(String prompt) -> calls {ollamaBaseUrl}/api/generate
}
