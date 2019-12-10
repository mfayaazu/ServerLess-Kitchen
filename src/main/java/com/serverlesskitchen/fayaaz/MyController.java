package com.serverlesskitchen.fayaaz;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  private Service service;

  @GetMapping("/ping")
  public String ping() {

    return "pong";
  }

}