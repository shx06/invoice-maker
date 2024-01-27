package com.wayyesy.invoicemaker.model;

public class TopicsModel {
    private String topicName;

    public TopicsModel() {
    }

    public TopicsModel(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
