package ru.sbt.school.lesson18.Scoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 07.10.2016.
 */
class EqualsNode extends FunctionalNode {

    @Override
    public double getResult(Map<String, Double> values) {
        return isNodesEqual(values) ? 1 : 0;
    }

    private boolean isNodesEqual(Map<String, Double> values) {
        return getChildNodes().get(0).getResult(values) == getChildNodes().get(1).getResult(values);
    }
}
