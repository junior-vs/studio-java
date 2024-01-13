package br.com.studio.java.refl;

import br.com.studio.java.Endereco;
import br.com.studio.java.Pessoa;
import br.com.studio.java.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;


class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");


    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTO = transformator.transformToDTO(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
    }

    @Test
    public void shouldNotTransform() {
        Endereco endereco = new Endereco("Nome da rua", 1);
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            Transformator transformator = new Transformator();
            transformator.transformToDTO(endereco);
        });
    }

    @Test
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoaSemCPF = new Pessoa(0, "João",  null);
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTOSemCPF = transformator.transformToDTO(pessoaSemCPF);

        Assertions.assertEquals(pessoa.getNome(), pessoaDTOSemCPF.getNome());
        Assertions.assertNull(pessoaDTOSemCPF.getCpf());
    }


}