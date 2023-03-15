package com.example.oauth2resourceserverspringkotlin

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import java.io.Serializable

class CustomPermissionEvaluator : PermissionEvaluator {
    override fun hasPermission(authentication: Authentication?, targetDomainObject: Any?, permission: Any?): Boolean {
        if (authentication == null) {
            return false;
        }

        if (authentication.authorities.first().authority == "all:all") {
            return true
        }

        return authentication.authorities.any {
            targetDomainObject as String
            permission as String
            it.authority == "${targetDomainObject.lowercase()}:${permission.lowercase()}"
        }
    }

    override fun hasPermission(authentication: Authentication?, targetId: Serializable?, targetType: String?, permission: Any?): Boolean {
        throw Exception()
    }
}