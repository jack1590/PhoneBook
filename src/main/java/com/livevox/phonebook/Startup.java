package com.livevox.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class Startup {

  public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
  }

}
