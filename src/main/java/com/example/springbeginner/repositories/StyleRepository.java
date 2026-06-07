package com.example.springbeginner.repositories;

import com.example.springbeginner.models.Style;

import java.util.List;

public interface StyleRepository {

    List<Style> get();
    Style get(int id);
}
