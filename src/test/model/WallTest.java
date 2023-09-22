package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {
    private Wall w1;
    private Wall w2;

    @BeforeEach
    public void setUp() {
        w1 = new Wall();
        w2 = new Wall();
    }

    @Test
    public void testConstructor() {
        assertEquals ("", w1.getSheet());
        assertEquals ("", w1.getMaterial());
        assertEquals (0, w1.getAreaDensity());

        assertEquals ("", w2.getSheet());
        assertEquals ("", w2.getMaterial());
        assertEquals (0, w2.getAreaDensity());
    }

    @Test
    public void testSetUpTwo() {
        w1.assignSheet("SINGLE");
        w1.assignMaterial("CRAFT");
        w1.assignAreaDensity(250);

        assertEquals ("SINGLE", w1.getSheet());
        assertEquals ("CRAFT", w1.getMaterial());
        assertEquals (250, w1.getAreaDensity());

        w2.assignSheet("DOUBLE");
        w2.assignMaterial("MEDIUM");
        w2.assignAreaDensity(150);

        assertEquals ("DOUBLE", w2.getSheet());
        assertEquals ("MEDIUM", w2.getMaterial());
        assertEquals (150, w2.getAreaDensity());
    }

    @Test
    public void testSetUpThree_Limit() {
        w1.assignAreaDensity(101);
        assertEquals(101, w1.getAreaDensity());
        w1.assignAreaDensity(102);
        assertEquals(102, w1.getAreaDensity());
        w1.assignAreaDensity(103);
        assertEquals(103, w1.getAreaDensity());
        w1.assignAreaDensity(99);
        assertEquals(103, w1.getAreaDensity());

        w1.assignAreaDensity(247);
        assertEquals(247, w1.getAreaDensity());
        w1.assignAreaDensity(248);
        assertEquals(248, w1.getAreaDensity());
        w1.assignAreaDensity(249);
        assertEquals(249, w1.getAreaDensity());
        w1.assignAreaDensity(251);
        assertEquals(249, w1.getAreaDensity());
    }

    @Test
    public void testSetUpFour_TwoChange() {
        w1.assignSheet("SINGLE");
        w1.assignSheet("DOUBLE");
        w1.assignSheet("TROUBADOUR");
        assertEquals("DOUBLE", w1.getSheet());

        w1.assignMaterial("CRAFT");
        w1.assignMaterial("MEDIUM");
        w1.assignMaterial("HARD");
        assertEquals("MEDIUM", w1.getMaterial());

        w1.assignAreaDensity(101);
        w1.assignAreaDensity(230);
        assertEquals(230, w1.getAreaDensity());
    }
}
