package com.jj.gpstracker.domain.google

class GetGoogleDataUseCase(
    private val templateRepository: TemplateRepository,
) {
    suspend operator fun invoke() = templateRepository.getGoogleData()
}