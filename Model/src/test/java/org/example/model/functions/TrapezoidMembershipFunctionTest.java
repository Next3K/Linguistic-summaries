package org.example.model.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapezoidMembershipFunctionTest {

    @Test
    void evaluateAreaFull() {
        TrapezoidMembershipFunction trapezoidMembershipFunction =
                new TrapezoidMembershipFunction(0d, 1d, 2d, 3d);
        assertEquals(2, trapezoidMembershipFunction.getIntegral(1d, 4d));
    }

    @Test
    void areaAreaHalf() {
        TrapezoidMembershipFunction trapezoidMembershipFunction =
                new TrapezoidMembershipFunction(0d, 1d, 2d, 3d);
        assertEquals(1, trapezoidMembershipFunction.getIntegral(-1d, 1.5d), 0.05);
        assertEquals(1, trapezoidMembershipFunction.getIntegral(0d, 1.5d), 0.05);
    }

    @Test
    void areaAlmostAll() {
        TrapezoidMembershipFunction trapezoidMembershipFunction =
                new TrapezoidMembershipFunction(0d, 1d, 2d, 3d);
        assertEquals(1.5, trapezoidMembershipFunction.getIntegral(-1d, 2d), 0.05);
        assertEquals(1.5, trapezoidMembershipFunction.getIntegral(0d, 2d), 0.05);
    }

    @Test
    void areaAlmostAlmostAll() {
        TrapezoidMembershipFunction trapezoidMembershipFunction =
                new TrapezoidMembershipFunction(0d, 1d, 2d, 3d);
        assertEquals(1.5 + 0.375, trapezoidMembershipFunction.getIntegral(-1d, 2.5d), 0.05);
        assertEquals(1.5 + 0.375, trapezoidMembershipFunction.getIntegral(0d, 2.5d), 0.05);
    }

    @Test
    void areaInsider() {
        TrapezoidMembershipFunction trapezoidMembershipFunction =
                new TrapezoidMembershipFunction(0d, 1d, 2d, 3d);
        assertEquals(1, trapezoidMembershipFunction.getIntegral(1d, 2d), 0.05);
        assertEquals(1 + 2 * 0.375, trapezoidMembershipFunction.getIntegral(0.5d, 2.5d), 0.05);
    }

    @Test
    void getIntegral() {
        TrapezoidMembershipFunction trapezoidMembershipFunction =
                new TrapezoidMembershipFunction(0d, 1d, 2d, 3d);
        assertEquals(0, trapezoidMembershipFunction.evaluate(0d));
        assertEquals(0.5, trapezoidMembershipFunction.evaluate(0.5d));
        assertEquals(1, trapezoidMembershipFunction.evaluate(1d));
        assertEquals(1, trapezoidMembershipFunction.evaluate(1.5d));
        assertEquals(1, trapezoidMembershipFunction.evaluate(2d));
        assertEquals(0.5, trapezoidMembershipFunction.evaluate(2.5d));
        assertEquals(0, trapezoidMembershipFunction.evaluate(3d));
    }
}