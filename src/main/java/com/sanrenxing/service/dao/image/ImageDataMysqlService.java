package com.sanrenxing.service.dao.image;

import com.sanrenxing.service.model.data.Image;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("imageMySQL")
public class ImageDataMysqlService implements ImageDao {

    private final JdbcTemplate jdbcTemplate;

    public ImageDataMysqlService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID uploadImage(byte[] data) {
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO images(id, data) VALUES (?, ?);";
        jdbcTemplate.update(sql,
                id.toString(),  // Convert UUID to string
                data);
        return id;
    }

    @Override
    public Optional<Image> fetchImage(UUID targetId) {
        final String sql = "SELECT * FROM images WHERE id = ?;";
        try {
            Image image = jdbcTemplate.queryForObject(sql,
                    new Object[]{targetId.toString()},  // Convert UUID to string for query
                    (resultSet, i) -> {
                        UUID id = UUID.fromString(resultSet.getString("id"));  // Convert string back to UUID
                        byte[] data = resultSet.getBytes("data");
                        return new Image(id, data);
                    }
            );
            return Optional.ofNullable(image);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteImage(UUID id) {
        final String sql = "DELETE FROM images WHERE id = ?;";
        return jdbcTemplate.update(sql, id.toString());  // Convert UUID to string
    }
}
