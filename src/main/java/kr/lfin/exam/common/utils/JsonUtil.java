package kr.lfin.exam.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

public class JsonUtil {

    /**
     * json string 에서 특정 태그 값을 찾아서 리턴
     * @param json
     * @param name
     * @return
     */
    public static String getTagValue(String json, String name){
        if (Strings.isNullOrEmpty(json) || Strings.isNullOrEmpty(name)) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            return "";
        }
        JsonNode node = rootNode.get(name);
        return node == null ? "" : node.asText();
    }
}
