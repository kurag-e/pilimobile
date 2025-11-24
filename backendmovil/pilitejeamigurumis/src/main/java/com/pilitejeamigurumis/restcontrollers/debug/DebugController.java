package com.pilitejeamigurumis.restcontrollers.debug;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/debug")
@CrossOrigin(origins = "*")
public class DebugController {

    @GetMapping("/me")
    public Map<String, Object> me(Authentication auth) {
        if (auth == null) {
            return Map.of("auth", null);
        }
        return Map.of(
                "name", auth.getName(),
                "authorities", auth.getAuthorities()
        );
    }
}
