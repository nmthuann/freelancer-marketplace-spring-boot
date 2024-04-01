package com.nmt.freelancermarketplacespringboot.core.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "My Freelancer Marketplace Server API",
                description = "This API provides endpoints for managing users and their data.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Thuan Nguyen",
                        email = "thuan.nguyen@example.com"
                ),
                license = @License(
                        name = "license name",
                        url = "https://www.thuan.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "termsOfService"
        ),
        servers = {
                @Server(url = "https://api.example.com/v1", description = "Production server"),
                @Server(url = "https://example.sandbox.api.example.com/v1", description = "Sandbox server")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {

}

//
//// @Value("${keycloak.auth-server-url}")
//private  static final String authServerUrl = "http://localhost:8888";
////    @Value("${keycloak.realm}")
//String realm  = "SpringBootKeycloak";
//
//private static final String OAUTH_SCHEME_NAME = "my_oAuth_security_schema";
//
//@Bean
//public OpenAPI openAPI() {
//    return new OpenAPI().components(new Components()
//                    .addSecuritySchemes(OAUTH_SCHEME_NAME, createOAuthScheme()))
//            .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME_NAME))
//            .info(new Info().title("Todos Management Service")
//                    .description("A service providing todos.")
//                    .version("1.0"));
//}
//
//private SecurityScheme createOAuthScheme() {
//    OAuthFlows flows = createOAuthFlows();
//    return new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
//            .flows(flows);
//}
//
//private OAuthFlows createOAuthFlows() {
//    OAuthFlow flow = createAuthorizationCodeFlow();
//    return new OAuthFlows().implicit(flow);
//}
//
//private OAuthFlow createAuthorizationCodeFlow() {
//    return new OAuthFlow()
//            .authorizationUrl(authServerUrl + "/realms/" + realm + "/protocol/openid-connect/auth")
//            .scopes(new Scopes().addString("read_access", "read data")
//                    .addString("write_access", "modify data"));
//}