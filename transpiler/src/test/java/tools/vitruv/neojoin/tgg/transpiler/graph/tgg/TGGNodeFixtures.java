package tools.vitruv.neojoin.tgg.transpiler.graph.tgg;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.transpiler.graph.Attribute;
import tools.vitruv.neojoin.tgg.transpiler.graph.NameRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class TGGNodeFixtures {
    public static TGGNode someTGGNode(
            String id,
            FQN type,
            boolean green,
            NameRepository nameRepository,
            ArrayList<TGGLink> links,
            LinkedHashSet<Attribute> attributes) {
        return new TGGNode(id, type, green, nameRepository, links, attributes);
    }
}
