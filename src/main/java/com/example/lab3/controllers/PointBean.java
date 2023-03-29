package com.example.lab3.controllers;

import com.example.lab3.dto.PointDto;
import com.example.lab3.services.HitService;
import com.example.lab3.services.ValidateService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named(value = "pointBean")
@ApplicationScoped
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PointBean implements Serializable {

    private static final long serialVersionUID = -6680734133634363295L;

    private HitService hitService;
    private float xValue;
    private float yValue;
    @Min(1)
    @Max(5)
    @Setter
    private int rValue = 1;


    public PointDto createPoint() {
        if(ValidateService.validateX(xValue) && ValidateService.validateY(yValue) && ValidateService.validateR(rValue)) {
            long startTime = System.nanoTime();
            String hit = hitService.checkHit(xValue, yValue, rValue) ? "hit" : "miss";
            String date = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date());
            return new PointDto(xValue, yValue, rValue, hit, date, startTime);
        }
        throw new InvalidParameterException("Wrong input data");
    }

    public void setXValue(float xValue) {
            this.xValue = (float) (Math.ceil(xValue * 10)/10);
    }

    public void setYValue(float yValue) {
        this.yValue = (float) (Math.ceil(yValue * 10)/10);
    }
}
