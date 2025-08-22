package ar.edu.utn.frc.tup.p4.dtos.printer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrinterStatusDto {
    private boolean ready;
    private int labelsRemaining;
    private String rawStatus;
}
