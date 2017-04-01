package me.jastz.wechat.wxa;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.jastz.wechat.wxa.vo.WxaUserInfo;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by zhiwen on 2017/3/17.
 */
public class WxaUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static WxaUserInfo getUserInfo(String sessionKey, String encryptedData, String iv) {
        byte[] sessionKeyByte = Base64.getDecoder().decode(sessionKey.getBytes());
        byte[] encryptedDateByte = Base64.getDecoder().decode(encryptedData.getBytes());
        byte[] ivByte = Base64.getDecoder().decode(iv.getBytes());
        WxaUserInfo wxaUserInfo = null;
        try {
            wxaUserInfo = objectMapper.readValue(new String(Pkcs7Encoder.decryptOfDiyIv(encryptedDateByte
                    , sessionKeyByte, ivByte)), WxaUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxaUserInfo;
    }
}
