package ru.sbt.school.lesson18.Scoring;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anatoly on 07.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Double> data = new HashMap<>();
        data.put("city", 54.0);
        data.put("salary", 10000.0);
        data.put("amountOfCredit", 10000.0);
        data.put("amountOfMonth", 1.0);

        Node andNode = new AndNode();

        EqualValueNode equalValueNode = new EqualValueNode(54);
        GreaterNode greaterNode = new GreaterNode();

        andNode.addNode(equalValueNode);
        andNode.addNode(greaterNode);

        equalValueNode.addNode(new ParameterNode("city"));
        greaterNode.addNode(new ParameterNode("salary"));

        DivisionNode divisionNode = new DivisionNode();
        greaterNode.addNode(divisionNode);

        divisionNode.addNode(new ParameterNode("amountOfCredit"));
        divisionNode.addNode(new ParameterNode("amountOfMonth"));

        double result = andNode.getResult(data);
        System.out.println(result);
    }
}
