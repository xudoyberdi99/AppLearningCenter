package com.company.exp;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourseNotFoundException extends RuntimeException {

    private final String resourseName;
    private final String resourseField;
    private final Object object;

}
