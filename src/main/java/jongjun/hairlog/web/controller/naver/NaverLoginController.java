package jongjun.hairlog.web.controller.naver;

import jongjun.hairlog.web.dto.get.NaverLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;


// note 전체적인 흐름대로 구현한 것이 아닌 쪼개서 구현한 것 실제로 구현할 때는 합쳐서 구현해야 한다.
@Slf4j
@Controller
@RequestMapping("/api/naver")
@RequiredArgsConstructor
public class NaverLoginController {

    @GetMapping("/authenticate")
    public String login(HttpServletRequest req) {
        String sessionToken = makeSessionToken(req);
        String url = getLoginUrl("https://nid.naver.com/oauth2.0/authorize?client_id=aCiFsiJ_R0Uuq1d0q4tA", "response_type=code", "redirect_uri=http://localhost:8080/api/naver/callback", sessionToken);
        return "redirect:" + url;
    }
    private String makeSessionToken(HttpServletRequest req) {
        String state = generateState();
        req.getSession()
           .setAttribute("state", state);
        return state;
    }

    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    private String getLoginUrl(String baseUrl, String responseType, String redirectURI, String sessionToken) {
        String stateToken = "state="+ sessionToken;
        return baseUrl + "&" + responseType + "&" + redirectURI + "&" + stateToken;
    }

    @ResponseBody
    @GetMapping("/callback")
    public String callback(HttpServletRequest req, @RequestParam("code") String code, @RequestParam("state") String state) {
        if (!state.equals((req.getSession()
                              .getAttribute("state")))) {
            throw new IllegalStateException("session state token이 일치하지 않습니다.");
        }
        // 편의를 위해 session에 저장
        req.getSession()
           .setAttribute("code", code);
        req.getSession()
           .setAttribute("state", state);
        return "code : " + code + ", state : " + state;
    }

    @ResponseBody
    @GetMapping("/tokenV1")
    public String token(@SessionAttribute("state") String state, @SessionAttribute("code") String code, HttpServletRequest req, RestTemplate restTemplate) {
        String url = getTokenUrl("https://nid.naver.com/oauth2.0/token?client_id=aCiFsiJ_R0Uuq1d0q4tA&client_secret=4fFa29NRim", "grant_type=authorization_code", "redirect_uri=http://localhost:8080/api/naver/callback", state, code);
        NaverLoginDTO naverLoginDTO = restTemplate.getForObject(url, NaverLoginDTO.class);
        String access_token = naverLoginDTO.getAccess_token();
        // 편의를 위해
        req.getSession()
           .setAttribute("access_token", access_token);
        return naverLoginDTO.toString();
    }

    @ResponseBody
    @GetMapping("/tokenV2")
    public String token(HttpServletRequest req, @RequestParam("code") String code, @RequestParam("state") String state, RestTemplate restTemplate) {
        req.setAttribute("code", code);
        req.setAttribute("state", state);

        String url = getTokenUrl("https://nid.naver.com/oauth2.0/token?client_id=aCiFsiJ_R0Uuq1d0q4tA&client_secret=4fFa29NRim", "grant_type=authorization_code", "redirect_uri=http://localhost:8080/api/naver/callback", state, code);
        NaverLoginDTO naverLoginDTO = restTemplate.getForObject(url, NaverLoginDTO.class);
        String access_token = naverLoginDTO.getAccess_token();
        // 편의를 위해
        req.getSession()
           .setAttribute("access_token", access_token);
        return naverLoginDTO.toString();
    }

    private String getTokenUrl(String baseUrl, String grantType, String redirectURI, String sessionToken, String sessionCode) {
        String stateToken = "state="+ sessionToken;
        String codeToken = "code=" + sessionCode;
        return baseUrl + "&" + grantType + "&" + redirectURI + "&" + stateToken +"&"+ codeToken;
    }

    @ResponseBody
    @GetMapping("/infoV1")
    public ResponseEntity<String> infoV1(@SessionAttribute("access_token") String token, RestTemplate restTemplate) {
        // todo RestTemplate 공부하기
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON,}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "https://openapi.naver.com/v1/nid/me";
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        // todo unicode로 나오는 값 처리할 수 있는 transfer 구현해야 할 것 같음
    }

    @ResponseBody
    @GetMapping("/infoV2")
    public ResponseEntity<String> infoV2(@RequestParam("access_token") String token, RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "https://openapi.naver.com/v1/nid/me";
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }


}
