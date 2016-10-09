package ru.sbt.school.lesson18.Scoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 06.10.2016.
 */
class MinusNode extends FunctionalNode {

    @Override
    public double getResult(Map<String, Double> values) {
        List<Node> nodes = getChildNodes();
        return nodes.get(0).getResult(values) - nodes.get(1).getResult(values);
    }
}
