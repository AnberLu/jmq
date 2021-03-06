package com.ipd.jmq.server.broker.cluster;

/**
 * 集群事件
 */
public abstract class ClusterEvent {

    // 类型
    protected EventType type;

    public EventType getType() {
        return this.type;
    }

    public static enum EventType {
        /**
         * topic信息通知
         */
        TOPIC_UPDATE,
        /**
         * 重试服务变更
         */
        RETRY_CHANGE,
        /**
         * 所有topic全部更新
         */
        ALL_TOPIC_UPDATE,
        /**
         * 所有broker全部更新
         */
        ALL_BROKER_UPDATE,
        /**
         * 所有从消费全部更新
         */
        ALL_SLAVECONSUME_UPDATE
    }

}