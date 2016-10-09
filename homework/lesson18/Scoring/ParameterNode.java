package ru.sbt.school.lesson18.Scoring;

import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 06.10.2016.
 */
class ParameterNode implements Node{
    private final String keyName;

    ParameterNode(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public double getResult(Map<String, Double> values) {
        return values.get(keyName);
    }

    @Override
    public void addNode(Node node) {

    }

    @Override
    public void removeNode(Node node) {

    }

    @Override
    public List<Node> getChildNodes() {
        return null;
    }
}
