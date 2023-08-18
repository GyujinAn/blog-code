package com.example.aop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UpdateAccountDto {
    public UUID accountId;
    public UUID organizationId;
    public String name;
    public String address;
}
