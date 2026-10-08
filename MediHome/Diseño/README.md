# MediHome

Proyecto universitario de **Diseño y Programación** que modela la solicitud y atención de servicios médicos domiciliarios. Un paciente solicita una visita, un profesional de salud la atiende y registra signos vitales, observaciones y recomendaciones. El sistema también muestra las alergias del paciente en el reporte para que se tengan en cuenta durante la atención.

## Participantes

- Cristian Alexis Jimenez Bastidas
- Jair Alfonso Parrado Calderón

## Tecnologías

- Java 17 o superior
- Programación Orientada a Objetos
- Visual Paradigm

## Clases

| Clase | Responsabilidad |
|---|---|
| `Usuario` | Clase base con identificación, nombre y correo. |
| `Notificable` | Interfaz para enviar notificaciones al paciente y al profesional. |
| `ProfesionalSalud` | Usuario con registro profesional y especialidad; verifica su disponibilidad. |
| `Paciente` | Usuario con teléfono, dirección y alergias registradas. |
| `EquipoAtencion` | Agrupa profesionales por zona de cobertura. |
| `ServicioDomiciliario` | Servicio solicitado por un paciente; se programa, asigna, inicia, finaliza o cancela. |
| `AtencionMedica` | Atención del servicio con observaciones, recomendaciones y mediciones. |
| `MedicionSignosVitales` | Registra temperatura, frecuencia cardíaca, presión arterial y saturación de oxígeno. |
| `Main` | Crea los objetos y muestra el reporte de la atención. |

## Conceptos de POO

- **Encapsulamiento:** los atributos son privados y se accede a ellos mediante métodos.
- **Herencia:** `ProfesionalSalud` y `Paciente` extienden `Usuario`.
- **Polimorfismo:** `Paciente` y `ProfesionalSalud` implementan `Notificable` y envían sus mensajes por canales distintos.
- **Asociación:** el servicio relaciona al paciente que lo solicita con el profesional asignado.
- **Agregación:** `EquipoAtencion` agrupa profesionales que pueden existir independientemente del equipo.
- **Composición:** el servicio crea su atención médica y esta contiene las mediciones de signos vitales.

## Ejecución

Desde la carpeta del proyecto, compila y ejecuta con:

```bash
javac -d out src/*.java
java -cp out Main
```

El reporte incluye los datos del paciente, sus alergias, el profesional asignado, el servicio, la atención médica y los signos vitales registrados.
