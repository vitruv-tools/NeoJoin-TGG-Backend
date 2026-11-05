@import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGLink
@param TGGLink link
@if(link.isGreen())++@endif-${link.getName()}->${link.getTarget().getId()}
