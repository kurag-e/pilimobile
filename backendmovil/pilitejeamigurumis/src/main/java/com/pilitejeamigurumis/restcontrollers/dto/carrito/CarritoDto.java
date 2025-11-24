package com.pilitejeamigurumis.restcontrollers.dto.carrito;

import java.util.List;
import java.util.stream.Collectors;

import com.pilitejeamigurumis.entities.Carrito;

public class CarritoDto {

  private List<CarritoItemDto> items;

  public static CarritoDto fromEntity(Carrito c) {
    CarritoDto dto = new CarritoDto();
    dto.items = c.getItems().stream()
        .map(CarritoItemDto::fromEntity)
        .collect(Collectors.toList());
    return dto;
  }

  public CarritoDto() {}
  public CarritoDto(List<CarritoItemDto> items) { this.items = items; }

  public List<CarritoItemDto> getItems() { return items; }
  public void setItems(List<CarritoItemDto> items) { this.items = items; }
}
