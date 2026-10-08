import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AtencionMedica {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String observaciones;
    private String recomendaciones;
    private List<MedicionSignosVitales> mediciones = new ArrayList<>();

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public List<MedicionSignosVitales> getMediciones() {
        return mediciones;
    }

    public void agregarMedicion(MedicionSignosVitales medicion) {
        if (medicion != null) {
            mediciones.add(medicion);
        }
    }
}
