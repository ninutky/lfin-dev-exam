package kr.lfin.exam.domains;

import kr.lfin.exam.common.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Health {
    private String message;
    private String hostname;
    private String datetime;

    public Health(String message) {

        this.message = message;
        this.datetime = DateUtil.getNowToString(DateUtil.YYYY__MM__DD__HH__MM__ss__SSS, DateUtil.TIME_ZONE_SEOUL);
        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            this.hostname = "unknown";
        }
    }
}