package dds.monedero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movimientos {
  private List<Movimiento> movimientos;

  public Movimientos(List<Movimiento> movimientos) {
    this.movimientos = new ArrayList<>(movimientos);
  }

  public void add(Movimiento movimiento) {
    movimientos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return movimientos.stream()
        .filter(movimiento -> movimiento.fueExtraidoEn(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public long cantidadDeDepositosEn(LocalDate fecha) {
    return movimientos.stream()
        .filter(movimiento -> movimiento.fueDepositadoEn(fecha))
        .count();
  }


}
