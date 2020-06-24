package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.util.Parser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ParsingController {

    private final Parser parser;

    @PostMapping(value = "/parser")
    public void parseFile(@RequestBody String path) {
        parser.parseData(path);
    }
}
