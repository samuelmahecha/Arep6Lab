package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AllController {
    @GetMapping
    public String index() {
        return "index"; // This will serve index.html from src/main/resources/static
    }
}
