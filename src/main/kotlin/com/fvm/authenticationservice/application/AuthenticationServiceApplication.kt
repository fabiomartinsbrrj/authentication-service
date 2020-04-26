package com.fvm.authenticationservice.application

import com.fvm.authenticationservice.application.config.dimodules.ConfigModule
import com.fvm.authenticationservice.application.config.dimodules.ControllersModule
import com.fvm.authenticationservice.application.config.dimodules.RoutesModule
import com.fvm.authenticationservice.application.config.dimodules.ServicesModule
import com.fvm.authenticationservice.application.errors.ErrorHandler
import com.fvm.authenticationservice.application.errors.HttpErrorResponse
import com.fvm.authenticationservice.application.web.routes.AuthenticationServiceRoutes
import io.javalin.Javalin
import io.javalin.http.HttpResponseException
import io.javalin.plugin.openapi.OpenApiOptions
import io.javalin.plugin.openapi.OpenApiPlugin
import io.javalin.plugin.openapi.ui.SwaggerOptions
import io.swagger.v3.oas.models.info.Info
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.slf4j.LoggerFactory
import java.lang.Exception

object AuthenticationServiceApplication : KoinComponent {
    private val logger = LoggerFactory.getLogger(AuthenticationServiceApplication::class.java)
    private val authenticationServiceRoutes: AuthenticationServiceRoutes by inject()
    private val errorHandler: ErrorHandler by inject()
    val SERVER_PORT = (System.getenv("SERVER_PORT") ?: "7000").toInt()

    fun init(): Javalin {
        setupDIModules()

        val app:Javalin = Javalin.create{
            it.defaultContentType = "application/json"
            it.registerPlugin(getConfiguredOpenApiPlugin())
        }

        app.routes {
            authenticationServiceRoutes.register()
        }

        app.exception(Exception::class.java, errorHandler::handleException)
        app.exception(RuntimeException::class.java, errorHandler::handleRuntimeException)
        app.exception(HttpResponseException::class.java, errorHandler::handleRuntimeException)

        app.start(SERVER_PORT)

        logger.info("Check out Swagger UI docs at http://localhost:$SERVER_PORT/swagger-ui")

        return app
    }

    private fun getConfiguredOpenApiPlugin() = OpenApiPlugin(
        OpenApiOptions(
            Info().apply {
                version("1.0")
                description("Password API")
            }
        ).apply {
            path("/swagger-docs") // endpoint for OpenAPI json
            swagger(SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
            defaultDocumentation { doc ->
                doc.json("500", HttpErrorResponse::class.java)
            }
        }
    )

    private fun setupDIModules() {
        StandAloneContext.startKoin(
            list = listOf(
                RoutesModule.modules(),
                ControllersModule.modules(),
                ConfigModule.modules(),
                ServicesModule.modules()
            ),
            useEnvironmentProperties = true
        )
    }


}

