
package com.klu.springmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdditionController {

    @GetMapping("/MS1")
    public Map<String, Integer> addition(
            @RequestParam("A") int a,
            @RequestParam("B") int b) {

        int result = a + b;

        return Map.of("result", result);
    }
}
