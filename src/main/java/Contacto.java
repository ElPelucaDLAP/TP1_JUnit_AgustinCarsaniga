public class Contacto {
    private String nombre;
    private String telefono;
    private String mail;
    public Contacto(String nombre, String telefono, String mail){
        if (nombre == null || nombre.isEmpty()){
            throw new IllegalArgumentException("Falta de Valores necesarios");
        }
        if (mail == null || !mail.contains("@")){
            throw new IllegalArgumentException("Falta de Valores necesarios");
        }
        this.nombre=nombre;
        this.telefono=telefono;
        this.mail=mail;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this.getClass() !=obj.getClass()){
            return false;
        }
        Contacto otroContacto = (Contacto) obj;
        if (this.getNombre() == null){
            return otroContacto.getNombre() == null;
        }
        return this.getNombre().equals(otroContacto.getNombre());
    }

}