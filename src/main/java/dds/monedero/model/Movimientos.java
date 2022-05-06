package dds.monedero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movimientos {
  private List<Movimiento> elementos;

  public Movimientos(List<Movimiento> movimientos) {
    this.elementos = new ArrayList<>(movimientos);
  }

  public void add(Movimiento movimiento) {
    elementos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return elementos.stream()
        .filter(movimiento -> movimiento.fueExtraidoEn(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public long cantidadDeDepositosEn(LocalDate fecha) {
    return elementos.stream()
        .filter(movimiento -> movimiento.fueDepositadoEn(fecha))
        .count();
  }
}
