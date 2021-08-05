package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:users_data.properties")
public interface UserConfig extends Config {


    @Key("otus_login")
    String otusLogin();

    @Key("otus_password")
    String otusPassword();

    @Key("facebook_contact")
    String facebookcontact();

    @Key("vk_contact")
    String vkcontact();

    @Key("ok_contact")
    String okcontact();
}

