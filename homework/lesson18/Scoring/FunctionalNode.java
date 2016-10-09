package ru.sbt.school.lesson18.Scoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 07.10.2016.
 */
abstract class FunctionalNode implements Node {
    private final List<Node> childNodes = new ArrayList<>();

    @Override
    public abstract double getResult(Map<String, Double> data);

    @Override
    public final void addNode(Node node) {
        childNodes.add(node);
    }

    @Override
    public final void removeNode(Node node) {
        childNodes.remove(node);
    }

    @Override
    public final List<Node> getChildNodes() {
        return childNodes;
    }
}
