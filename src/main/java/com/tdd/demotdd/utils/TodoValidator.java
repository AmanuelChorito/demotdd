package com.tdd.demotdd.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TodoValidator {
    public  boolean validateTitlelength(String length){
      return   length.length()<=15;
    }
}
