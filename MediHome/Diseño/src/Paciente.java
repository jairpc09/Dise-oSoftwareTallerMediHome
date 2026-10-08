public class Paciente extends Usuario implements Notificable {
    private String telefono;
    private String direccion;
    private String alergias;

    public Paciente() {
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("  [SMS a " + telefono + "] Sr(a). " + getNombre() + ": " + mensaje);
    }
}
