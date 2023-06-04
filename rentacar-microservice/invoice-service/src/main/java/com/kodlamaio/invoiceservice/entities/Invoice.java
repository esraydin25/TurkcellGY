package com.kodlamaio.invoiceservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    private String id;
    private String plate;
    private String cardHolder;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;

    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd HH:mm:ss.SSSZZ")
    private LocalDateTime rentedAt;
}
