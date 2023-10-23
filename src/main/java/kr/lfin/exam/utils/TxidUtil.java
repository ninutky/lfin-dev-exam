package kr.lfin.exam.utils;

import java.util.UUID;

public class TxidUtil {
    public static String generateTxid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toLowerCase();
    }
}
