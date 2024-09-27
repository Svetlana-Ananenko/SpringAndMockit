  package com.homework.Spring_Mockito.exception;

  import org.springframework.http.HttpStatus;
  import org.springframework.web.bind.annotation.ResponseStatus;

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public class EmployeeNotFoundException extends RuntimeException {
      public EmployeeNotFoundException() {
          super("Сотрудник не найден");
      }

      public EmployeeNotFoundException(String message) {
          super(message);
      }
  }