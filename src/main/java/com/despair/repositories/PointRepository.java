package com.despair.repositories;

import com.despair.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, OffsetDateTime> {
    List<Point> findAllByUsernameOrderByTime(String USERNAME);
}
