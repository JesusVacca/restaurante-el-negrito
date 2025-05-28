package com.prototype.restaurante_el_negro.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.restaurante_el_negro.dto.ProductBaseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProductBaseDto implements Converter<String, ProductBaseDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ProductBaseDto convert(String source) {
        try {
            return objectMapper.readValue(source, ProductBaseDto.class);
        }catch (Exception e){
            throw new RuntimeException("Error al convertir el producto", e);
        }
    }
}
