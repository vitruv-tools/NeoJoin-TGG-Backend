package tools.vitruv.neojoin.tgg.transpiler.graph.tgg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.constraint.ArbitraryAttributeConstraint;

class ArbitraryAttributeConstraintTest {
    @Test
    void testOrdering() {
        var constraint = new ArbitraryAttributeConstraint("foo");
        constraint.addParameter("a", ConstantExpression.createPrimitive(""));
        constraint.addParameter("c", ConstantExpression.createPrimitive(""));
        constraint.addParameter("return", ConstantExpression.createPrimitive(""));
        constraint.addParameter("self", ConstantExpression.createPrimitive(""));
        constraint.addParameter("b", ConstantExpression.createPrimitive(""));
        var parameters = constraint.getParameters().stream().toList();
        assertEquals("self", parameters.get(0).attribute());
        assertEquals("a", parameters.get(1).attribute());
        assertEquals("b", parameters.get(2).attribute());
        assertEquals("c", parameters.get(3).attribute());
        assertEquals("return", parameters.get(4).attribute());
    }
}
