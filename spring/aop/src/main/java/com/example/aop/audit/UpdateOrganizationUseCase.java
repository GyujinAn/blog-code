package com.example.aop.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UpdateOrganizationUseCase {

    private final OrganizationRepository organizationRepository;

    @RestoreAudit
    @Transactional
    public Organization execute(UpdateOrganizationDto dto) throws Exception {
        Organization organization;
        Optional<Organization> optionalAccount = organizationRepository.findById(dto.organizationId);
        if (optionalAccount.isPresent()) {
            organization = optionalAccount.get();
        } else  {
            throw new Exception();
        }

        return organization.update(dto);
    }
}
