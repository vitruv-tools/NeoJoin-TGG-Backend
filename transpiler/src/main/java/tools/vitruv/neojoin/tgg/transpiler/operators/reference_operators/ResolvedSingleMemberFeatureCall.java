package tools.vitruv.neojoin.tgg.transpiler.operators.reference_operators;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGSlice;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGNode;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRule;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.GraphPathToNode;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRulesBuilder;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGLink;

public class ResolvedSingleMemberFeatureCall implements ResolvedReferenceOperator {
    private final String feature;
    private final FQN featureElement;

    public ResolvedSingleMemberFeatureCall(String feature, FQN featureElement) {
        this.feature = feature;
        this.featureElement = featureElement;
    }

    public String getFeature() {
        return feature;
    }

    public FQN getFeatureElement() {
        return featureElement;
    }

    @Override
    public void extendRules(TripleRulesBuilder builder) {
        final TripleRule rule = builder.getLatestRule();

        final GraphPathToNode pathToLastNode = builder.getPathToLastNode();
        final TGGNode lastSourceNode = rule.findNestedSourceNode(pathToLastNode);

        final TGGSlice sourceSlice = rule.addSourceSlice();
        TGGNode childNode = sourceSlice.addNode(featureElement);
        childNode.makeGreen();

        TGGLink parentLinkToChild = TGGLink.createGreen(feature, childNode);
        builder.addLinkToPathToLastNode(feature);
        lastSourceNode.addLink(parentLinkToChild);
    }
}
