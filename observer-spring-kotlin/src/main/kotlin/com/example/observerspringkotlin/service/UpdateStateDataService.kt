package com.example.observerspringkotlin.service

import com.example.observerspringkotlin.infrastructure.StateData
import com.example.observerspringkotlin.service.user.UserRepository
import com.example.observerspringkotlin.service.watch.WatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateStateDataService(
    private val watchRepository: WatchRepository,
    private val userRepository: UserRepository,
) {

    @Transactional
    fun updateData(stateData: StateData) {

        val user = userRepository.findByIdOrNull(stateData.userId) ?: throw Exception()

        user.updateState(stateData)

        val watch = watchRepository.findByIdOrNull(stateData.watchId) ?: throw Exception()

        watch.updateState(stateData)
    }
}