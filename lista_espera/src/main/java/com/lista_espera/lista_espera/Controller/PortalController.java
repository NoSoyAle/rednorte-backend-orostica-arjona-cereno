package com.lista_espera.lista_espera.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {

    @GetMapping({ "/", "/portal", "/lista-espera/portal" })
    public String portal() {
        return "forward:/index.html";
    }
}
