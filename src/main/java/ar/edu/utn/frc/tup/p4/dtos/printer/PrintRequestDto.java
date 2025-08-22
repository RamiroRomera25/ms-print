package ar.edu.utn.frc.tup.p4.dtos.printer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintRequestDto {
    @NotBlank
    private String printerId;
    private Map<String,String> data;
    private String zpl;
}
