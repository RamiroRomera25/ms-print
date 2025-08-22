package ar.edu.utn.frc.tup.p4.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZebraStatus {
    private boolean ready;
    private int labelsRemaining;
    private String rawStatus;

    /**
     * Parsea la respuesta completa de ~HS y devuelve un ZebraStatus.
     * No todos los campos de ~HS son usados, solo los más importantes.
     */
    public static ZebraStatus parse(String raw) {
        ZebraStatus status = new ZebraStatus();
        status.setRawStatus(raw != null ? raw.trim() : "");

        if (raw == null || raw.isEmpty()) {
            status.setReady(false);
            status.setLabelsRemaining(0);
            return status;
        }

        // Dividimos en líneas
        String[] lines = raw.split("\\r?\\n");
        int labels = 0;
        boolean readyFlag = true;

        // La línea P suele contener info de etiquetas pendientes
        // Ejemplo: "P   1   2   0   0" -> el segundo número suele ser labels pendientes
        for (String line : lines) {
            if (line.startsWith("P")) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length > 2) {
                    try {
                        labels = Integer.parseInt(parts[2]); // número de etiquetas pendientes
                    } catch (NumberFormatException ignored) {
                        labels = 0;
                    }
                }
            }
            // La línea O o H puede indicar errores (0 = OK)
            if (line.startsWith("H") || line.startsWith("O")) {
                if (line.contains("1")) { // si hay un 1, hay un error
                    readyFlag = false;
                }
            }
        }

        status.setLabelsRemaining(labels);
        status.setReady(readyFlag && labels == 0); // ready si no hay errores y no hay etiquetas pendientes
        return status;
    }
}
