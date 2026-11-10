package tools.vitruv.neojoin.tgg.transpiler.operators;

import org.junit.jupiter.api.Test;
import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.operators.LogicOperator;
import tools.vitruv.neojoin.tgg.operators.Query;
import tools.vitruv.neojoin.tgg.operators.View;
import tools.vitruv.neojoin.tgg.transpiler.TranspilerQueryResolver;
import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantFilterTest {

    private static FQN pkg(String name) {
        return new FQN("pkg", name);
    }

    private static FQN t(String name) {
        return new FQN("t", name);
    }

    private List<ResolvedQuery> resolve(View view) {
        return new TranspilerQueryResolver().resolveView(view).queries();
    }

    @Test
    void filterToRule() {
        var view = new View();
        view.addQuery(Query.from(pkg("A")).create(t("A'")).build()
                .filter(pkg("A"), "id", LogicOperator.Equals, ConstantExpression.createString("foo")));
        var queries = resolve(view);
        assertEquals(1, queries.size());
        var query = queries.get(0);
        assertEquals(
            "[src: [<++a: pkg.A;;.id==\"foo\">] tgt: [<++a': t.A';;>] corr: [++a<-->a']]",
            query.getGeneratedRules().toString()
        );
    }

    @Test
    void multiNodeFilterToRule() {
        var view = new View();
        view.addQuery(Query.from(pkg("A")).ref(pkg("B"), "b").create(t("A'")).build()
                .filter(pkg("A"), "id", LogicOperator.Equals, ConstantExpression.createString("foo")));
        var queries = resolve(view);
        assertEquals(1, queries.size());
        var query = queries.get(0);
        assertEquals(
            "[src: [<++a: pkg.A;++-[b]->b;.id==\"foo\">, <++b: pkg.B;;>] tgt: [<++a': t.A';;>] corr: [++a<-->a', "
                + "++b<-->a']]",
            query.getGeneratedRules().toString()
        );
    }

    @Test
    void multiNodeFilterToRuleAlt() {
        var view = new View();
        view.addQuery(Query.from(pkg("A")).ref(pkg("B"), "b").create(t("A'")).build()
                .filter(pkg("B"), "id", LogicOperator.Equals, ConstantExpression.createString("foo")));
        var queries = resolve(view);
        assertEquals(1, queries.size());
        var query = queries.get(0);
        assertEquals(
            "[src: [<++a: pkg.A;++-[b]->b;>, <++b: pkg.B;;.id==\"foo\">] tgt: [<++a': t.A';;>] corr: [++a<-->a', "
                + "++b<-->a']]",
            query.getGeneratedRules().toString()
        );
    }
}
