package kr.lfin.exam.common.aspect;

import com.google.common.base.Strings;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.lfin.exam.common.cnsts.Const;
import kr.lfin.exam.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;

/**
 * A servlet filter to log request and response
 *
 */
@Component
@Order(2)
public class HttpLoggingAspect implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        logger.trace("Initializing filter:{}", this);
    }

    /**
     * Request, Response Logging
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        Instant start = Instant.now();

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        chain.doFilter(requestWrapper, responseWrapper);

        // ---------------------------
        // request/response logging
        // ---------------------------
        String reqBody = getRequestBody(requestWrapper);
        String resBody = getResponseBody(responseWrapper);

        String method = req.getMethod();
        String url = req.getRequestURI();
        String queryString = req.getQueryString();

        if (!Strings.isNullOrEmpty(queryString)) {
            url = String.format("%s?%s", url, queryString);
        }

        // request
        String remoteAddr = req.getRemoteAddr();
        String ua = req.getHeader(Const.HEADER_USER_AGENT);
        String reqContentType = req.getHeader(Const.HEADER_CONTENT_TYPE);
        String accept = req.getHeader(Const.HEADER_ACCEPT);

        // response
        int status = res.getStatus();
        String txid = JsonUtil.getTagValue(resBody, Const.RES_NAME_TXID);
        String code = JsonUtil.getTagValue(resBody, Const.RES_NAME_STATUS_CODE);
        String resContentType = res.getHeader(Const.HEADER_CONTENT_TYPE);

        long duration = Duration.between(start, Instant.now()).toMillis();

        //TODO health check로 시작하면 로깅하지 말 것 참조 : PMAS
        if (!url.equals("/health/ping")) {
            //  format
            //  <->txid ReAddress UA Method URL Status Code Duration reqLength resLength
            logger.info("<-> {} \t{}\t{}\t{} {}\t{}\t{}\t{}ms\t{}\t{}",
                    txid, remoteAddr, ua,
                    method, url, status, code,
                    duration, reqBody.length(), resBody.length());
            // >>>txid request header, length, body
            logger.info(">>> {} {}: {}, {}: {}, {}, [{}]",
                    txid, Const.HEADER_CONTENT_TYPE, reqContentType,
                    Const.HEADER_ACCEPT, accept,
                    reqBody.length(), reqBody);
            // <<<txid response header, length, body
            logger.info("<<< {} {}: {}, {}, [{}]",
                    txid, Const.HEADER_CONTENT_TYPE, resContentType,
                    resBody.length(), resBody);
        }
    }
    @Override
    public void destroy() {
        logger.trace("Destructing filter:{}", this);
    }

    /**
     * Request body 가져오기
     *
     * @param request
     * @return
     */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        String body = "";
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    body = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    body = "";
                }
            }
        }
        return body;
    }

    /**
     * Response Body 가져오기
     *
     * @param response
     * @return
     * @throws IOException
     */
    private String getResponseBody(final HttpServletResponse response) throws IOException {
        String body = "";
        ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                body = new String(buf, 0, buf.length, Const.CHARACTER_UTF_8);
                wrapper.copyBodyToResponse();
            }
        }
        return body;
    }
}