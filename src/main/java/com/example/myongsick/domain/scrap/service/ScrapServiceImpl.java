package com.example.myongsick.domain.scrap.service;

import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapRequest;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.entity.CampusType;
import com.example.myongsick.domain.scrap.entity.Scrap;
import com.example.myongsick.domain.scrap.entity.Store;
import com.example.myongsick.domain.scrap.exception.AlreadyScrapException;
import com.example.myongsick.domain.scrap.exception.NotFoundScrapException;
import com.example.myongsick.domain.scrap.exception.NotFoundStoreException;
import com.example.myongsick.domain.scrap.repository.ScrapRepository;
import com.example.myongsick.domain.scrap.repository.StoreRepository;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.exception.NotFoundUserException;
import com.example.myongsick.domain.user.repository.UserRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScrapServiceImpl implements ScrapService{
  private final UserRepository userRepository;
  private final ScrapRepository scrapRepository;
  private final StoreRepository storeRepository;

  @Value("${kakao.rest-key}")
  private String key;

  @Override
  public Page<ScrapResponse> getScrapList(String phoneId, Pageable pageable) {
    // 유저 조회
    User user = userRepository.findByPhoneId(phoneId).orElseThrow(NotFoundUserException::new);
    return scrapRepository.findAllByUser(user, pageable).map(ScrapResponse::toDto);
  }

  @Override
  @Transactional
  public ScrapResponse createScrap(ScrapRequest request) {
    User user = userRepository.findByPhoneId(request.getPhoneId()).orElseThrow(NotFoundUserException::new);
    Store store = createStore(request);
    if (scrapRepository.findByStoreAndUser(store, user).isPresent())
      throw new AlreadyScrapException();
    Scrap scrap = scrapRepository.save(Scrap.builder().store(store).user(user).build());
    return ScrapResponse.toDto(scrap);
  }

  private Store createStore(ScrapRequest request) {
    Optional<Store> already = storeRepository.findByCode(request.getCode());
    if (already.isPresent())
      return already.get();
    else {
      Store store = Store.builder().code(request.getCode()).name(request.getName())
          .category(request.getCategory()).distance(request.getDistance()).address(request.getAddress())
          .urlAddress(request.getUrlAddress()).contact(request.getContact()).campus(CampusType.valueOf(request.getCampus()))
          .longitude(request.getLongitude()).latitude(request.getLatitude())
          .build();
    return storeRepository.save(store);
    }
  }
  @Override
  @Transactional
  public void deleteScrap(Long scrapId) {
    Scrap scrap = scrapRepository.findById(scrapId).orElseThrow(NotFoundScrapException::new);
    scrapRepository.deleteById(scrap.getId());
  }

  @Override
  public Page<ScrapCountResponse> getScrapCount(String campus, Pageable pageable) {
    return scrapRepository.findAllCustom(campus, pageable).map(ScrapCountResponse::toDto);
  }

  @Transactional
  public void updateStore() {
    List<Store> storeList = storeRepository.findAll();
    String pass = "";
    for (int i =0; i < storeList.size(); i++) {
     try {
        pass = URLEncoder.encode(storeList.get(i).getName(), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
      String host = storeList.get(i).getCampus() == CampusType.YONGIN ? "https://dapi.kakao.com/v2/local/search/keyword?query=" + pass + "&category_group_code=FD6%2C%20CE7&x=127.18758354347&y=37.224650469991&radius=3000&page=2&size=15&sort=distance" : "https://dapi.kakao.com/v2/local/search/keyword?query=" + pass + "&category_group_code=FD6%2C%20CE7&x=126.923460283882&y=37.5803504797164&radius=3000&page=2&size=15&sort=distance";
      try {
        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Authorization", " KakaoAK " + key);
        urlConnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {result += line;}
        JsonObject jobj = (JsonObject) JsonParser.parseString(result);
        JsonElement element = jobj.get("documents");
        JsonElement x = element.getAsJsonArray().get(0).getAsJsonObject().get("x");
        JsonElement y = element.getAsJsonArray().get(0).getAsJsonObject().get("y");
        storeList.get(i).updatePoint(x.getAsString(), y.getAsString());
        br.close();
      } catch (MalformedURLException e) {
        throw new RuntimeException(e);
      } catch (ProtocolException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public ScrapCountResponse getStoreOne(Long storeId) {
    return storeRepository.findByIdCustom(storeId).map(ScrapCountResponse::toDto).orElseThrow(NotFoundStoreException::new);

  }
}
