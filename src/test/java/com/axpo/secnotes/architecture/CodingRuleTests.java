package com.axpo.secnotes.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;
import org.slf4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(packages = "com.axpo.secnotes", importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class CodingRuleTests
{
    @ArchTest
    private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    private final ArchRule loggers_should_be_private_static_final =
            fields().that().haveRawType(Logger.class)
                    .should().bePrivate()
                    .andShould().beStatic()
                    .andShould().beFinal()
                    .because("we agreed on this convention");

    @ArchTest
    private final ArchRule no_jodatime = NO_CLASSES_SHOULD_USE_JODATIME;

    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    static final ArchRule no_classes_should_access_standard_streams_or_throw_generic_exceptions =
            CompositeArchRule.of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS)
                    .and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS);

}
