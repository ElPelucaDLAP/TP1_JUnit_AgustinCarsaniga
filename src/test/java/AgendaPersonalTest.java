
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaPersonalTest {
    private Contacto participante1;
    private Contacto participante2;
    private Contacto participante3;
    private Reunion reunion1;
    private AgendaPersonal agendaPersonal;

    @BeforeAll
    static void iniciarSuite() {
        System.out.println(" Iniciando suite de pruebas de AgendaPersonal ");
    }

    @AfterEach
    void testXterminado (){
        System.out.println("*");
    }

    @AfterAll
    static void finalizarSuite() {
        System.out.println("=== Suite finalizada ===");
    }

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
        assertThrows(IllegalArgumentException.class, () ->
                agendaPersonal.anadirContacto(null)
        );
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
        assertThrows(IllegalStateException.class, () ->
                agendaPersonal.anadirContacto(participante2)
        );
        assertEquals(participante1, agendaPersonal.obtenerUltimoContactoRegistrado());
    }

    @Test
    void testAnadirReunionNull (){
        assertThrows(IllegalArgumentException.class, () ->
                agendaPersonal.anadirReunion(null)
        );

    }

    @Test
    void testAnadirReunionIgual (){
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion2 = new Reunion(
                LocalDateTime.of(2026, 6, 20, 12, 0),
                LocalDateTime.of(2026, 6, 20, 14, 0), "AS", "av 123"
        );
        assertThrows(IllegalStateException.class, () ->
                agendaPersonal.anadirReunion(reunion2)
        );
        assertEquals(reunion1, agendaPersonal.obtenerUltimoReunionRegistrado());
    }

    @Test
    void testAnadirReunionDiferente (){
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion3 = new Reunion(LocalDateTime.of(2026, 6, 20, 16, 0),
                LocalDateTime.of(2026, 6, 20, 18, 0), "AS", "av 123");
        agendaPersonal.anadirReunion(reunion3);
        assertEquals(reunion3,agendaPersonal.obtenerUltimoReunionRegistrado());
    }

    @Test
    void testCompartimosHorarioNoSePisan (){
        Reunion reunion2 = new Reunion(LocalDateTime.of(2026, 6, 20, 14, 0),
                LocalDateTime.of(2026, 6, 20, 16, 0), "AS", "av 123");
        assertFalse(agendaPersonal.existeConflictoReunion(reunion2));
    }

    @Test
    void testCompartimosHorarioEsAnterior () {
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion5 = new Reunion(LocalDateTime.of(2026, 6, 20, 10, 30),
                LocalDateTime.of(2026, 6, 20, 12, 30), "AS", "av 123");
        assertTrue(agendaPersonal.existeConflictoReunion(reunion5));
    }

    @Test
    void testCompartimosHorarioArrancaAntes () {
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion3 = new Reunion(LocalDateTime.of(2026, 6, 20, 13, 30),
                LocalDateTime.of(2026, 6, 20, 15, 0), "AS", "av 123");
        assertTrue(agendaPersonal.existeConflictoReunion(reunion3));
    }

    @Test
    void testCompartimosHorarioContenidaDentroDeOtra () {
        agendaPersonal.anadirReunion(reunion1);
        Reunion reunion4 = new Reunion(LocalDateTime.of(2026, 6, 20, 12, 30),
                LocalDateTime.of(2026, 6, 20, 13, 30), "AS", "av 123");
        assertTrue(agendaPersonal.existeConflictoReunion(reunion4));
    }

    @Test
    void testExisteConflictoReunionParametroNull (){
        assertTrue(agendaPersonal.existeConflictoReunion(null));
    }

    @Test
    void testExisteConflictoIdentificarIgual () {
        agendaPersonal.anadirContacto(participante1);
        Contacto mismo = new Contacto("Roberto","2494066652","1@gmail");
        assertTrue(agendaPersonal.existeConflictoContacto(mismo));
    }

    @Test
    void testExisteConflictoDatosNull () {
        assertTrue(agendaPersonal.existeConflictoContacto(null));
    }

    @Disabled
    @Test
    void testDesabilidatado () {
        //test desabilitado para probar disable
        participante1.setNombre(null);
        assertFalse(agendaPersonal.existeConflictoContacto(participante2));
    }
}
