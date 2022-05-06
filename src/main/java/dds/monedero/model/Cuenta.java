package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;

public class Cuenta {

  private double saldo = 0;
  private Movimientos movimientos;

  public Cuenta(double montoInicial, Movimientos movimientos) {
    saldo = montoInicial;
    this.movimientos = movimientos;
  }

  private void acreditarOperacion(double monto, TipoDeMovimiento tipo) {
    Movimiento movimiento = new Movimiento(LocalDate.now(), monto, tipo); 
    saldo = movimiento.calcularValor(saldo);
    movimientos.add(movimiento);
  }

  public void poner(double monto) {
    if (monto <= 0) {
      throw new MontoNegativoException(monto + ": el monto a ingresar debe ser un valor positivo");
    }

    if (movimientos.cantidadDeDepositosEn(LocalDate.now()) >= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }

    acreditarOperacion(monto, TipoDeMovimiento.EXTRACCION);
  }

  public void sacar(double monto) {
    if (monto <= 0) {
      throw new MontoNegativoException(monto + ": el monto a ingresar debe ser un valor positivo");
    }

    if (saldo - monto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + saldo + " $");
    }

    double montoExtraidoHoy = movimientos.getMontoExtraidoA(LocalDate.now());
    double limite = 1000 - montoExtraidoHoy;
    if (monto > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
          + " diarios, límite: " + limite);
    }
    
    acreditarOperacion(monto, TipoDeMovimiento.EXTRACCION);
  }
}
