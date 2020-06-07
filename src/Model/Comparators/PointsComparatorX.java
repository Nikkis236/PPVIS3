package Model.Comparators;

import java.awt.*;
import java.util.Comparator;

public class PointsComparatorX implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        return p1.getX() >= p2.getX() ? 1 : 0;
    }


}
