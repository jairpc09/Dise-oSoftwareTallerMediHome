import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String LINEA = "========================================";

    public static void main(String[] args) {
        Paciente paciente = new Paciente();
        paciente.setIdentificacion("1001234567");
        paciente.setNombre("Maria Fernanda Lopez");
        paciente.setCorreo("maria.lopez@correo.com");
        paciente.setTelefono("3001234567");
        paciente.setDireccion("Calle 45 # 12-30, Barrio Centro");
        paciente.setAlergias("Penicilina");

        ProfesionalSalud profesional = new ProfesionalSalud();
        profesional.setIdentificacion("79876543");
        profesional.setNombre("Carlos Andres Ramirez");
        profesional.setCorreo("carlos.ramirez@medihome.com");
        profesional.setNumeroRegistroProfesional("RM-2024-0158");
        profesional.setEspecialidad("Medicina General");

        EquipoAtencion equipo = new EquipoAtencion();
        equipo.setCodigo("EQ-01");
        equipo.setNombre("Equipo Domiciliario Norte");
        equipo.setZonaCobertura("Zona Norte");
        equipo.agregarProfesional(profesional);

        LocalDateTime fechaVisita = LocalDateTime.of(2026, 10, 8, 9, 0);
        ServicioDomiciliario servicio = new ServicioDomiciliario();
        servicio.setCodigo("SD-0001");
        servicio.setDireccionAtencion(paciente.getDireccion());
        servicio.setMotivo("Control de hipertension arterial");
        servicio.setPaciente(paciente);

        System.out.println(LINEA);
        System.out.println("     MEDIHOME - PROCESO DEL SERVICIO");
        System.out.println(LINEA);

        servicio.programar(fechaVisita);
        System.out.println("Servicio programado. Estado: " + servicio.getEstado());

        servicio.asignarProfesional(profesional);
        System.out.println("Profesional asignado. Estado: " + servicio.getEstado());

        System.out.println();
        System.out.println("Notificaciones enviadas (polimorfismo):");
        Notificable[] destinatarios = { paciente, profesional };
        for (Notificable destinatario : destinatarios) {
            destinatario.notificar("Visita " + servicio.getCodigo() + " programada para el "
                    + fechaVisita.format(FORMATO));
        }

        System.out.println();
        servicio.iniciarAtencion();
        System.out.println("Atencion iniciada. Estado: " + servicio.getEstado());
        AtencionMedica atencion = servicio.getAtencionMedica();

        MedicionSignosVitales medicion = new MedicionSignosVitales();
        medicion.setFechaHora(fechaVisita.plusMinutes(10));
        medicion.setTemperatura(36.8);
        medicion.setFrecuenciaCardiaca(78);
        medicion.setPresionSistolica(135);
        medicion.setPresionDiastolica(85);
        medicion.setSaturacionOxigeno(97.5);
        medicion.realizarMedicion();
        atencion.agregarMedicion(medicion);

        atencion.setObservaciones("Paciente consciente y orientado. Presion levemente elevada.");
        atencion.setRecomendaciones("Reducir consumo de sal, caminar 30 minutos diarios y control en 15 dias.");
        atencion.setFechaHoraFin(fechaVisita.plusMinutes(45));
        servicio.finalizar();
        System.out.println("Atencion finalizada. Estado: " + servicio.getEstado());
        System.out.println();

        imprimirReporte(servicio, equipo);
    }

    private static void imprimirReporte(ServicioDomiciliario servicio, EquipoAtencion equipo) {
        Paciente paciente = servicio.getPaciente();
        ProfesionalSalud profesional = servicio.getProfesional();
        AtencionMedica atencion = servicio.getAtencionMedica();

        System.out.println(LINEA);
        System.out.println("     MEDIHOME - REPORTE DE ATENCION");
        System.out.println(LINEA);

        System.out.println();
        System.out.println("PACIENTE");
        System.out.println("  Identificacion : " + paciente.getIdentificacion());
        System.out.println("  Nombre         : " + paciente.getNombre());
        System.out.println("  Correo         : " + paciente.getCorreo());
        System.out.println("  Telefono       : " + paciente.getTelefono());
        System.out.println("  Direccion      : " + paciente.getDireccion());
        System.out.println("  Alergias       : " + paciente.getAlergias());

        System.out.println();
        System.out.println("PROFESIONAL DE SALUD");
        System.out.println("  Identificacion : " + profesional.getIdentificacion());
        System.out.println("  Nombre         : " + profesional.getNombre());
        System.out.println("  Registro       : " + profesional.getNumeroRegistroProfesional());
        System.out.println("  Especialidad   : " + profesional.getEspecialidad());
        System.out.println("  Equipo         : " + equipo.getNombre() + " (" + equipo.getZonaCobertura()
                + ", " + equipo.getProfesionales().size() + " profesional(es))");

        System.out.println();
        System.out.println("SERVICIO DOMICILIARIO");
        System.out.println("  Codigo         : " + servicio.getCodigo());
        System.out.println("  Fecha          : " + servicio.getFechaProgramada().format(FORMATO));
        System.out.println("  Direccion      : " + servicio.getDireccionAtencion());
        System.out.println("  Motivo         : " + servicio.getMotivo());
        System.out.println("  Estado         : " + servicio.getEstado());

        System.out.println();
        System.out.println("ATENCION MEDICA");
        System.out.println("  Inicio         : " + atencion.getFechaHoraInicio().format(FORMATO));
        System.out.println("  Fin            : " + atencion.getFechaHoraFin().format(FORMATO));
        System.out.println("  Observaciones  : " + atencion.getObservaciones());
        System.out.println("  Recomendaciones: " + atencion.getRecomendaciones());

        System.out.println();
        System.out.println("SIGNOS VITALES");
        for (MedicionSignosVitales m : atencion.getMediciones()) {
            System.out.println("  Fecha y hora   : " + m.getFechaHora().format(FORMATO));
            System.out.println("  Temperatura    : " + m.getTemperatura() + " C");
            System.out.println("  Frec. cardiaca : " + m.getFrecuenciaCardiaca() + " lpm");
            System.out.println("  Presion art.   : " + m.getPresionSistolica() + "/"
                    + m.getPresionDiastolica() + " mmHg");
            System.out.println("  Saturacion O2  : " + m.getSaturacionOxigeno() + " %");
        }

        System.out.println();
        System.out.println(LINEA);
        System.out.println("            FIN DEL REPORTE");
        System.out.println(LINEA);
    }
}
