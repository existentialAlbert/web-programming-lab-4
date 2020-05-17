package com.despair.jmx;

import com.despair.model.Point;
import com.despair.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DatabaseManager implements DatabaseManagerMBean {
    @Autowired
    private PointRepository pointRepository;

    @Override
    public String getUserPoints(String username) {
        try {
            List<Point> points = pointRepository.findAllByUsernameOrderByTime(username);
            System.out.println(points);
            int count = points.size();
            int countWrongOnes = 0;
            int inRow = 0;
            for (Point point : points) {
                if (point.isHit().equals("Промах")) {
                    countWrongOnes++;
                    inRow++;
                } else inRow = 0;
                if (inRow > 1)
                    continue;
            }
            return "Всего точек: " + count + "\n" +
                    "Промахов: " + countWrongOnes;
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
