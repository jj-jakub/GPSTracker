package konsist

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentOf
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class KonsistTests {

    @Test
    fun `Validate architecture`() {
        Konsist.scopeFromProject().assertArchitecture {
            val data = Layer("data", "com.jj.templateproject.data..")
            val presentation = Layer("presentation", "com.jj.templateproject.presentation..")
            val domain = Layer("domain", "com.jj.templateproject.domain..")

            domain.dependsOnNothing()
            data.dependsOn(domain)
            presentation.dependsOn(domain, data)
        }
    }

    @Test
    fun `Validate UseCases are in correct package`() {
        Konsist.scopeFromProject().classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.resideInPackage("com.jj.templateproject.domain..")
            }
    }

    @Test
    fun `Validate ViewModel has single constructor with private parameters`() =
        Konsist.scopeFromProject()
            .classes()
            .withParentOf(ViewModel::class)
            .assertTrue { declaration ->
                val hasSingleConstructor = declaration
                    .constructors
                    .let {
                        it.size == 1
                    }
                val constructorParametersHavePrivateModifier = declaration
                    .constructors
                    .first()
                    .parameters.all {
                        it.modifiers.isEmpty() ||
                                (it.hasValModifier && it.hasModifier(KoModifier.PRIVATE))
                    }
                hasSingleConstructor && constructorParametersHavePrivateModifier
            }
}