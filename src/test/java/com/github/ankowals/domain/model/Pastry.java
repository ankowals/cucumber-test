package com.github.ankowals.domain.model;

public record Pastry(String name, String description, String size, Double price, Status status) {

  public enum Status {
    AVAILABLE("available");

    private final String name;

    Status(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  public enum Size {
    L("L"),
    M("M"),
    S("S");

    private final String name;

    Size(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }
}
