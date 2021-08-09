package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:connection.properties")
public interface ProjectServerConfig extends Config {
    @Key("url")
    String url();

    @Key("urlTele2")
    String urlTele2();

    @Key("urlyandex")
    String urlyandex();
}
