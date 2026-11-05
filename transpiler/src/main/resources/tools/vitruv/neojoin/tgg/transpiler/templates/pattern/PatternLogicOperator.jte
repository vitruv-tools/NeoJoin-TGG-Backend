@import tools.vitruv.neojoin.tgg.operators.LogicOperator
@import tools.vitruv.neojoin.tgg.transpiler.graph.pattern.PatternNode
@param LogicOperator operator
@param PatternNode node
@if(operator == LogicOperator.Equals):
@elseif(operator == LogicOperator.NotEquals)!=
@elseif(operator == LogicOperator.LessThan)<
@elseif(operator == LogicOperator.LessEquals)<=
@elseif(operator == LogicOperator.MoreThan)>
@elseif(operator == LogicOperator.MoreEquals)>=
@endif
