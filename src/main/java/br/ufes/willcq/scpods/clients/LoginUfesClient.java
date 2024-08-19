package br.ufes.willcq.scpods.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient( name = "loginUfesClient", url = "http://0.0.0.0:8000" )
public interface LoginUfesClient {

    @RequestMapping( method = RequestMethod.GET, value = "/login.html" )
    String getLoginPage();

}
