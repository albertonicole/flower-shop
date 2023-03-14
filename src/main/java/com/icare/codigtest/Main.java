package com.icare.codigtest;

import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    String flower1 = "R12"; // the flower type
    int orderQty1 = 10; // the order quantity
    String flower2 = "L09"; // the flower type
    int orderQty2 = 15; // the order quantity
    String flower3 = "T58"; // the flower type
    int orderQty3 = 13; // the order quantity

    FlowerShop.manageOrder(Map.of(flower1, orderQty1, flower2, orderQty2, flower3, orderQty3));
  }
}
