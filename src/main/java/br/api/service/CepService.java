package br.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.api.model.Address;


@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface CepService {
	
	@GetMapping("{cep}/json")
    Address findAdressByCep(@PathVariable("cep") String cep);
}