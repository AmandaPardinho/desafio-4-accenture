package br.api.service;

import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.api.dto.AlunoDto;
//import br.api.model.Address;
import br.api.model.Aluno;
import br.api.repository.CepRepository;
import br.api.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private CepService cepService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CepRepository cepRepository;


    public AlunoService(AlunoRepository alunoRepository, CepService cepService, CepRepository cepRepository) {
        this.alunoRepository = alunoRepository;
        this.cepRepository = cepRepository;
        this.cepService = cepService;
    }

    public List<AlunoDto> listarCadastros(){
        try {
            List<Aluno> alunos = alunoRepository.findAll();
            List<AlunoDto> alunosDto = alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
            return alunosDto;
        } catch (Exception e) {
            System.out.println("Erro ao listar cadastros" + e.getMessage());
            return null;
        }
    }

    public AlunoDto cadastroById(Long id){
        try {
            Aluno cadastro = alunoRepository.findById(id).orElse(null);

            if (cadastro == null) {
                return null;
            }

            AlunoDto cadastroDto = new AlunoDto(cadastro);
            return cadastroDto;         
        } catch (Exception e) {
            System.out.println("Erro ao buscar cadastro: " + e.getMessage());
            return null;
        }
    }

    public void insertAluno(AlunoDto alunoDto){
        try {
            Aluno aluno = new Aluno(alunoDto);   
            alunoRepository.save(aluno);    
        } catch (Exception e) {
            System.out.println("Erro ao inserir cadastro: " + e.getMessage());
        }
    }

    public void updateAluno(AlunoDto alunoDto){
        try {
            Aluno aluno = alunoRepository.findById(alunoDto.getId()).orElse(null);
            
            if (aluno == null) {
                System.out.println("Cadastro não encontrado!");
                return;
            }

            if(alunoDto.getNome() != null){
                aluno.setNome(alunoDto.getNome());
            }
            if(alunoDto.getEmail() != null){
                aluno.setEmail(alunoDto.getEmail());
            }
            if(alunoDto.getCpf() != null){
                aluno.setCpf(alunoDto.getCpf());
            }
            if(alunoDto.getDataNascimento() != null){
                aluno.setDataNascimento(alunoDto.getDataNascimento());
            }
            if(alunoDto.getTelefone() != null){
                aluno.setTelefone(alunoDto.getTelefone());
            }

            alunoRepository.save(aluno);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cadastro: " + e.getMessage());
        }
    }

    public void deleteAluno(Long id){
        try {
            Aluno aluno = alunoRepository.findById(id).orElse(null);

            if (aluno == null) {
                System.out.println("Cadastro não encontrado!");
                return;
            }

            alunoRepository.delete(aluno);
        } catch (Exception e) {
            System.out.println("Erro ao deletar cadastro: " + e.getMessage());
        }
    }

    // public AddressService(CepService cepService, CepRepository cepRepository) {
    //     this.cepService = cepService;
    //     this.cepRepository = cepRepository;
    // }

    // public Address findAddressByCep(String cep) {
    //     return cepService.findAdressByCep(cep);
    // }

    // public Address updateAddress(String cep, Address addressDetails) {
    //     Optional<Address> optionalAddress = cepRepository.findById(cep);
    //     if (optionalAddress.isPresent()) {
    //         Address address = optionalAddress.get();
    //         address.setLogradouro(addressDetails.getLogradouro());
    //         address.setLocalidade(addressDetails.getLocalidade());
    //         address.setUf(addressDetails.getUf());
    //         address.setCep(addressDetails.getCep());
    //         return cepRepository.save(address);
    //     } else {
    //         throw new RuntimeException("Endereço não encontrado para o CEP: " + cep);
    //     }    
    // }

    // public void deleteAddress(String cep) {
    //     Optional<Address> optionalAddress = cepRepository.findById(cep);
    //     if (optionalAddress.isPresent()) {
    //         cepRepository.delete(optionalAddress.get());
    //     } else {
    //         throw new RuntimeException("Endereço não encontrado para o CEP: " + cep);
    //     }
    // }
}
