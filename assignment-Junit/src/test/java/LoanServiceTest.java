import org.example.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LoanServiceTest {
    LoanService service = new LoanService();

    @Test
    void testValidEligibility() {
        assertTrue(service.isEligible(30, 30000));
    }
    @Test
    void testInvalidAgeLow() {
        assertFalse(service.isEligible(18, 30000));
    }
   @Test
    void testInvalidSalary(){
        assertFalse(service.isEligible(28,20000));
   }
   @Test
    void testBoundaryValues(){
        assertTrue(service.isEligible(21,30000));
        assertTrue(service.isEligible(60,30000));
   }
   @Test
    void testValidEmi(){
       double emi = service.calculateEMI(120000, 1);
       assertEquals(10000, emi);
   }
   @Test
   void testInvalidLoanAmount() {
       assertThrows(IllegalArgumentException.class, () -> {
           service.calculateEMI(0, 2);
       });
   }

    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(100000, 0);
        });
    }

    @Test
    void testLoanCategories() {
        assertAll("Credit Score Categories",
                () -> assertEquals("Premium", service.getLoanCategory(800)),
                () -> assertEquals("Standard", service.getLoanCategory(650)),
                () -> assertEquals("High Risk", service.getLoanCategory(500))
        );
    }

    @Test
    void testCreditScoreBoundaries() {
        assertEquals("Premium", service.getLoanCategory(750));
        assertEquals("Standard", service.getLoanCategory(600));
        assertEquals("High Risk", service.getLoanCategory(599));
    }

    @Test
    void testServiceNotNull() {
        assertNotNull(service);
    }


}
