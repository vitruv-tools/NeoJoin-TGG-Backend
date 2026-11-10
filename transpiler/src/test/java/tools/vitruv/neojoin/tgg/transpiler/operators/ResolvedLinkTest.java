package tools.vitruv.neojoin.tgg.transpiler.operators;

import org.junit.jupiter.api.Test;
import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.transpiler.TranspilerQueryResolver;
import tools.vitruv.neojoin.tgg.transpiler.operators.patterns.ResolvedPattern;
import tools.vitruv.neojoin.tgg.operators.selection.Pattern;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRule;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResolvedLinkTest {
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
    void extendRule() {
        var rule = new TripleRule();
        rule.addSourceSlice().addNode(pkg("A"));
        rule.addTargetSlice().addNode(t("A'"));
        assertEquals("src: [<a: pkg.A;;>] tgt: [<a': t.A';;>] corr: []", rule.toString());
        var source = resolve(Pattern.from(pkg("A")).ref(pkg("B"), "b"));
        var target = resolve(Pattern.from(t("A'")).ref(t("B'"), "b"));
        var link = new ResolvedLink(source, target, List.of());
        link.extendRule(rule);
        assertEquals(
            "src: [<a: pkg.A;++-[b]->b;>, <b: pkg.B;;>] tgt: [<a': t.A';++-[b]->b';>, <b': t.B';;>] corr: [b<-->b']",
            rule.toString()
        );
    }

    @Test
    void extendComplexRule() {
        var rule = new TripleRule();
        rule.addSourceSlice().addNode(pkg("A"));
        rule.addTargetSlice().addNode(t("A'"));
        assertEquals("src: [<a: pkg.A;;>] tgt: [<a': t.A';;>] corr: []", rule.toString());
        var source = resolve(Pattern.from(pkg("A")).ref(pkg("B"), "b").ref(pkg("C"), "c"));
        var target = resolve(Pattern.from(t("A'")).ref(t("B'"), "b"));
        var link = new ResolvedLink(source, target, List.of());
        link.extendRule(rule);
        assertEquals(
            "src: [<a: pkg.A;++-[b]->b;>, <++b: pkg.B;++-[c]->c;>, <c: pkg.C;;>] tgt: [<a': t.A';++-[b]->b';>, <b': "
                + "t.B';;>] corr: [c<-->b']",
            rule.toString()
        );
    }
}
