package ru.sbt.school.lesson18.Scoring;

import java.util.Map;

/**
 * Created by Anatoly on 07.10.2016.
 */
public class EqualValueNode extends FunctionalNode {
    double value;

    public EqualValueNode(double value) {
        this.value = value;
    }

    @Override
    public double getResult(Map<String, Double> values) {
        for (Node node :
                getChildNodes()) {
            if (isNodeEqualsValue(values, node)) {
                return 0;
            }
        }
        return 1;
    }

    private boolean isNodeEqualsValue(Map<String, Double> values, Node node) {
        return node.getResult(values) != value;
    }
}
