package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:websites_config.properties")
public interface WebsiteConfig extends Config {
    @Key("otus_url")
    String otusUrl();

    @Key("otus_login_page_url")
    String otusLoginPageUrl();

    @Key("otus_personal_page_url")
    String otusPersonalPageUrl();

    @Key("browser")
    String browser();
}