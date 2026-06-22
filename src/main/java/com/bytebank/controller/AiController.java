package com.bytebank.controller;

import com.bytebank.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** REST endpoints powering the chatbot widget and AI-assisted features. */
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    // TODO: POST /chat, POST /categorise, POST /loan-assistant
}
