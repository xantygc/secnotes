package com.axpo.secnotes.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.axpo.secnotes", importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class LayeredArchitectureTests
{

        @ArchTest
        static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

                .layer("Controllers").definedBy("com.axpo.secnotes.application.controller..")
                .layer("Services").definedBy("com.axpo.secnotes.application.service..")
                .layer("Persistence").definedBy("com.axpo.secnotes.infraestructure..")

                .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
                .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");


}
