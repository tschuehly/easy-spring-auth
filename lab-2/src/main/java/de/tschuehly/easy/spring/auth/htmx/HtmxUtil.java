package de.tschuehly.easy.spring.auth.htmx;

//import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader;
//import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxSwapType;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxSwapType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriTemplate;

public class HtmxUtil {
  public static String target(String id){
    return "#" + id;
  }

  public static void retarget(String cssSelector) {
    setHeader(HtmxResponseHeader.HX_RETARGET.getValue(), cssSelector);
  }

  public static void reswap(HxSwapType hxSwapType){
    setHeader(HtmxResponseHeader.HX_RESWAP.getValue(), hxSwapType.getValue());
  }

  public static void trigger(String event) {
    setHeader(HtmxResponseHeader.HX_TRIGGER.getValue(), event);
  }

  public static String URI(String uriTemplate, Object... variables) {
    return new UriTemplate(uriTemplate).expand(variables).toString();
  }

  public static void setHeader(String headerName, String headerValue) {
    HttpServletResponse response = getResponse();
    response.setHeader(headerName, headerValue);
  }


  private static HttpServletRequest getRequest() {
    Optional<HttpServletRequest> httpServletResponse =
        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .filter(ServletRequestAttributes.class::isInstance)
            .map(ServletRequestAttributes.class::cast)
            .map(ServletRequestAttributes::getRequest);
    return httpServletResponse.orElseThrow(
        () -> new RuntimeException(
            "No Request found in RequestContextHolder")
    );
  }

  private static HttpServletResponse getResponse() {
    Optional<HttpServletResponse> httpServletResponse =
        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .filter(ServletRequestAttributes.class::isInstance)
            .map(ServletRequestAttributes.class::cast)
            .map(ServletRequestAttributes::getResponse);
    return httpServletResponse.orElseThrow(
        () -> new RuntimeException(
            "No Response found in RequestContextHolder")
    );
  }

}
