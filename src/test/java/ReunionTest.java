
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReunionTest {
    private Reunion reunion1;
    private Contacto participante1;
    private Contacto participante2;
    private Contacto participante3;

    @BeforeAll
    static void iniciarSuite() {
        System.out.println(" Iniciando suite de pruebas de Reunion ");
    }

    @AfterEach
    void testXterminado (){
        System.out.println("*");
    }

    @BeforeAll
    static void testTerminado() {
        System.out.println("Finalizando prueba");
    }

    @AfterAll
    static void finalizarSuite() {
        System.out.println("=== Suite finalizada ===");
    }

    @BeforeEach
    void inicializarParametros (){
        participante1 = new Contacto("Roberto","2494066674", "ejemplo1@gmail.com");
        participante2 = new Contacto("Roberto","2494077642", "ejemplo2@gmail.com");
        participante3 = new Contacto("Daniel", "2412988864","ejemplo3@gmail.com");
        reunion1 = new Reunion(LocalDateTime.of(2026, 6, 20, 12, 0), LocalDateTime.of(2026, 6, 20, 14, 0), "AS", "av 123");
    }

    @Test
    void testThrowsConstructorInicioReunionNull () {
        assertThrows(IllegalArgumentException.class, ()->{
            new Reunion(null, LocalDateTime.of(2026, 6, 20, 14, 0), "AS", "av 123");
        });
    }

    @Test
    void testThrowsConstructorFinReunionNull () {
        assertThrows(IllegalArgumentException.class, ()->{
            new Reunion(LocalDateTime.of(2026, 6, 20, 12, 0), null, "AS", "av 123");
        });
    }

    @Test
    void testThrowsConstructorFinDeReunionMenorInicio () {
        assertThrows(IllegalArgumentException.class, ()->{
            new Reunion(LocalDateTime.of(2026, 6, 20, 12, 0), LocalDateTime.of(2026, 6, 20, 11, 0),
                    "AS", "av 123");
        });
    }

    @Test
    void testThrowsConstructorFinDeReunionIgualInicio () {
        assertThrows(IllegalArgumentException.class, ()->{
            new Reunion(LocalDateTime.of(2026, 6, 20, 12, 0), LocalDateTime.of(2026, 6, 20, 12, 0),
                    "AS", "av 123");
        });
    }

    @Test
    void testAnadirParticipantes (){
        reunion1.anadirParticipante(participante1);
        Contacto ultimoAgregado = reunion1.obtenerUltimoContactoRegistrado();
        assertEquals(participante1, ultimoAgregado);
    }

    @Test
    void testAnadirParticipantesIguales (){
        reunion1.anadirParticipante(participante2);
        reunion1.anadirParticipante(participante1);
        Contacto ultimoAgregado = reunion1.obtenerUltimoContactoRegistrado();
        assertEquals(participante2, ultimoAgregado);
    }

    @Test
    void testAnadirParticipantesValoresNull (){
        reunion1.anadirParticipante(null);
        assertEquals(0,reunion1.getCantidadParticipantes());
    }

    @Test
    void testYaExisteContactoNull () {
        assertFalse(reunion1.yaExisteContacto(null));
    }

    @Test
    void testYaExisteContactoNuevo () {
        reunion1.anadirParticipante(participante1);
        assertFalse(reunion1.yaExisteContacto(participante3));
    }

    @Test
    void testYaExisteContactoIdentifico () {
        reunion1.anadirParticipante(participante1);
        assertTrue(reunion1.yaExisteContacto(participante2));
    }

    @Test
    void testCompartimosHorarioNoSePisan (){
        Reunion reunion2 = new Reunion(LocalDateTime.of(2026, 6, 20, 14, 0),
                LocalDateTime.of(2026, 6, 20, 16, 0), "AS", "av 123");
        assertFalse(reunion1.compartimosHorario(reunion2));
    }

    @Test
    void testCompartimosHorarioEsAnterior () {
        Reunion reunion5 = new Reunion(LocalDateTime.of(2026, 6, 20, 10, 30),
                LocalDateTime.of(2026, 6, 20, 12, 30), "AS", "av 123");
        assertTrue(reunion1.compartimosHorario(reunion5));
    }

    @Test
    void testCompartimosHorarioArrancaAntes () {
        Reunion reunion3 = new Reunion(LocalDateTime.of(2026, 6, 20, 13, 30),
                LocalDateTime.of(2026, 6, 20, 15, 0), "AS", "av 123");
        assertTrue(reunion1.compartimosHorario(reunion3));
    }

    @Test
    void testCompartimosHorarioContenidaDentroDeOtra () {
        Reunion reunion4 = new Reunion(LocalDateTime.of(2026, 6, 20, 12, 30),
                LocalDateTime.of(2026, 6, 20, 13, 30), "AS", "av 123");
        assertTrue(reunion1.compartimosHorario(reunion4));
    }
}