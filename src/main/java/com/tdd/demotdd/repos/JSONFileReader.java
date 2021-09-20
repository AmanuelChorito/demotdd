package com.tdd.demotdd.repos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.jdi.ArrayReference;
import com.tdd.demotdd.domain.TodoItem;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Repository

public class JSONFileReader {

    ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

      public  List<TodoItem> getJsonFile() throws IOException {

          return ( objectMapper.readValue(new File("Todo.json"), new TypeReference<List<TodoItem>>() {}));
        }

        public void writetoJson(TodoItem todoItem)throws IOException{
        objectMapper.writeValue(Paths.get("Todo.json").toFile(),todoItem);

        }

}
