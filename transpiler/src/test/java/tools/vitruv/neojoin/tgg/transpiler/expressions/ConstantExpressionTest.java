package tools.vitruv.neojoin.tgg.transpiler.expressions;

import org.junit.jupiter.api.Test;
import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantExpressionTest {
    @Test
    void primitive() {
        assertEquals("5", ConstantExpression.createPrimitive(5).value());
    }

    @Test
    void primitiveString() {
        assertEquals("5", ConstantExpression.createPrimitive("5").value());
        assertEquals("Foo", ConstantExpression.createPrimitive("Foo").value());
    }

    @Test
    void string() {
        assertEquals("\"5\"", ConstantExpression.createString("5").value());
        assertEquals("\"Foo\"", ConstantExpression.createString("Foo").value());
    }

}
