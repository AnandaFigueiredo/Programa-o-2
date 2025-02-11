package util;

import java.util.List;
import modelo.Animal;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste para o DAO de Animais.
 * Testa inserção, listagem e exclusão de registros.
 *
 * @author Usuário
 */
public class DaoTest {
    
    public DaoTest() {
    }

    @Test
    public void testInserirAnimal() {
        Animal a = new Animal("B1", "Vaquinha", "Holandesa");
        Dao<Animal> dao = new Dao<>(Animal.class);
        dao.inserir(a);    
    }
    
    @Test
    public void testListarAnimais() {
        Dao<Animal> dao = new Dao<>(Animal.class);
        List<Animal> lista = dao.listarTodos(); 
        for (Animal a : lista) {
            System.out.println(a.getIdentificacao() + " " + a.getApelido());
        }
    }
    
//    @Test
//    public void testExcluir(){
//        Dao<Animal> dao = new Dao<>(Animal.class);
//        // boolean result = dao.excluir("identificacao", "15"); // Para dar errado 
//        boolean result = dao.excluir("identificacao", "B1");  // Para dar certo
//        System.out.println(result);
//    }
}
