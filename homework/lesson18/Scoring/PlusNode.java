package ru.sbt.school.lesson18.Scoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 06.10.2016.
 */
public class PlusNode extends FunctionalNode {

    @Override
    public double getResult(Map<String, Double> values) {
        List<Node> nodes = getChildNodes();
        double result = 0;
        for (Node node :
                nodes) {
            result += node.getResult(values);
        }
        return result;
    }
}
