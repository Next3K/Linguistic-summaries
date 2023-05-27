package org.example.model.functions;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

class GaussianMembershipFunctionTest {

    @Test
    void getIntegral() {
        GaussianMembershipFunction gaussianMembershipFunction =
                new GaussianMembershipFunction(1000, 500);
        assertEquals(855.070, gaussianMembershipFunction.getIntegral(500, 1500), 5d);
    }

    @Test
    void getValue() {
        GaussianMembershipFunction gaussianMembershipFunction =
                new GaussianMembershipFunction(1000, 500);
        assertEquals(1d, gaussianMembershipFunction.evaluate(1000d), 0.1d);
    }
}