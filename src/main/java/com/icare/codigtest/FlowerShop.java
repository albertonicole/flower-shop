package com.icare.codigtest;

import java.util.*;

public class FlowerShop {

  public static final Map<String, List<Integer>> BUNDLES_SIZES = new HashMap<>(
      Map.of("R12", List.of(10, 5), "L09", List.of(9, 6, 3), "T58", List.of(9, 5, 3))
  );
  private static final Map<String, List<Double>> BUNDLES_PRICES = new HashMap<>(
      Map.of("R12", List.of(12.99, 6.99), "L09", List.of(24.95, 16.95, 9.95), "T58", List.of(16.99, 9.95, 5.95))
  );

  public static List<Map<Integer, Integer>> manageOrder(Map<String, Integer> order) {
    List<Map<Integer, Integer>> result = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : order.entrySet()) {
      String flower = entry.getKey();
      int orderQty = entry.getValue();
      Integer[] bundleSizes = BUNDLES_SIZES.get(flower).toArray(new Integer[0]);
      Double[] bundlePrices = BUNDLES_PRICES.get(flower).toArray(new Double[0]);
      List<List<Integer>> combos = findBestCombination(orderQty, bundleSizes, new ArrayList<>());
      Integer[] bestCombo;
      Map<Integer, Integer> orderBreakdown = new HashMap<>();
      if (combos.isEmpty()) {
        bestCombo = new Integer[0]; // no valid combination
      } else {
        bestCombo = combos.get(0).toArray(new Integer[0]);
        System.out.println(orderQty + " " + flower + " $" + calculateCost(combos.get(0), bundlePrices));
        for (int i = 0; i < bestCombo.length; i++) {
          if (bestCombo[i] != 0) {
            orderBreakdown.put(bundleSizes[i], bestCombo[i]);
            System.out.println("    " + bestCombo[i] + " x " + bundleSizes[i] + " $" + bundlePrices[i]);
          }
        }
      }
      result.add(orderBreakdown);
    }
    return result;
  }

  public static List<List<Integer>> findBestCombination(int orderQty, Integer[] bundleSizes, List<Integer> currentCombo) {
    List<List<Integer>> validCombos = new ArrayList<>();

    if (orderQty == 0) {
      // base case: we have fulfilled the order
      validCombos.add(new ArrayList<>(currentCombo));
      return validCombos;
    }

    // iterate over the bundle sizes
    for (int i = 0; i < bundleSizes.length; i++) {
      if (validCombos.size() > 0) {
        // we have already found a valid combination, so we can stop
        break;
      }
      int bundleSize = bundleSizes[i];
      int maxNumBundles = orderQty / bundleSize;

      // iterate over the possible numbers of bundles
      for (int numBundles = maxNumBundles; numBundles >= 0; numBundles--) {
        if (validCombos.size() > 0) {
          // we have already found a valid combination, so we can stop
          break;
        }
        int remainingQty = orderQty - numBundles * bundleSize;

        if (remainingQty == 0) {// we have fulfilled the order using the current bundle size
          currentCombo.add(numBundles);
          validCombos.add(new ArrayList<>(currentCombo));
          currentCombo.remove(currentCombo.size() - 1);
        } else if (i < bundleSizes.length - 1) {// if there is at least one smaller bundle size
          // try to fulfill the remaining quantity using smaller bundles
          Integer[] smallerBundleSizes = Arrays.copyOfRange(bundleSizes, i + 1, bundleSizes.length);

          // recursively find the best combination for the remaining quantity
          List<List<Integer>> smallerCombos = findBestCombination(remainingQty, smallerBundleSizes, currentCombo);
          if (!smallerCombos.isEmpty()) {
            // we found a valid combination using the smaller bundles
            currentCombo.add(numBundles);
            // add the smaller bundles to the current combo
            currentCombo.addAll(smallerCombos.get(0));
            // add the current combo to the list of valid combos
            validCombos.add(new ArrayList<>(currentCombo));
            currentCombo.subList(currentCombo.size() - smallerCombos.get(0).size(), currentCombo.size()).clear();
            currentCombo.remove(currentCombo.size() - 1);
          }
        }
      }
    }

    return validCombos;
  }

  public static double calculateCost(List<Integer> combo, Double[] bundlePrices) {
    double cost = 0;
    for (int i = 0; i < combo.size(); i++) {
      cost += combo.get(i) * bundlePrices[i];
    }
    return Math.round(cost*100.0)/100.0;
  }
}


























