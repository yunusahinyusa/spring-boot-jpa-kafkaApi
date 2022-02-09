package com.ysahin.spring.jpa.kafkaApi.repository;

import com.ysahin.spring.jpa.kafkaApi.entity.ApiS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApisRepository extends JpaRepository<ApiS,Long> {
}
