package ru.sbt.school.lesson18.Scoring;

import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 06.10.2016.
 */
interface Node {

    double getResult(Map<String, Double> values);
    void addNode(Node node);
    void removeNode(Node node);
    List<Node> getChildNodes();
}
