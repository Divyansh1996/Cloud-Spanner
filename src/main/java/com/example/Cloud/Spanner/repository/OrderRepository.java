package com.example.Cloud.Spanner.repository;

import com.example.Cloud.Spanner.model.Order;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends SpannerRepository<Order, String> {
}
