package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.Collections.emptyList;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  public void setUp() {
    cuenta = cuentaSinPlata();
  }

  @Test
  void sePuedePonerDinero() {
    cuenta.poner(1500);
    assertEquals(1500.0, cuenta.getSaldo());
  }

  @Test
  void noSePuedePonerUnMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.poner(-1500));
  }

  @Test
  void sePuedenHacerTresDepositos() {
    cuenta.poner(1500);
    cuenta.poner(456);
    cuenta.poner(1900);
    assertEquals(1500+456+1900, cuenta.getSaldo());
  }

  @Test
  void noSePuedeHacerMasDeTresDepositosDiarios() {
    assertThrows(MaximaCantidadDepositosException.class, () -> {
          cuenta.poner(1500);
          cuenta.poner(456);
          cuenta.poner(1900);
          cuenta.poner(245);
    });
  }

  @Test
  void noSePuedeExtraerMasQueElSaldo() {
    assertThrows(SaldoMenorException.class, () -> cuentaCon90Pesos().sacar(1001));
  }

  @Test
  public void noSePuedeExtraerMasDe1000() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> cuentaCon5Lucas().sacar(1001));
  }

  @Test
  public void noSePuedeExtraerUnMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuentaSinPlata().sacar(-500));
  }

  private Cuenta cuentaCon5Lucas(){
    return new Cuenta(5000, new Movimientos(emptyList()));
  }

  private Cuenta cuentaCon90Pesos(){
    return new Cuenta(90, new Movimientos(emptyList()));
  }

  private Cuenta cuentaSinPlata(){
    return new Cuenta(0, new Movimientos(emptyList()));
  }
}