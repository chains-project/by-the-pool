package io.github.algomaster99.bytecode.diff.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.gumtreediff.actions.EditScript;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Delete;
import com.github.gumtreediff.actions.model.Update;
import com.github.gumtreediff.tree.TypeSet;
import java.io.IOException;
import java.lang.classfile.Opcode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ClassFileVisitorTest {
    private static final Path RESOURCES = Path.of("src/test/resources");

    private static final Path EQ = RESOURCES.resolve("EQ");

    @Test
    void shouldShowDiffIn_majorVersion() throws IOException {
        // arrange
        Path oldClass = RESOURCES.resolve("majorVersion").resolve("old").resolve("A.class");
        Path newClass = RESOURCES.resolve("majorVersion").resolve("new").resolve("A.class");
        DiffImpl diff = getDiff(oldClass, newClass);

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
        DiffImpl diff = getDiff(random1, random2);

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
        // arrange
        Path proxy1 = RESOURCES.resolve("orderOfFieldsMethodsClint").resolve("A.class");
        Path proxy2 = RESOURCES.resolve("orderOfFieldsMethodsClint").resolve("B.class");
        DiffImpl diff = getDiff(proxy1, proxy2);

        // assert
        List<Action> rootOperations = diff.getSimplifiedOperations().asList();
        assertThat(rootOperations).size().isEqualTo(6);
    }

    @Test
    void shouldShowDiff_onlyInClassfileVersion() throws IOException {
        // arrange
        Path proxy1 = RESOURCES.resolve("classfileVersion").resolve("ClientMain6.class");
        Path proxy2 = RESOURCES.resolve("classfileVersion").resolve("ClientMain8.class");
        DiffImpl diff = getDiff(proxy1, proxy2);

        // assert
        List<Action> rootOperations = diff.getSimplifiedOperations().asList();
        assertThat(rootOperations).size().isEqualTo(1);

        Update classfileVersion = (Update) rootOperations.get(0);
        assertThat(classfileVersion.getNode().getType()).isEqualTo(TypeSet.type(DiffTypes.DIFF_IN_MAJOR_VERSION));
        String oldVersion = classfileVersion.getNode().getLabel();
        assertThat(oldVersion).isEqualTo("50");
        String newVersion = classfileVersion.getValue();
        assertThat(newVersion).isEqualTo("52");
    }

    @Test
    void onlyOneInstructionShouldBeInserted() throws IOException {
        // arrange
        Path fileA = RESOURCES.resolve("insertInstruction").resolve("old").resolve("A.class");
        Path fileB = RESOURCES.resolve("insertInstruction").resolve("new").resolve("A.class");
        DiffImpl diff = getDiff(fileA, fileB);

        // assert
        List<Action> rootOperations = diff.getRootOperations();
        assertThat(rootOperations).size().isEqualTo(3);

        assertThat(rootOperations.get(0).getNode().getType().name).isEqualTo(Opcode.GETSTATIC.name());
        assertThat(rootOperations.get(1).getNode().getType().name).isEqualTo(Opcode.LDC.name());
        assertThat(rootOperations.get(2).getNode().getType().name).isEqualTo(Opcode.INVOKEVIRTUAL.name());
    }

    @Test
    void shouldDetect_removalOfCheckCast_updateOfReturnTypeOfMethodInvoked() throws IOException {
        // arrange
        Path fileA = RESOURCES.resolve("infiniteRecursion").resolve("original").resolve("A.class");
        Path fileB =
                RESOURCES.resolve("infiniteRecursion").resolve("decompiled").resolve("A.class");
        DiffImpl diff = getDiff(fileA, fileB);

        // assert
        List<Action> rootOperations = diff.getRootOperations();
        assertThat(rootOperations).size().isEqualTo(2);

        assertThat(rootOperations.get(0)).isInstanceOf(Update.class);
        assertThat(rootOperations.get(0).getNode().getType().name).isEqualTo("METHOD_INVOKED_RETURN_TYPE");

        assertThat(rootOperations.get(1)).isInstanceOf(Delete.class);
        assertThat(rootOperations.get(1).getNode().getType().name).isEqualTo(Opcode.CHECKCAST.name());
    }

    @Nested
    class EQ {
        private static final Path EQ_OPENJDK = EQ.resolve("OpenJDK");
        private static final Path EQ_SAMECOMP = EQ.resolve("SameComp");
        private static final Path EQ_SAMEMJCOMPVER = EQ.resolve("SameMjCompVer");

        @Nested
        class ClassesWithSomeVersionOfOpenJDK {
            @Test
            void gumtreeShouldShowNoDifference() throws IOException {
                // arrange
                Path oldClass = EQ_OPENJDK.resolve("1").resolve("8.0.372").resolve("AbstractTestCase.class");
                Path newClass = EQ_OPENJDK.resolve("1").resolve("9.0.1").resolve("AbstractTestCase.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(0);
            }
        }

        @Nested
        class SameComp {
            @Test
            void gumtreeShowsDifferenceBetween_tryCatchFinally_and_tryWithResource() throws IOException {
                // arrange
                Path oldClass =
                        EQ_SAMECOMP.resolve("8909").resolve("openjdk-10.0.2").resolve("HmacUtils.class");
                Path newClass =
                        EQ_SAMECOMP.resolve("8909").resolve("openjdk-11.0.12").resolve("HmacUtils.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(21);
            }

            @Test
            void gumtreeShowsDifferenceBetween_stringConcatenationJEP280() throws IOException {
                // arrange
                Path oldClass =
                        EQ_SAMECOMP.resolve("7896").resolve("openjdk-9.0.1").resolve("ArchiveStreamFactory.class");
                Path newClass =
                        EQ_SAMECOMP.resolve("7896").resolve("openjdk-10.0.2").resolve("ArchiveStreamFactory.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(21);
            }

            @Test
            void shouldShowNoDifferenceInBytecode() throws IOException {
                // arrange
                Path oldClass = EQ_SAMECOMP
                        .resolve("176701")
                        .resolve("oraclejdk-11.0.19")
                        .resolve("JavaLanguageParser$AnnotationConstantRestContext.class");
                Path newClass = EQ_SAMECOMP
                        .resolve("176701")
                        .resolve("oraclejdk-17.0.7")
                        .resolve("JavaLanguageParser$AnnotationConstantRestContext.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(0);
            }
        }

        @Nested
        class DiffComp {
            @Disabled(
                    "This test is disabled because I have to check why gumtree gives so many changes even though the bytecode should be equivalent.")
            @Test
            void shouldShowNoDifferenceInBytecode_1() throws IOException {
                // arrange
                Path oldClass = EQ.resolve("DiffComp")
                        .resolve("82141")
                        .resolve("openjdk-17.0.1")
                        .resolve("TapeInputStream.class");
                Path newClass = EQ.resolve("DiffComp")
                        .resolve("82141")
                        .resolve("ecj-3.28.0_openjdk-11.0.19")
                        .resolve("TapeInputStream.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(1);
            }

            @Disabled("Unclear why there is a difference.")
            @Test
            void shouldShowNoDifferenceInBytecode_2() throws IOException {
                Path oldClass = EQ.resolve("DiffComp")
                        .resolve("82138")
                        .resolve("openjdk-17.0.1")
                        .resolve("DumpArchiveUtil.class");
                Path newClass = EQ.resolve("DiffComp")
                        .resolve("82138")
                        .resolve("ecj-3.28.0_openjdk-11.0.19")
                        .resolve("DumpArchiveUtil.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(1);
            }

            @Disabled("Diff should clearly mention that indy is replaced with an earlier implementation.")
            @Test
            void diffShouldIndicateThatInvokeDyanamicIsReplaced() throws IOException {
                Path oldClass = EQ.resolve("DiffComp")
                        .resolve("71848")
                        .resolve("openjdk-15.0.1")
                        .resolve("AttributeLayout.class");
                Path newClass = EQ.resolve("DiffComp")
                        .resolve("71848")
                        .resolve("ecj-3.24.0_openjdk-11.0.19")
                        .resolve("AttributeLayout.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(1);
            }

            @Test
            void shouldShowNoDifferenceInBytecode_3() throws IOException {
                Path oldClass = EQ.resolve("DiffComp")
                        .resolve("73882")
                        .resolve("openjdk-15.0.1")
                        .resolve("PropertyListParser$JJCalls.class");
                Path newClass = EQ.resolve("DiffComp")
                        .resolve("73882")
                        .resolve("ecj-3.24.0_openjdk-11.0.19")
                        .resolve("PropertyListParser$JJCalls.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(0);
            }

            @Test
            void shouldShowDifferenceInLocalVariableTable() throws IOException {
                Path oldClass = EQ.resolve("DiffComp")
                        .resolve("274831")
                        .resolve("openjdk-20.0.1")
                        .resolve("AnnClass.class");
                Path newClass = EQ.resolve("DiffComp")
                        .resolve("274831")
                        .resolve("openjdk-nodebug-20.0.1")
                        .resolve("AnnClass.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(0);
            }
        }

        @Nested
        class SameMjCompVer {
            @Test
            void shouldOnlyShowBytecodeMajorVersionUpdate() throws IOException {
                Path oldClass = EQ.resolve("SameMjCompVer")
                        .resolve("161556")
                        .resolve("ecj-3.15.1_openjdk-11.0.19")
                        .resolve("LZMAUtils$CachedAvailability.class");
                Path newClass = EQ.resolve("SameMjCompVer")
                        .resolve("161556")
                        .resolve("ecj-3.24.0_openjdk-11.0.19")
                        .resolve("LZMAUtils$CachedAvailability.class");
                DiffImpl diff = getDiff(oldClass, newClass);

                // assert
                List<Action> rootOperations = diff.getRootOperations();
                assertThat(rootOperations).size().isEqualTo(1);
            }
        }
    }

    private static DiffImpl getDiff(Path oldClass, Path newClass) throws IOException {
        byte[] bytes1 = Files.readAllBytes(oldClass);
        byte[] bytes2 = Files.readAllBytes(newClass);
        final Builder scanner = new Builder();
        DiffImpl diff = new DiffImpl(scanner.getTreeContext(), scanner.getTree(bytes1), scanner.getTree(bytes2));
        diff.computeDiff();
        return diff;
    }
}
