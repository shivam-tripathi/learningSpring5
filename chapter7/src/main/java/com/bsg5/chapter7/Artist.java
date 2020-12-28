package com.bsg5.chapter7;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Artist implements Comparable<Artist> {
  private int id;
  private String name;
  public Artist() {
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof Artist) {
      Artist artist = (Artist) obj;
      return Objects.equals(getName(), artist.getName()) && getId() == artist.getId();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }

  @Override
  public int compareTo(@NonNull Artist o) {
    return getName().toLowerCase().compareTo(o.getName().toLowerCase());
  }
}
