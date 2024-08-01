package io.github.algomaster99.bytecode.diff.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.gumtreediff.actions.EditScript;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Update;
import com.github.gumtreediff.tree.TypeSet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClassFileVisitorTest {
    private static final Path RESOURCES = Path.of("src/test/resources");

    @Test
    void shouldShowDiffIn_majorVersion() throws IOException {
        // arrange
        Path oldClass = RESOURCES.resolve("majorVersion").resolve("old").resolve("A.class");
        Path newClass = RESOURCES.resolve("majorVersion").resolve("new").resolve("A.class");
        byte[] bytes1 = Files.readAllBytes(oldClass);
        byte[] bytes2 = Files.readAllBytes(newClass);
        final Builder scanner = new Builder();

        // act
        DiffImpl diff = new DiffImpl(scanner.getTreeContext(), scanner.getTree(bytes1), scanner.getTree(bytes2));
        diff.computeDiff();

        // assert
        EditScript allOperations = diff.getAllOperations();
        assertThat(allOperations.size()).isEqualTo(1);

        Update classfileVersion = (Update) allOperations.get(0);
        assertThat(classfileVersion.getNode().getType()).isEqualTo(TypeSet.type(DiffTypes.DIFF_IN_MAJOR_VERSION));
        String oldVersion = classfileVersion.getNode().getLabel();
        assertThat(oldVersion).isEqualTo("66");
        String newVersion = classfileVersion.getValue();
        assertThat(newVersion).isEqualTo("61");
    }

    @Test
    void shouldShowDiffIn_thisClass_sourceFileAttribute() throws IOException {
        // arrange
        Path random1 = RESOURCES
                .resolve("thisClass_sourceFileAttribute")
                .resolve("A4e6577e8-d1ee-4acd-b38f-1dcd68658a48.class");
        Path random2 = RESOURCES
                .resolve("thisClass_sourceFileAttribute")
                .resolve("A45305b1a-3bdc-455f-b22d-96636743c129.class");
        byte[] bytes1 = Files.readAllBytes(random1);
        byte[] bytes2 = Files.readAllBytes(random2);
        final Builder scanner = new Builder();

        // act
        DiffImpl diff = new DiffImpl(scanner.getTreeContext(), scanner.getTree(bytes1), scanner.getTree(bytes2));
        diff.computeDiff();

        // assert
        EditScript allOperations = diff.getAllOperations();
        assertThat(allOperations.size()).isEqualTo(2);

        Update sourceFileAttribute = (Update) allOperations.get(0);
        assertThat(sourceFileAttribute.getNode().getType()).isEqualTo(TypeSet.type(DiffTypes.DIFF_IN_CLASS_NAME));
        String oldClassName = sourceFileAttribute.getNode().getLabel();
        assertThat(oldClassName)
                .isEqualTo(
                        "io/dropwizard/jersey/DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443");
        String newClassName = sourceFileAttribute.getValue();
        assertThat(newClassName)
                .isEqualTo(
                        "io/dropwizard/jersey/DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862");

        Update thisClass = (Update) allOperations.get(1);
        assertThat(thisClass.getNode().getType()).isEqualTo(TypeSet.type(DiffTypes.DIFF_IN_SOURCE_FILE_ATTRIBUTE));
        String oldSourceFile = thisClass.getNode().getLabel();
        assertThat(oldSourceFile)
                .isEqualTo("DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443.java");
        String newSourceFile = thisClass.getValue();
        assertThat(newSourceFile)
                .isEqualTo("DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862.java");
    }

    @Test
    void shouldShowDiffIf_orderOfFieldAndMethodsAreFlipped() throws IOException {
        // it could be better if it shows that field names are replaced in methods
        // arrange
        Path proxy1 = RESOURCES.resolve("orderOfFieldsMethodsClint").resolve("A.class");
        Path proxy2 = RESOURCES.resolve("orderOfFieldsMethodsClint").resolve("B.class");
        byte[] bytes1 = Files.readAllBytes(proxy1);
        byte[] bytes2 = Files.readAllBytes(proxy2);
        final Builder scanner = new Builder();

        // act
        DiffImpl diff = new DiffImpl(scanner.getTreeContext(), scanner.getTree(bytes1), scanner.getTree(bytes2));
        diff.computeDiff();

        // assert
        List<Action> rootOperations = diff.getSimplifiedOperations().asList();
        assertThat(rootOperations).size().isEqualTo(12);
    }
}
