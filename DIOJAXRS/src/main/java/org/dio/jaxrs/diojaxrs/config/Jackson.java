package org.dio.jaxrs.diojaxrs.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import org.dio.jaxrs.diojaxrs.enums.Race;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Jackson {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.registerModule(raceModuleMapper());
        return objectMapper;
    }

    public SimpleModule raceModuleMapper() {
        SimpleModule dateModule = new SimpleModule("JSONRaceModule", PackageVersion.VERSION);
        dateModule.addSerializer(Race.class, new RaceSerialize());
        dateModule.addDeserializer(Race.class, new RaceDeserialize());
        return dateModule;
    }

    class RaceSerialize extends StdSerializer<Race> {
        public RaceSerialize() {
            super(Race.class);
        }

        @Override
        public void serialize(Race race, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(race.getValue());
        }
    }

    class RaceDeserialize extends StdDeserializer<Race> {
        public RaceDeserialize() {
            super(Race.class);
        }

        @Override
        public Race deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Race.of(p.getText());
        }
    }
}
