
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ContactoTest {
private  Contacto contactoDePrueba1;
private  Contacto contactoDePrueba2;
private  Reunion reunionDePrueba;

    @BeforeEach
    void inicializarParametros (){
        contactoDePrueba1 = new Contacto("Roberto","2494066642","1@gmail");
        contactoDePrueba2 = new Contacto("Maria","2494066652","2@gmail");
        reunionDePrueba = new Reunion();
    }
    @Test
    void testThrowsConstructorNombreNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Contacto(null,"2494066642","1@gmail");
        });
    }

    @Test
    void testThrowsConstructorNombreVacio(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Contacto("","2494066642","1@gmail");
        });
    }

    @Test
    void testThrowsConstructorMailSinArroba(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Contacto("Roberto","2494066642","1gmail");
        });
    }

    @Test
    void testThrowsConstructorMailNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Contacto("Roberto","2494066642",null);
        });
    }
    @Test
    void testThrowsConstructorMailVacio(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Contacto("Roberto","2494066642","");
        });
    }

    @Test
    void testEqualsIdentificarDiferente (){
        assertNotEquals(true,contactoDePrueba1.equals(contactoDePrueba2));
    }

    @Test
    void testEqualsIdentificarIgual () {
        Contacto mismo = new Contacto("Roberto","2494066652","1@gmail");
        assertTrue(contactoDePrueba1.equals(mismo));
    }

    @Test
    void testEqualsDatosNull () {
        assertFalse(contactoDePrueba1.equals(null));
    }

    @Test
    void testEqualDatosInvalidos () {
        assertFalse(contactoDePrueba1.equals(reunionDePrueba));
    }

    @Test
    void testEqualContactoComparable () {
        contactoDePrueba1.setNombre(null);
        assertFalse(contactoDePrueba1.equals(contactoDePrueba2));
    }

}
