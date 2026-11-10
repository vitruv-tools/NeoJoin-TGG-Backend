package tools.vitruv.neojoin.tgg.transpiler.operators;

import org.junit.jupiter.api.Test;
import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.transpiler.TranspilerQueryResolver;
import tools.vitruv.neojoin.tgg.transpiler.operators.patterns.ResolvedPattern;
import tools.vitruv.neojoin.tgg.operators.selection.Pattern;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRule;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResolvedContainmentTest {

    private static FQN pkg(String name) {
        return new FQN("pkg", name);
    }

    private static FQN t(String name) {
        return new FQN("t", name);
    }

    private ResolvedPattern resolve(Pattern pattern) {
        return new TranspilerQueryResolver().resolvePattern(pattern);
    }


    @Test
    void toRule() {
        var rule = new TripleRule();
        rule.addSourceSlice().addNode(pkg("B")).makeGreen();
        rule.addTargetSlice().addNode(t("B'")).makeGreen();
        assertEquals("src: [<++b: pkg.B;;>] tgt: [<++b': t.B';;>] corr: []", rule.toString());
        var source = resolve(Pattern.from(pkg("A")).ref(pkg("B"), "b"));
        var target = resolve(Pattern.from(t("A'")).ref(t("B'"), "b"));
        var container = new ResolvedContainment(source, target, List.of());
        container.extendRule(rule);
        System.out.println(rule);
        assertEquals(
            "src: [<++b: pkg.B;;>, <a: pkg.A;++-[b]->b;>] tgt: [<++b': t.B';;>, <a': t.A';++-[b]->b';>] corr: "
                + "[a<-->a']",
            rule.toString()
        );
    }

}
