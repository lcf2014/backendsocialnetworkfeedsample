package com.backendsocialnetworkapplication.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteEntity {
   private DoubleIdObjectEntity doubleIdObjectEntity;
   private String quoteMessage;
}
