package tools.vitruv.neojoin.tgg.transpiler.operators.reference_operators;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGSlice;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGNode;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRule;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.GraphPathToNode;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRulesBuilder;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGLink;

public class ResolvedCollectReferences implements ResolvedReferenceOperator {
    private final FQN targetRoot;
    private final FQN targetLeaf;
    private final String targetField;

    public ResolvedCollectReferences(FQN targetRoot, FQN targetLeaf, String targetField) {
        this.targetRoot = targetRoot;
        this.targetLeaf = targetLeaf;
        this.targetField = targetField;
    }

    public FQN getTargetRoot() {
        return targetRoot;
    }

    @Override
    public void extendRules(TripleRulesBuilder builder) {
        final TripleRule latestRule = builder.getLatestRule();

        final GraphPathToNode pathToLastNode = builder.getPathToLastNode();
        final TGGNode lastSourceNode = latestRule.findNestedSourceNode(pathToLastNode);
        lastSourceNode.makeBlack();

        final TGGNode targetSourceNode = latestRule.findTargetNodeByType(targetRoot).orElseThrow();

        final TGGSlice targetSlice = latestRule.addTargetSlice();
        TGGNode targetChildNode = targetSlice.addNode(targetLeaf);

        TGGLink targetParentLinkToChild = TGGLink.createGreen(targetField, targetChildNode);
        targetSourceNode.addLink(targetParentLinkToChild);

        latestRule.addCorrespondenceRule(lastSourceNode, targetChildNode);
    }
}
