package io.gson.adapters;

import com.google.gson.*;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.time.Month;

/**
 * @see Month
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
@Singleton
public class MonthAdapter implements JsonSerializer<Month>, JsonDeserializer<Month> {

    private static final Month[] MONTHS = Month.values();

    @Override
    public Month deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json instanceof JsonPrimitive) {
                return Month.of(json.getAsInt());
            } else {
                final String monthAsJson = json.getAsString();
                for (Month month : MONTHS) {
                    if (month.name().equalsIgnoreCase(monthAsJson)) {
                        return month;
                    }
                }

                throw new JsonParseException("Month can not be parsed from: " + monthAsJson);
            }
        } catch (JsonParseException e) {
            throw e;
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Month src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }
}
