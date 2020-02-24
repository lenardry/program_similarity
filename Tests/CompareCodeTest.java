import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompareCodeTest {

    @org.junit.Test
    public void compareFolders() throws IOException {
        CompareCode compareCode = new CompareCode();
        ArrayList<Double> grades = new ArrayList<>();
        grades.add(0.1564083997103548);
        grades.add(0.036514522821576766);
        grades.add(0.0);
        grades.add(0.1063993831919815);

        assertEquals(compareCode.compareFolders("antoc-vicente", "coteok-portugal"), grades);
    }

    @org.junit.Test
    public void compareFiles1() throws IOException {
        CompareCode compareCode = new CompareCode();
        assertEquals(compareCode.compareFiles("Enghoy_main.java", "Enghoy_main.java"), 1.0, 1.0);
    }
}