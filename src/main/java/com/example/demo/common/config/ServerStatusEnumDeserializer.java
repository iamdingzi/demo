package com.example.demo.common.config;

import com.example.demo.common.constant.ServerStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Administrator on 2019/5/20/020.
 */
public class ServerStatusEnumDeserializer extends JsonDeserializer<ServerStatus> {

    @Override
    public ServerStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String valueAsString = jsonParser.getValueAsString();
        for (ServerStatus typeEnum : ServerStatus.values()) {
            if (Objects.equals(valueAsString, typeEnum.name())) {
                return typeEnum;
            }
        }
        return null;
    }

}
