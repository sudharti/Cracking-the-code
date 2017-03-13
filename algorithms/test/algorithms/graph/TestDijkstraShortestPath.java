package algorithms.graph;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algorithms.util.InputUtil;

public class TestDijkstraShortestPath {
  private static final String basePath = "input_files/graph/shortest_path/";
  private static String[] testCases = new String[] { "test_case_1" };
  private static List<String[]> inputList = new ArrayList<String[]>();
  private DijkstraShortestPath dijkstra = null;

  @BeforeClass
  public static void setup() {
    for (String testCase : testCases) {
      String inputFile = basePath + testCase;
      inputList.add(InputUtil.readContents(inputFile));
    }
  }

  @AfterClass
  public static void teardown() {
    testCases = null;
    inputList = null;
  }

  @Before
  public void setupTest() {
    dijkstra = new DijkstraShortestPath();
  }

  @After
  public void tearDownTest() {
    dijkstra = null;
  }

  @Test
  public void testDijkstraShortestPathTestCase1() {
    String[] input = inputList.get(0);
    dijkstra.constructGraph(input);
    assertShortestPath(input);
  }

  private void assertShortestPath(String[] input) {
    for (String line : input) {
      String[] values = line.split(" ");
      switch (values[0]) {
      case "compute":
        dijkstra.computeShortestPath(values[1]);
        break;
      case "shortestPath":
        assertThat(dijkstra.getShortestPathTo(values[1]), contains(values[2].split(",")));
        break;
      case "shortestDistance":
        assertThat(dijkstra.getShortestDistanceTo(values[1]), is(Integer.parseInt(values[2])));
        break;
      }
    }
  }
}
