package com.example.springbeginner.services;

import com.example.springbeginner.models.Style;

import java.util.List;

public interface StyleService {
    List<Style> get();
    Style get(int id);
}
