package com.example.crud.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
  public static ResponseEntity<Object> responseBuilder(
      String message,Integer code, HttpStatus httpStatus, Object responseObject
  ){
    Map<String, Object> response = new HashMap<>();
    response.put("data", responseObject);
    response.put("message", message);
    response.put("status", httpStatus);
    response.put("code", code);
    return new ResponseEntity<>(response, httpStatus);
  }

  public static ResponseEntity<Object> responseError(
      String message, HttpStatus httpStatus, Integer code
  ){
      Map<String, Object> response = new HashMap<>();
      response.put("message", message);
      response.put("code", code);
      response.put("responStatus", httpStatus);
      return new ResponseEntity<>(response, httpStatus);
  }
}
