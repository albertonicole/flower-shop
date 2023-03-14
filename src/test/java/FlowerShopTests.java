import static com.icare.codigtest.FlowerShop.BUNDLES_SIZES;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icare.codigtest.FlowerShop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Array;
import java.util.*;

public class FlowerShopTests {

  @DisplayName("Test R12")
  @ParameterizedTest(name = "Test {index}: orderQty={1}, flower={2}")
  @CsvSource({
      "1, 0, R12",
      "2, 5, R12",
      "3, 15, R12",
  })
  public void testR12ManageOrder(int index, int orderQty, String flower) {
    final Map<Integer, Integer[]> expectedBundlesSizes = new HashMap<>(
        Map.of(1, new Integer[]{}, 2, new Integer[]{5}, 3, new Integer[]{5, 10})
    );
    final Map<Integer, Integer[]> expectedBundlesQtys = new HashMap<>(
        Map.of(1, new Integer[]{}, 2, new Integer[]{1}, 3, new Integer[]{1, 1})
    );
    Integer[] expectedBundlesSize = expectedBundlesSizes.get(index);
    Integer[] expectedBundlesQty = expectedBundlesQtys.get(index);

    Map<Integer, Integer> result = FlowerShop.manageOrder(Map.of(flower, orderQty)).get(0);
    Integer[] bundlesSizes = result.keySet().toArray(new Integer[0]);
    Integer[] bundlesQtys = result.values().toArray(new Integer[0]);

    assertEquals(expectedBundlesQty.length, bundlesQtys.length);
    for (int i = 0; i < result.size(); i++) {
      assertEquals(expectedBundlesQty[i], bundlesQtys[i], "i: " + i);
      assertEquals(expectedBundlesSize[i], bundlesSizes[i]);
    }
  }

  @DisplayName("Test L09")
  @ParameterizedTest(name = "Test {index}: orderQty={1}, flower={2}")
  @CsvSource({
      "1, 0, L09",
      "2, 3, L09",
      "3, 6, L09",
      "4, 9, L09",
      "5, 12, L09",
      "6, 24, L09",
  })
  public void testL09ManageOrder(int index, int orderQty, String flower) {
    final Map<Integer, Integer[]> expectedBundlesSizes = new HashMap<>(
        Map.of(1, new Integer[]{}, 2, new Integer[]{3}, 3, new Integer[]{6}, 4, new Integer[]{9}, 5, new Integer[]{3, 9}, 6, new Integer[]{6, 9})
    );
    final Map<Integer, Integer[]> expectedBundlesQtys = new HashMap<>(
        Map.of(1, new Integer[]{}, 2, new Integer[]{1}, 3, new Integer[]{1}, 4, new Integer[]{1}, 5, new Integer[]{1, 1}, 6, new Integer[]{1, 2})
    );
    Integer[] expectedBundlesSize = expectedBundlesSizes.get(index);
    Integer[] expectedBundlesQty = expectedBundlesQtys.get(index);

    Map<Integer, Integer> result = FlowerShop.manageOrder(Map.of(flower, orderQty)).get(0);
    Integer[] bundlesSizes = result.keySet().toArray(new Integer[0]);
    Integer[] bundlesQtys = result.values().toArray(new Integer[0]);

    assertEquals(expectedBundlesQty.length, bundlesQtys.length);
    for (int i = 0; i < result.size(); i++) {
      assertEquals(expectedBundlesQty[i], bundlesQtys[i], "i: " + i);
      assertEquals(expectedBundlesSize[i], bundlesSizes[i]);
    }
  }

  @DisplayName("Test T58")
  @ParameterizedTest(name = "Test {index}: orderQty={1}, flower={2}")
  @CsvSource({
      "1, 0, T58",
      "2, 3, T58",
      "3, 5, T58",
      "4, 9, T58",
      "5, 12, T58",
      "6, 18, T58",
      "7, 26, T58",
      "8, 34, T58",
  })
  public void testT58ManageOrder(int index, int orderQty, String flower) {
    final Map<Integer, Integer[]> expectedBundlesSizes = new HashMap<>(
        Map.of(1, new Integer[]{}, 2, new Integer[]{3}, 3, new Integer[]{5}, 4, new Integer[]{9}, 5, new Integer[]{3, 9}, 6, new Integer[]{9}, 7, new Integer[]{3, 5, 9}, 8, new Integer[]{3, 5, 9})
    );
    final Map<Integer, Integer[]> expectedBundlesQtys = new HashMap<>(
        Map.of(1, new Integer[]{}, 2, new Integer[]{1}, 3, new Integer[]{1}, 4, new Integer[]{1}, 5, new Integer[]{1, 1}, 6, new Integer[]{2}, 7, new Integer[]{1, 1, 2}, 8, new Integer[]{2, 2, 2})
    );
    Integer[] expectedBundlesSize = expectedBundlesSizes.get(index);
    Integer[] expectedBundlesQty = expectedBundlesQtys.get(index);

    Map<Integer, Integer> result = FlowerShop.manageOrder(Map.of(flower, orderQty)).get(0);
    Integer[] bundlesSizes = result.keySet().toArray(new Integer[0]);
    Integer[] bundlesQtys = result.values().toArray(new Integer[0]);

    assertEquals(expectedBundlesQty.length, bundlesQtys.length);
    for (int i = 0; i < result.size(); i++) {
      assertEquals(expectedBundlesQty[i], bundlesQtys[i], "i: " + i);
      assertEquals(expectedBundlesSize[i], bundlesSizes[i]);
    }
  }

  @DisplayName("Test mixed orders")
  @ParameterizedTest(name = "Test {index}: orderQty={1}, flower={2}")
  @CsvSource({
      "1, 10, R12",
      "2, 15, L09",
      "3, 13, T58"
  })
  public void test1ManageOrder(int index, int orderQty, String flower) {
    final Map<Integer, Integer[]> expectedBundlesSizes = new HashMap<>(
        Map.of(1, new Integer[]{10}, 2, new Integer[]{6, 9}, 3, new Integer[]{3, 5})
    );
    final Map<Integer, Integer[]> expectedBundlesQtys = new HashMap<>(
        Map.of(1, new Integer[]{1}, 2, new Integer[]{1, 1}, 3, new Integer[]{1, 2})
    );
    Integer[] expectedBundlesSize = expectedBundlesSizes.get(index);
    Integer[] expectedBundlesQty = expectedBundlesQtys.get(index);

    Map<Integer, Integer> result = FlowerShop.manageOrder(Map.of(flower, orderQty)).get(0);
    Integer[] bundlesSizes = result.keySet().toArray(new Integer[0]);
    Integer[] bundlesQtys = result.values().toArray(new Integer[0]);

    assertEquals(expectedBundlesQty.length, bundlesQtys.length);
    for (int i = 0; i < result.size(); i++) {
      assertEquals(expectedBundlesQty[i], bundlesQtys[i], "i: " + i);
      assertEquals(expectedBundlesSize[i], bundlesSizes[i]);
    }
  }
}
