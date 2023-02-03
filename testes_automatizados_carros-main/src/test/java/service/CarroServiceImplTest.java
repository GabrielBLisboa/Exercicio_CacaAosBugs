package service;

import model.Carro;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarroServiceImplTest {

    Carro carro1;
    CarroService carroService;

    @Before
    public void setup(){
        carroService = new CarroServiceImpl();
        carro1 = new Carro("Azul", "Fiat", "Uno", 2015, 150);
    }

    @Test
    public void deveIniciarDesligado() {
        // Dado: Classe Carro

        //Quando: O carro for criado deve iniciar desligado

        Assert.assertFalse(carro1.isLigado());

        //Então: Deve conseguir ligar (e mostra estado atual)

        carroService.ligar(carro1);
        Assert.assertTrue(carro1.isLigado());

    }

    @Test
    public void deveLigarCorretamente() {
        // Dado: Um carro criado

        // Quando: For ligado
        carroService.ligar(carro1);

        // Então:
        Assert.assertTrue(carro1.isLigado());

    }

    @Test
    public void deveAcelerarCorretamente() {

        // Dado: Um carro ligado
        carroService.ligar(carro1);

        // Quando: For este acelerado em 10km/h
        carroService.acelerar(carro1, 10);
        System.out.println(carroService.estadoAtual(carro1));

        // Então: Sua velocidade será 10
        Assert.assertTrue(carro1.getVelocidadeAtual() == 10);
    }
    @Test
    public void deveFrearCorretamente() {
        // Dado: O carro está ligado e a 40km/h
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 40);

        // Quando: Sua velocidade for reduzida de 20km/h
        carroService.frear(carro1,20);

        // Então: Sua velocidade deve ser de 20km/h
        Assert.assertEquals(20, carro1.getVelocidadeAtual());
        System.out.println(carroService.estadoAtual(carro1));

    }

    @Test
    public void velocidadeNaoDeveSerMenorQueZero() {

        // Dado: Um carro ligado e parado
        carroService.ligar(carro1); // velocidade zero

        // Quando: For pedido que reduza sua velocidade
        carroService.frear(carro1, 10);

        // Então: Sua velocidade não pode ser negativa
        Assert.assertTrue(carro1.isLigado());
        System.out.println(carroService.estadoAtual(carro1));
        Assert.assertEquals(0, carro1.getVelocidadeAtual());
    }
    @Test
    public void naoPodeUltrapassarSuaVelocidadeMaxima(){
        // Dado: Um carro ligado e de velocidade máxima de 150km/h
        carroService.ligar(carro1); // velocidade zero

        // Quando:
        //         O carro estiver numa velocidade de 140km/h
        carroService.acelerar(carro1, 140);
        System.out.println(carroService.estadoAtual(carro1));

        //         E for pedido que aumente sua velocidade em mais 20km/h
        carroService.acelerar(carro1, 20);
        System.out.println(carroService.estadoAtual(carro1));

        // Então: Sua velocidade e não pode ultrapassar a velocidade máxima
        Assert.assertEquals(150, carro1.getVelocidadeAtual());
    }

    @Test
    public void deveLigarCorretamenteEAcelerarCorretamente() {
        // Dado:

        // Quando:
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 10);

        // Então:
        Assert.assertTrue(carro1.isLigado());
        Assert.assertEquals(10, carro1.getVelocidadeAtual());
    }

}
