
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaPersonalTest {
    private Contacto participante1;
    private Contacto participante2;
    private Contacto participante3;
    private Reunion reunion1;
    private AgendaPersonal agendaPersonal;

    @BeforeEach
    void inicializarParametros (){
        participante1 = new Contacto("Roberto","2494066674", "ejemplo1@gmail.com");
        participante2 = new Contacto("Roberto","2494077642", "ejemplo2@gmail.com");
        participante3 = new Contacto("Martin","2494077642", "ejemplo2@gmail.com");
        reunion1 = new Reunion(LocalDateTime.of(2026, 6, 20, 12, 0), LocalDateTime.of(2026, 6, 20, 14, 0), "AS", "av 123");
        agendaPersonal = new AgendaPersonal();
    }

    @Test
    void testAnadirConctactoNull (){
        agendaPersonal.anadirContacto(null);
        assertEquals(0,agendaPersonal.getCantidadDeContactos());
    }

    @Test
    void testAnadirContactoDiferente (){
        agendaPersonal.anadirContacto(participante1);
        agendaPersonal.anadirContacto(participante3);
        assertEquals(participante3, agendaPersonal.obtenerUltimoContactoRegistrado());
    }

    @Test
    void testAnadirContactoIgual (){
        agendaPersonal.anadirContacto(participante1);
        agendaPersonal.anadirContacto(participante2);
        assertEquals(participante1, agendaPersonal.obtenerUltimoContactoRegistrado());
    }

    @Test
    void testAnadirReunionNull (){

        agendaPersonal.anadirReunion(null);
        assertEquals(0,agendaPersonal.getCantidadDeReuniones());
    }

    @Test
    void testAnadirReunionIgual (){
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion2 = new Reunion(LocalDateTime.of(2026, 6, 20, 12, 0), LocalDateTime.of(2026, 6, 20, 14, 0), "AS", "av 123");
        agendaPersonal.anadirReunion(reunion2);
        assertEquals(reunion1,agendaPersonal.obtenerUltimoReunionRegistrado());
    }

    @Test
    void testAnadirReunionDiferente (){
        Reunion reunion3 = new Reunion(LocalDateTime.of(2026, 6, 20, 16, 0), LocalDateTime.of(2026, 6, 20, 18, 0), "AS", "av 123");
        agendaPersonal.anadirReunion(reunion3);
        assertEquals(reunion3,agendaPersonal.obtenerUltimoReunionRegistrado());
    }

    @Test
    void testCompartimosHorarioNoSePisan (){
        Reunion reunion2 = new Reunion(LocalDateTime.of(2026, 6, 20, 14, 0), LocalDateTime.of(2026, 6, 20, 16, 0), "AS", "av 123");
        assertFalse(agendaPersonal.existeConflictoReunion(reunion2));
    }

    @Test
    void testCompartimosHorarioEsAnterior () {
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion5 = new Reunion(LocalDateTime.of(2026, 6, 20, 10, 30), LocalDateTime.of(2026, 6, 20, 12, 30), "AS", "av 123");
        assertTrue(agendaPersonal.existeConflictoReunion(reunion5));
    }

    @Test
    void testCompartimosHorarioArrancaAntes () {
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion3 = new Reunion(LocalDateTime.of(2026, 6, 20, 13, 30), LocalDateTime.of(2026, 6, 20, 15, 0), "AS", "av 123");
        assertTrue(agendaPersonal.existeConflictoReunion(reunion3));
    }

    @Test
    void testCompartimosHorarioContenidaDentroDeOtra () {
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion4 = new Reunion(LocalDateTime.of(2026, 6, 20, 12, 30), LocalDateTime.of(2026, 6, 20, 13, 30), "AS", "av 123");
        assertTrue(agendaPersonal.existeConflictoReunion(reunion4));
    }

    @Test
    void testExisteConflictoReunionParametroNull (){
        assertTrue(agendaPersonal.existeConflictoReunion(null));
    }

    @Test
    void testEqualsIdentificarDiferente (){
        assertNotEquals(true,participante1.equals(participante3));
    }

    @Test
    void testEqualsIdentificarIgual () {
        agendaPersonal.anadirContacto(participante1);
        Contacto mismo = new Contacto("Roberto","2494066652","1@gmail");
        assertTrue(agendaPersonal.existeConflictoContacto(mismo));
    }

    @Test
    void testEqualsDatosNull () {
        assertTrue(agendaPersonal.existeConflictoContacto(null));
    }

    @Test
    void testEqualContactoComparable () {
        participante1.setNombre(null);
        assertFalse(agendaPersonal.existeConflictoContacto(participante2));
    }
}
