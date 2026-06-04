import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reunion {
    private ArrayList<Contacto> contactosDeReunion;
    private LocalDateTime inicioReunion;
    private LocalDateTime finReunion;
    private String temaAtratar;
    private String lugarDeReunion;

    public Reunion (LocalDateTime inicioReunion, LocalDateTime finReunion, String temaAtratar, String lugarDeReunion){
        if (inicioReunion == null || finReunion == null) {
            throw new IllegalArgumentException("Las fechas de la reunión no pueden ser nulas");
        }
        if (finReunion.isBefore(inicioReunion) || finReunion.isEqual(inicioReunion)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        this.contactosDeReunion = new ArrayList();
        this.inicioReunion=inicioReunion;
        this.finReunion=finReunion;
        this.temaAtratar=temaAtratar;
        this.lugarDeReunion=lugarDeReunion;
    }

    public Reunion (){
    }

    public String getLugarDeReunion() {
        return lugarDeReunion;
    }

    public void setLugarDeReunion(String lugarDeReunion) {
        this.lugarDeReunion = lugarDeReunion;
    }

    public LocalDateTime getInicioReunion() {
        return inicioReunion;
    }

    public void setInicioReunion(LocalDateTime inicioReunion) {
        this.inicioReunion = inicioReunion;
    }

    public LocalDateTime getFinReunion() {
        return finReunion;
    }

    public void setFinReunion(LocalDateTime finReunion) {
        this.finReunion = finReunion;
    }

    public String getTemaAtratar() {
        return temaAtratar;
    }

    public void setTemaAtratar(String temaAtratar) {
        this.temaAtratar = temaAtratar;
    }

    public int getCantidadParticipantes() {
        return  contactosDeReunion.size();
    }

    public Contacto obtenerUltimoContactoRegistrado() {
        if (this.contactosDeReunion.isEmpty()) {
            return null;
        }
        return this.contactosDeReunion.getLast();
    }

    public void anadirParticipante(Contacto nuevoParticipante){
        if (nuevoParticipante==null){
            return;
        }
        if (!yaExisteContacto(nuevoParticipante)){
            this.contactosDeReunion.add(nuevoParticipante);
            System.out.println("+ participante reunion");
        }
    }
    public boolean yaExisteContacto (Contacto nuevoParticipante) {
        if (nuevoParticipante==null){
            return false;
        }
        boolean existe = false;
        int i = 0;
        while (i<this.contactosDeReunion.size() && !existe) {
            if(this.contactosDeReunion.get(i).equals(nuevoParticipante)){
                existe=true;
            }else {
                i++;
            }
        }
        return existe;
    }

    public boolean compartimosHorario (Reunion reunionNueva){
        if (reunionNueva == null) {
            return false;
        }
        return this.inicioReunion.isBefore(reunionNueva.getFinReunion())
                && reunionNueva.getInicioReunion().isBefore(this.finReunion);
    }
}
