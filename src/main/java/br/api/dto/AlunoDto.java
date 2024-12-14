package br.api.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import br.api.model.Aluno;

public class AlunoDto {
    
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataNascimento;

    private String telefone;
    private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;	
	// private String ddd;
    // private String ibge;
	// private String gia;
	// private String siafi;

    //Construtor
    public AlunoDto() {
        
    }

    public AlunoDto(Aluno aluno) {

        
    }

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

    // public String getIbge() {
	// 	return ibge;
	// }
	// public void setIbge(String ibge) {
	// 	this.ibge = ibge;
	// }

	// public String getGia() {
	// 	return gia;
	// }

	// public void setGia(String gia) {
	// 	this.gia = gia;
	// }

	// public String getDdd() {
	// 	return ddd;
	// }
	// public void setDdd(String ddd) {
	// 	this.ddd = ddd;
	// }
    
	// public String getSiafi() {
	// 	return siafi;
	// }
	// public void setSiafi(String siafi) {
	// 	this.siafi = siafi;
	// }

    @Override
    public String toString() {
        return "CadastroDto{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro=" + bairro +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                // ", ddd='" + ddd + '\'' +
                // ", ibge='" + ibge + '\'' +
                // ", gia='" + gia + '\'' +
                // ", siafi='" + siafi + 
                '}';               
    }
}
