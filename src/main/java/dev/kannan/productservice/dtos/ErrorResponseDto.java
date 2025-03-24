package dev.kannan.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private String message;
    private String resolution;
}