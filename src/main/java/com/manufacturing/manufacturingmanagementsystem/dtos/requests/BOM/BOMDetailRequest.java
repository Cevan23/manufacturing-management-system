package com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Material.MaterialRequest;

import javax.persistence.criteria.CriteriaBuilder;

public class BOMDetailRequest {

    private MaterialRequest material;

    private Integer quantity;

    private Float totalUnitPrice;
}
