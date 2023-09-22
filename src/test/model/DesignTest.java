package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesignTest {
    private Design testDesign;
    private Wall testWall;

    @BeforeEach
    void setUp() {
        testDesign = new Design();
        testWall = new Wall();
        testWall.assignSheet("SINGLE");
        testWall.assignMaterial("CRAFT");
        testWall.assignAreaDensity(250);
    }

     @Test
    public void testConstructor() {
        assertEquals("", testDesign.getFrontAndBack().getSheet());
        assertEquals("", testDesign.getFrontAndBack().getMaterial());
        assertEquals(0, testDesign.getFrontAndBack().getAreaDensity());

        assertEquals("", testDesign.getLeftAndRight().getSheet());
        assertEquals("", testDesign.getLeftAndRight().getMaterial());
        assertEquals(0, testDesign.getLeftAndRight().getAreaDensity());

        assertEquals("", testDesign.getTopAndBottom().getSheet());
        assertEquals("", testDesign.getTopAndBottom().getMaterial());
        assertEquals(0, testDesign.getTopAndBottom().getAreaDensity());
    }

    @Test
    public void testIndividualCustomization() {
        testDesign.frontAndBackCustomizeSheet("SINGLE");
        testDesign.frontAndBackCustomizeMaterial("CRAFT");
        testDesign.frontAndBackCustomizeAreaDensity(150);

        testDesign.topAndBottomCustomizeSheet("DOUBLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(250);

        testDesign.leftAndRightCustomizeSheet("SINGLE");
        testDesign.leftAndRightCustomizeMaterial("MEDIUM");
        testDesign.leftAndRightCustomizeAreaDensity(100);
    }

    @Test
    public void testCustomization() {
        testDesign.frontAndBackCustomizeSheet("SINGLE");
        testDesign.frontAndBackCustomizeMaterial("CRAFT");
        testDesign.frontAndBackCustomizeAreaDensity(150);

        testDesign.leftAndRightCustomizeSheet("DOUBLE");
        testDesign.leftAndRightCustomizeMaterial("MEDIUM");
        testDesign.leftAndRightCustomizeAreaDensity(200);

        testDesign.topAndBottomCustomizeSheet("SINGLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(200);

        assertEquals("SINGLE", testDesign.getFrontAndBack().getSheet());
        assertEquals("CRAFT", testDesign.getFrontAndBack().getMaterial());
        assertEquals(150, testDesign.getFrontAndBack().getAreaDensity());

        assertEquals("DOUBLE", testDesign.getLeftAndRight().getSheet());
        assertEquals("MEDIUM", testDesign.getLeftAndRight().getMaterial());
        assertEquals(200, testDesign.getLeftAndRight().getAreaDensity());

        assertEquals("SINGLE", testDesign.getTopAndBottom().getSheet());
        assertEquals("CRAFT", testDesign.getTopAndBottom().getMaterial());
        assertEquals(200, testDesign.getTopAndBottom().getAreaDensity());
    }

    @Test
    public void testCustomizeAll() {
        testDesign.frontAndBackCustomizeSheet("SINGLE");
        testDesign.frontAndBackCustomizeMaterial("CRAFT");
        testDesign.frontAndBackCustomizeAreaDensity(150);

        testDesign.leftAndRightCustomizeSheet("DOUBLE");
        testDesign.leftAndRightCustomizeMaterial("MEDIUM");
        testDesign.leftAndRightCustomizeAreaDensity(200);

        testDesign.topAndBottomCustomizeSheet("SINGLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(200);

        assertEquals("SINGLE", testDesign.getFrontAndBack().getSheet());
        assertEquals("CRAFT", testDesign.getFrontAndBack().getMaterial());
        assertEquals(150, testDesign.getFrontAndBack().getAreaDensity());

        assertEquals("DOUBLE", testDesign.getLeftAndRight().getSheet());
        assertEquals("MEDIUM", testDesign.getLeftAndRight().getMaterial());
        assertEquals(200, testDesign.getLeftAndRight().getAreaDensity());

        assertEquals("SINGLE", testDesign.getTopAndBottom().getSheet());
        assertEquals("CRAFT", testDesign.getTopAndBottom().getMaterial());
        assertEquals(200, testDesign.getTopAndBottom().getAreaDensity());

        testDesign.frontAndBackCustomizeSheet("DOUBLE");
        testDesign.frontAndBackCustomizeMaterial("CRAFT");
        testDesign.frontAndBackCustomizeAreaDensity(150);

        testDesign.leftAndRightCustomizeSheet("SINGLE");
        testDesign.leftAndRightCustomizeMaterial("CRAFT");
        testDesign.leftAndRightCustomizeAreaDensity(100);

        testDesign.topAndBottomCustomizeSheet("DOUBLE");
        testDesign.topAndBottomCustomizeMaterial("MEDIUM");
        testDesign.topAndBottomCustomizeAreaDensity(250);

        assertEquals("DOUBLE", testDesign.getFrontAndBack().getSheet());
        assertEquals("CRAFT", testDesign.getFrontAndBack().getMaterial());
        assertEquals(150, testDesign.getFrontAndBack().getAreaDensity());

        assertEquals("SINGLE", testDesign.getLeftAndRight().getSheet());
        assertEquals("CRAFT", testDesign.getLeftAndRight().getMaterial());
        assertEquals(100, testDesign.getLeftAndRight().getAreaDensity());

        assertEquals("DOUBLE", testDesign.getTopAndBottom().getSheet());
        assertEquals("MEDIUM", testDesign.getTopAndBottom().getMaterial());
        assertEquals(250, testDesign.getTopAndBottom().getAreaDensity());
    }
}
