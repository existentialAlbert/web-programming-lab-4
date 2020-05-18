package com.despair.jmx;

import com.despair.model.DatabaseManagerBean;
import com.despair.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Management implements ManagementMBean {
    @Autowired
    private DatabaseManagerBean databaseManager;

    @Override
    public String getUserPoints(String username) {
        try {
            List<Point> points = databaseManager.getPoints(username);
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
