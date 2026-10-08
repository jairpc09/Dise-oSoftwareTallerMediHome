import java.time.LocalDateTime;

public class ServicioDomiciliario {
    private String codigo;
    private LocalDateTime fechaProgramada;
    private String direccionAtencion;
    private String motivo;
    private String estado;
    private Paciente paciente;
    private ProfesionalSalud profesional;
    private AtencionMedica atencionMedica;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getDireccionAtencion() {
        return direccionAtencion;
    }

    public void setDireccionAtencion(String direccionAtencion) {
        this.direccionAtencion = direccionAtencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ProfesionalSalud getProfesional() {
        return profesional;
    }

    public AtencionMedica getAtencionMedica() {
        return atencionMedica;
    }

    public void programar(LocalDateTime fecha) {
        this.fechaProgramada = fecha;
        this.estado = "PROGRAMADO";
    }

    public void asignarProfesional(ProfesionalSalud profesional) {
        if (profesional != null && profesional.estaDisponible(fechaProgramada)) {
            this.profesional = profesional;
            this.estado = "ASIGNADO";
        } else {
            System.out.println("  El profesional no esta disponible para la fecha programada.");
        }
    }

    public void iniciarAtencion() {
        if (profesional == null) {
            System.out.println("  No se puede iniciar: el servicio no tiene profesional asignado.");
            return;
        }
        atencionMedica = new AtencionMedica();
        atencionMedica.setFechaHoraInicio(fechaProgramada);
        estado = "EN CURSO";
    }

    public void finalizar() {
        if (atencionMedica != null && atencionMedica.getFechaHoraFin() == null) {
            atencionMedica.setFechaHoraFin(LocalDateTime.now());
        }
        estado = "FINALIZADO";
    }

    public void cancelar() {
        estado = "CANCELADO";
    }
}
