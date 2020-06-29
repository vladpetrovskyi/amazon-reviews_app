package com.vp.amazonreviewsapp.util.parser;

import java.util.List;

public interface Parser {
    List<List<?>> parseData(String path);
}
