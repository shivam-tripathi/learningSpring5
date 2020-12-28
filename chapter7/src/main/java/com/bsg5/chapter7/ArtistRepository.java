package com.bsg5.chapter7;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistRepository {
  private final DataSource dataSource;

  public ArtistRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Artist findArtistById(int id) throws SQLException {
    // System.out.println("id = " + id);
    try (Connection conn = dataSource.getConnection()) {
      return getArtistById(conn, id);
    }
  }

  public Artist getArtistById(Connection conn, int id) {
    String sql = "SELECT * FROM artists where id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Artist(id, rs.getString("name"));
        } else {
          throw new ArtistNotFoundException(id + " not found in artist database");
        }
      }
    } catch (SQLException e) {
      throw new ArtistNotFoundException(e);
    }
  }

  public Artist saveArtist(String name) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      return saveArtist(conn, name);
    }
  }

  public Artist saveArtist(Connection conn, String name) throws SQLException {
    String sql = "INSERT INTO artists (NAME) VALUES (?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, name);
      int x = ps.executeUpdate();
      try (ResultSet rs = ps.getGeneratedKeys()) {
        rs.next();
        return new Artist(rs.getInt(1), name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return findArtistByName(conn, name);
    }
  }

  public Artist findArtistByName(String name) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      return findArtistByName(conn, name);
    }
  }

  public Artist findArtistByName(Connection conn, String name) throws SQLException {
    String sql = "SELECT * FROM artists WHERE LOWER(name) = LOWER(?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, name);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Artist(rs.getInt("id"), rs.getString("name"));
        } else {
          throw new ArtistNotFoundException(name + " not found in artists database");
        }
      }
    }
  }

  public List<Artist> findAllArtistsByName(String name) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      return findAllArtistsByName(conn, name);
    }
  }

  public List<Artist> findAllArtistsByName(Connection conn, String name) throws SQLException {
    String sql = "SELECT * FROM artists WHERE LOWER(name) like LOWER(?) ORDER BY name";
    List<Artist> artists = new ArrayList<>();
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, name + "%");
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          artists.add(new Artist(rs.getInt("id"), rs.getString("name")));
        }
      }
    }
    return artists;
  }
}
