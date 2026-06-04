import java.util.ArrayList;

public class AgendaPersonal {
    private ArrayList<Reunion> reunionesAgendadas;
    private ArrayList<Contacto> contactos;

    public AgendaPersonal (){
        this.reunionesAgendadas = new ArrayList<Reunion>();
        this.contactos = new ArrayList<Contacto>();
    }
    public void anadirContacto (Contacto nuevoContacto) {
        if(nuevoContacto == null){
            throw new IllegalArgumentException("El contacto no puede ser null");
        }
        if (existeConflictoContacto(nuevoContacto)){
            throw new IllegalStateException("Conflicto de horario con otra reunión");
        }else {
            this.contactos.add(nuevoContacto);
        }
    }
    public void anadirReunion (Reunion nuevaReunion){
        if (nuevaReunion == null) {
            throw new IllegalArgumentException("La reunión no puede ser null");
        }
        if (existeConflictoReunion(nuevaReunion)){
            throw new IllegalStateException("Conflicto de horario con otra reunión");
        }else{
            this.reunionesAgendadas.add(nuevaReunion);
        }
    }

    public boolean existeConflictoReunion (Reunion nuevaReunion){
        if (nuevaReunion==null){
            return true;
        }
        int i = 0;
        boolean existe = false;
        while (i<this.reunionesAgendadas.size() && !existe){
            if (this.reunionesAgendadas.get(i).compartimosHorario(nuevaReunion)){
                existe=true;
            }else {
                i++;
            }
        }
        return existe;
    }

    public Contacto obtenerUltimoContactoRegistrado() {
        if (this.contactos.isEmpty()) {
            return null;
        }
        return this.contactos.getLast();
    }

    public Reunion obtenerUltimoReunionRegistrado() {
        if (this.reunionesAgendadas.isEmpty()) {
            return null;
        }
        return this.reunionesAgendadas.getLast();
    }

    public int getCantidadDeReuniones () {
        return  this.reunionesAgendadas.size();
    }

    public int getCantidadDeContactos () {
        return  this.contactos.size();
    }

    public boolean existeConflictoContacto (Contacto nuevoContacto){
        if (nuevoContacto==null){
            return true;
        }
        int i = 0;
        boolean existe = false;
        while (i<this.contactos.size() && !existe){
            if (this.contactos.get(i).equals(nuevoContacto)){
                existe = true;
            }else {
                i++;
            }
        }
        return existe;


}
}
