package com.pilitejeamigurumis.restcontrollers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/regiones")
public class RegionRestController {

    @GetMapping
    public Map<String, List<String>> listarRegiones() throws Exception {
        try (InputStream in = new ClassPathResource("regiones.json").getInputStream()) {
            return new ObjectMapper().readValue(in, new TypeReference<Map<String, List<String>>>() {});
        }
    }
}
