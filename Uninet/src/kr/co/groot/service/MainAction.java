package kr.co.groot.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;

public class MainAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    
    String clientId = "vqLcW7JqiYZue0zeuYqs";
    String clientSecret = "3ItRGLhcxI";
    
    try {
      String text = URLEncoder.encode("대학교", "UTF-8");
      String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text + "&display=3";
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("X-Naver-Client-Id", clientId);
      con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
      int responseCode = con.getResponseCode();
      BufferedReader br;
      if (responseCode == 200) {
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }
      String inputLine;
      StringBuffer resp = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        resp.append(inputLine);
      }
      br.close();
      request.setAttribute("news", resp);
    } catch (UnsupportedEncodingException e) {
      System.out.println("뉴스: " + e.getMessage());
    } catch (MalformedURLException e) {
      System.out.println("뉴스: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("뉴스: " + e.getMessage());
    }
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/main.jsp");
    
    return forward;
  }
}
