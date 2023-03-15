package com.example.oauth2resourceserverspringkotlin

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController {

    @PreAuthorize("hasPermission('resource', 'read')")
    @GetMapping("/resource/{id}")
    fun resource(
        @PathVariable id: String,
    ): String {
        return id
    }
}