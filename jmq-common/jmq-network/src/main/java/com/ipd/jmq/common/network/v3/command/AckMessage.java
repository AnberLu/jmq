package com.ipd.jmq.common.network.v3.command;

import com.ipd.jmq.common.message.MessageLocation;
import com.ipd.jmq.common.network.v3.session.ConsumerId;
import com.ipd.jmq.toolkit.lang.Preconditions;

import java.util.Arrays;

/**
 * 应答消息
 */
public class AckMessage extends JMQPayload {
    public static byte FROM_CLIENT=1;
    // 消费者ID
    private ConsumerId consumerId;
    // 消息位置
    private MessageLocation[] locations;
    // 确认来源，默认来源于客户端
    private byte source = FROM_CLIENT;

    @Override
    public int type() {
        return CmdTypes.ACK_MESSAGE;
    }

    public byte getSource() {
        return source;
    }

    public void setSource(byte source) {
        this.source = source;
    }

    public AckMessage consumerId(final ConsumerId consumerId) {
        setConsumerId(consumerId);
        return this;
    }

    public AckMessage locations(final MessageLocation[] locations) {
        setLocations(locations);
        return this;
    }

    public ConsumerId getConsumerId() {
        return this.consumerId;
    }

    public void setConsumerId(ConsumerId consumerId) {
        this.consumerId = consumerId;
    }

    public MessageLocation[] getLocations() {
        return this.locations;
    }

    public void setLocations(MessageLocation[] locations) {
        this.locations = locations;
    }

    public int predictionSize() {
        String topic = locations[0].getTopic();
        return Serializer.getPredictionSize(consumerId.getConsumerId()) +
                Serializer.getPredictionSize(topic) + 8 + locations.length * 9 + 1;
    }

    @Override
    public void validate() {
        super.validate();
        Preconditions.checkArgument(consumerId != null, "consumer ID can not be null");
        Preconditions.checkArgument(locations != null && locations.length > 0 , "locations can not be null");


    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AckMessage{");
        sb.append(", consumerId=").append(consumerId);
        sb.append(", locations=").append(Arrays.toString(locations));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AckMessage that = (AckMessage) o;

        if (consumerId != null ? !consumerId.equals(that.consumerId) : that.consumerId != null) {
            return false;
        }
        if (!Arrays.equals(locations, that.locations)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = consumerId != null ? consumerId.hashCode() : 0;
        result = 31 * result + (locations != null ? Arrays.hashCode(locations) : 0);
        return result;
    }
}