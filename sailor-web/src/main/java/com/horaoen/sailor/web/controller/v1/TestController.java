package com.horaoen.sailor.web.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author horaoen
 */
@RestController
public class TestController {
    @GetMapping("/v2/test")
    public String Test() {
        int a = 2 / 0;
        return "test";
    }
}
