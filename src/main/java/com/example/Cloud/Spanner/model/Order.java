package com.example.Cloud.Spanner.model;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.Interleaved;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "orders")
public class Order {

    @PrimaryKey(keyOrder = 1)
    @Column(name = "order_id")
    private String order_id;

    private String description;

    @Column(name = "creation_timestamp")
    private LocalDateTime creation_timestamp;

    @Interleaved
    private List<OrderItem> orderItems;

}
