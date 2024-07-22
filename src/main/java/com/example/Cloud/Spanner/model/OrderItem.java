package com.example.Cloud.Spanner.model;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;

@Data
@Table(name = "order_items")
public class OrderItem {

    @PrimaryKey(keyOrder = 1)
    @Column(name = "order_id")
    private String order_id;

    @PrimaryKey(keyOrder = 2)
    @Column(name = "order_item_id")
    private String order_item_id;

    private String description;

    private Long quantity;
}
