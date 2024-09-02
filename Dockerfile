FROM quay.io/keycloak/keycloak:22.0.4

# Add the provider JAR file to the providers directory
ADD  ./target/keycloak.spiexamples-v1.0.1.jar /opt/keycloak/providers/

#ADD ./login-recaptcha-theme/login /opt/keycloak/themes/login-recaptcha-theme
#ADD ./login.ftl /opt/keycloak/themes/login.ftl
#ADD ./login.ftl /opt/jboss/keycloak/themes/base/login/login.ftl
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
