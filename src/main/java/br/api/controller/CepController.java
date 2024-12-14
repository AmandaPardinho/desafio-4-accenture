package br.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.api.service.CepService;
import br.api.model.Address;
import br.api.repository.CepRepository;

@RestController
public class CepController {

	@Autowired
	private CepService cepService;

	@Autowired
	private CepRepository cepRepository;

	public CepController(CepService cepService, CepRepository cepRepository) {
		this.cepService = cepService;
		this.cepRepository = cepRepository;
	}

	@GetMapping("/{cep}")
	public ModelAndView getCep(@PathVariable String cep) {
		
		Address adress = cepService.findAdressByCep(cep);

		ModelAndView modelAndView = new ModelAndView("cep");		
		modelAndView.addObject("cep", adress);
		
		return modelAndView;
	}

	@PostMapping("/{cep}")
	public ModelAndView post(@PathVariable String cep) {
		Address adress = cepService.findAdressByCep(cep);

		cepRepository.save(adress);

		ModelAndView modelAndView = new ModelAndView("cep");		
		modelAndView.addObject("cep", adress);
		
		return modelAndView;
    }

}