package io.github.chubbyhippo.insurance;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithDocsTest {

    @Test
    void writeDocs() {
        var modules = ApplicationModules.of(InsuranceApplication.class);
        new Documenter(modules).writeDocumentation();
    }
}
