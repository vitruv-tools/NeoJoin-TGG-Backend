# NeoJoin TGG Backend

[![GitHub Action CI](https://github.com/vitruv-tools/NeoJoin-TGG-Backend/actions/workflows/ci.yml/badge.svg)](https://github.com/vitruv-tools/NeoJoin-TGG-Backend/actions/workflows/ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vitruv-tools_NeoJoin-TGG-Backend&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=vitruv-tools_NeoJoin-TGG-Backend)
[![Issues](https://img.shields.io/github/issues/vitruv-tools/NeoJoin-TGG-Backend.svg)](https://github.com/vitruv-tools/NeoJoin-TGG-Backend/issues)
[![License](https://img.shields.io/github/license/vitruv-tools/NeoJoin-TGG-Backend.svg)](https://raw.githubusercontent.com/vitruv-tools/NeoJoin-TGG-Backend/main/LICENSE)

This repository contains the triple graph grammar (TGG) backend of [NeoJoin](https://github.com/vitruv-tools/NeoJoin), a declarative query language for view-based model-driven software development.
The backend transforms the operators of a view definition into TGGs and generates eMoflon::Neo projects that support bidirectional and incremental transformations between models and views.

The backend builds on the abstract query representation (`model`) and the reference expression parser (`expression-parser`) of the NeoJoin repository.
The language frontend of NeoJoin is only used in the tests of the driver.
Other repositories of the project are listed under the [neojoin topic](https://github.com/topics/neojoin) on GitHub.

## Usage

### Requirements

* JDK 21+
* [Neo4j](https://neo4j.com/) *(for running the generated transformations)*
* [eMoflon::Neo](https://github.com/eMoflon/emoflon-neo) installed in an Eclipse instance *(for running the generated transformations)*

### Transformation of Operators to Triple Graph Grammars

1. Build view from operators directly: `var view = new View(); view.addQuery(...);`
2. Resolve interdependent queries (see documentation in `Query`): `var queries = view.resolveQueries()`
3. Get the TGG rules and constraints generated for a query: `var rules = query.getGeneratedRules(); var constraints = query.getGeneratedConstraints();`
4. Build triple grammar from the TGG rules and constraints: `var grammar = new TripleGrammar(name, rules, constraints, sourceMetamodels, targetMetamodels);`
5. Optional: generate eMoflon::Neo project: `new Scaffolding(...).create(...);`

You can use the convenience function `API.generateProjectForView(...)` for steps 2 to 5.

### Running the Generated Model-View Transformations

Prerequisites:

- Eclipse with [eMoflon::Neo](https://emoflon.org/download/#emoflonneo) set up
- [Neo4j Desktop](https://neo4j.com/deployment-center/) **version 4.4.X**

Make sure the Neo4j database is running and the Neo4j username/password is correctly configured in Eclipse. Additional
information about how to set up Neo4j and eMoflon::Neo is
available [here](https://emoflon.org/getting-started/#emoflonneo).

The generated eMoflon::Neo project can be used for various model checking and transformation scenarios. The following
steps describe how to execute the forward and sync transformations with an initial source model:

1. Open the generated project folder with Eclipse
    - The `src/*.msl` files contain the generated TGG project and (meta)models
    - The `src/*Runner.java` files can be executed to sync the source and target models with Neo4j
    - For each source model, a `src/*ForwardRunner.java` is available to generate the target model and establish correspondences
2. Right-click the desired `src/*ForwardRunner.java` > `Run As` > `Java Application`
    - This will clear the Neo4j database and run the ForwardRunner for this model
3. Run `match (source {enamespace: "YOUR_SOURCE_MODEL_NAME"}), (target {enamespace: "Target"}) return source, target` in the Neo4j Browser
    - This will output the graph with the source and target elements as well as the correspondences
4. Update the source and/or target model with [Neo4j Clauses](https://neo4j.com/docs/cypher-manual/current/clauses/)
    - Make sure the property `_cr_: true` is added to created nodes and edges
    - Make sure the correct `enamespace: "YOUR_SOURCE_MODEL_NAME"` property is added to created nodes
    - Make sure the property `_de_: true` is added to "deleted" nodes and edges
5. Right-click the desired `src/*SyncRunner.java` > `Run As` > `Java Application`
    - This will run the SyncRunner for this model
    - Afterward, the source and target should be consistent again
6. Optional: Dump the models using the [script](/scripts/dump/readme.md)
    - Make sure the source and target model names are correctly configured

## Repository Structure

### Project
| Directory    | Content                                                 |
|--------------|---------------------------------------------------------|
| docs         | Documentation of the operators and the TGG generation   |
| scripts      | Helper scripts for the project                          |
| scripts/dump | Script for exporting models from a Neo4j graph database |

### Modules
| **Module**      | Description                                                                                 |
|-----------------|---------------------------------------------------------------------------------------------|
| **driver**      | Auxiliary logic for generating eMoflon::Neo Eclipse projects                                |
| **operators**   | Primary operators for creating TGGs                                                         |
| **transpiler**  | The core logic and operators for creating TGGs from operators                               |
| **emsl-utils**  | Generation of eMSL (meta)models from EMF (meta)models                                       |
| **emsl-parser** | Parser for eMSL (meta)models                                                                |
| **p2wrappers**  | Maven wrapper for the eMoflon::Neo eMSL bundle, which is only available on a p2 update site |

## Build

### Requirements

* JDK 21+

### Maven Projects

```sh
./mvnw verify
```

The build resolves the NeoJoin artifacts it depends on from the Maven Central snapshot repository, which the NeoJoin repository updates with every nightly build.
This repository does not publish artifacts yet; GitHub Actions only builds and tests it.
We use [SonarQube](https://sonarcloud.io/project/overview?id=vitruv-tools_NeoJoin-TGG-Backend) to analyze our Maven projects.

### Code Style

The code style is checked with [Checkstyle](https://checkstyle.org/) (`checkstyle.xml`). The check is optional and part of the Maven profile `checkstyle`, which prints violations as warnings and does not fail the build:

```sh
# only the style check (fast)
./mvnw checkstyle:check@validate -P checkstyle
# or: as part of the whole build
./mvnw verify -P checkstyle
```

Add `-Dcheckstyle.severity=error` to print the violations as errors and fail the build at the first module with violations. The CI job `Checkstyle` checks all modules and fails while there are violations.

## Used Technology

* [eMoflon::Neo](https://github.com/eMoflon/emoflon-neo) as TGG engine
* [Neo4j](https://neo4j.com/) as graph database
* [jte](https://jte.gg/) as templating engine
* [EMF](https://eclipse.dev/emf/) as modeling framework
