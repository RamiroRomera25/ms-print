package ar.edu.utn.frc.tup.p4.controllers;

import ar.edu.utn.frc.tup.p4.services.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PrintController {

    private final PrintService printService;

    @PostMapping("/{printerName}")
    public String print(@PathVariable String printerName, @RequestBody String zpl) {
        printService.printTo(printerName, zpl);
        return "Trabajo enviado a la impresora " + printerName;
    }
}
