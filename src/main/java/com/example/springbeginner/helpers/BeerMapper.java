package com.example.springbeginner.helpers;

import com.example.springbeginner.models.Beer;
import com.example.springbeginner.models.BeerDto;
import com.example.springbeginner.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {
    private final StyleService styleService;

    @Autowired
    public BeerMapper(StyleService styleService){
        this.styleService = styleService;
    }

    public Beer fromDto(int id, BeerDto dto){
        Beer beer = fromDto(dto);
        beer.setId(id);
        return beer;
    }

    public Beer fromDto(BeerDto dto){
        Beer beer = new Beer();
        beer.setName((dto.getName()));
        beer.setAbv(dto.getAbv());
        beer.setStyle(styleService.get(dto.getStyleId()));
        return beer;
    }
}
