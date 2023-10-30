package kr.lfin.exam.common.cnsts;

/**
 * Http Constant
 */
public class Const {
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_APP_JSON = "application/json";
    public static final String HEADER_ACCEPT = "Accept";
    /** 확장헤더 txid */
    public static final String HEADER_X_TXID = "X-CROWD-TXID";
    /**  확장헤더 결과코드 */
    public static final String HEADER_X_CODE = "X-CROWD-CODE";
    public static final String CHARACTER_UTF_8 = "UTF-8";

    public static final String RES_NAME_TXID = "txid";
    public static final String RES_NAME_STATUS_CODE = "status";

    /** test 시 사용할 mysql docker image name */
    public static final String TEST_DOCKER_IMAGE_MYSQL =  "mysql:8.0.32";
}
