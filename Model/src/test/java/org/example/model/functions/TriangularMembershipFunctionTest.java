package org.example.model.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangularMembershipFunctionTest {

    @Test
    void evaluate() {
        TriangularMembershipFunction f = new TriangularMembershipFunction(0d, 1d, 2d);
        assertEquals(0d, f.evaluate(0d),  0.05);
        assertEquals(0.5d, f.evaluate(0.5d),  0.05);
        assertEquals(1d, f.evaluate(1d),  0.05);
        assertEquals(0.5d, f.evaluate(1.5d),  0.05);
        assertEquals(0d, f.evaluate(2d),  0.05);

    }

    @Test
    void getIntegral() {
        TriangularMembershipFunction f = new TriangularMembershipFunction(0d, 1d, 2d);
        assertEquals(1, f.getIntegral(-1, 5),  0.05);
        assertEquals(1, f.getIntegral(0, 2),  0.05);

        assertEquals(0.5, f.getIntegral(-1, 1),  0.05);
        assertEquals(0.5, f.getIntegral(0, 1),  0.05);
        assertEquals(0.5, f.getIntegral(1, 5),  0.05);
        assertEquals(0.5, f.getIntegral(1, 2),  0.05);

        assertEquals(0.5 - 0.125, f.getIntegral(0.5, 1),  0.05);
        assertEquals(0.5 - 0.125, f.getIntegral(1, 1.5),  0.05);
        assertEquals(2 * (0.5 - 0.125), f.getIntegral(0.5, 1.5),  0.05);

    }
}