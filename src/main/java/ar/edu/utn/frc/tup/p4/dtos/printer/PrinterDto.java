package ar.edu.utn.frc.tup.p4.dtos.printer;

import ar.edu.utn.frc.tup.p4.entities.Printer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrinterDto {
    private Long id;
    private String name;

    public PrinterDto(Printer printer) {
        this.id = printer.getId();
        this.name = printer.getName();
    }
}
