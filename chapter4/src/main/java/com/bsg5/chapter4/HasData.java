package com.bsg5.chapter4;

import java.util.Objects;

abstract class HasData {
    String datum = "default";

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HasData)) return false;
        HasData hasData = (HasData) obj;
        return Objects.equals(getDatum(), hasData.getDatum());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDatum());
    }
}
