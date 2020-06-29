package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.util.parser.Parser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ParsingController {

    private final Parser parser;

    @PostMapping(value = "/parser")
    public String parseFile(@RequestBody String path) {
        parser.parseData(path);
        return "Parsing completed";
    }
}
