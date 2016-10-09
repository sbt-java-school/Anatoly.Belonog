package ru.sbt.school.lesson18.Scoring;

import java.util.Map;

/**
 * Created by Anatoly on 07.10.2016.
 */
public class AndNode extends FunctionalNode {
    @Override
    public double getResult(Map<String, Double> values) {
        for (Node node :
                getChildNodes()) {
            if (node.getResult(values) == 0) {
                return 0;
            }
        }
        return 1;
    }
}
