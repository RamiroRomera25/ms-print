package ar.edu.utn.frc.tup.p4.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Printer {
    private String id;
    private String host;
    private int port;
}
