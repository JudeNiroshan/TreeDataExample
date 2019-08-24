package com.holidu.interview.assignment.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.model.Tree;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class NycTreeDataHandlerTest {

    private NycTreeDataHandler classUnderTest = new NycTreeDataHandler();

    private ObjectMapper testMapper = new ObjectMapper();

    @Test
    public void findTreesInsideTheCircleTest() {

        // preparation
        Tree expectedTreeInsideTheCircle = new Tree("t_id_1", "Oak", 1.2, 1.2);

        Set<Tree> testTrees = new HashSet<>();
        testTrees.add(expectedTreeInsideTheCircle);
        testTrees.add(new Tree("t_id_2", "Pine", 3.2, 8.2));

        // test
        Map<String, Long> treesInsideTheCircle = classUnderTest.findTreesInsideTheCircle(testTrees, 1.0, 1.0, 2.0);

        // assertion
        assertThat(treesInsideTheCircle).hasSize(1);
        assertThat(treesInsideTheCircle).containsKeys("Oak");
        assertThat(treesInsideTheCircle).containsValue(1L);
    }

    @Test
    public void getAllTreesTest() {

        // preparation
        classUnderTest.objectMapper = testMapper;
        String json = "[\n" +
                "  {\n" +
                "    \"tree_id\": \"180683\",\n" +
                "    \"spc_common\": \"red maple\",\n" +
                "    \"x_sp\": \"1027431.148\",\n" +
                "    \"y_sp\": \"202756.7687\"\n" +
                "  }\n" +
                "]";
        ObjectNode[] sampleData = new ObjectNode[0];
        try {
            sampleData = testMapper.readValue(json, ObjectNode[].class);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        ObjectNode[] finalSampleData = sampleData;
        Supplier<ObjectNode[]> treeDataSupplier = () -> finalSampleData;

        // test
        Set<Tree> allTrees = classUnderTest.getAllTrees(treeDataSupplier);

        // assertions
        assertThat(allTrees).hasSize(1);
        for (Tree t: allTrees) {
            assertThat(t.getTree_id()).isEqualTo("180683");
            assertThat(t.getSpc_common()).isEqualTo("red maple");
            assertThat(t.getX_sp()).isEqualTo(1027431.148);
            assertThat(t.getY_sp()).isEqualTo(202756.7687);

        }
    }
}
