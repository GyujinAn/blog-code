package com.example.aop.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UpdateAccountDto {
    public Long accountId;
    public Long organizationId;
    public String name;
    public String address;
}
