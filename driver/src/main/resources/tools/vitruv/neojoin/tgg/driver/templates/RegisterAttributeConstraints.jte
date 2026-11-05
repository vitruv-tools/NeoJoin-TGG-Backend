@import tools.vitruv.neojoin.tgg.driver.ConstraintSolver
@import tools.vitruv.neojoin.tgg.driver.Project
@param Project p

import org.emoflon.neo.engine.modules.NeoGenerator;

class RegisterAttributeConstraints {
    static void register(NeoGenerator generator) {
        @for(ConstraintSolver solver : p.constraintSolvers())
        generator.getAttrConstrContainer().addCreator("${solver.name()}", () -> new ${solver.className()}());
        @endfor
    }
}