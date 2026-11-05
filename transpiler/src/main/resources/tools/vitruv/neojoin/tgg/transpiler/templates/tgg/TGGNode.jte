@import tools.vitruv.neojoin.tgg.transpiler.NameResolver
@import tools.vitruv.neojoin.tgg.transpiler.graph.Attribute
@import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGNode
@import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGLink
@param TGGNode node
@param NameResolver nameResolver
@if(node.isGreen())++@endif ${node.getId()}: ${nameResolver.resolveName(node.getType())} {
    @for(Attribute attr : node.attributes())@template.tgg.TGGAttribute(node = node, attribute = attr)
    @endfor

    @for(TGGLink link : node.links())@template.tgg.TGGLink(link = link)
    @endfor
}
