package xb.mall.common;

import lombok.Getter;

@Getter
public enum Enums {
    AUTOPARTS("de0309d8e89b11e8847c83fe7038c841","autoParts"),
    BLUESPEAKERS("de1727c4e89b11e8847c83fe7038c841","blueSpeaker"),
    TOY("de1e1638e89b11e8847c83fe7038c841","toy"),

    FEATUREPRODUCT("cac6f1d8ef0111e89b6a36e2cc59559f","feature"),

    BANNER("","banner"),
    MIDDLEADS("","middleAds"),
    ;

    private String code;
    private String value;

    Enums(String code, String value){
        this.code=code;
        this.value=value;
    }
}
