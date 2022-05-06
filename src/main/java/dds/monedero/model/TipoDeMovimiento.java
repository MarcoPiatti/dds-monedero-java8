package dds.monedero.model;

public enum TipoDeMovimiento {
  DEPOSITO{
    @Override
    public double montoNuevo(double montoInicial, double montoMovimiento) {
      return montoInicial + montoMovimiento;
    }
  },
  EXTRACCION{
    public double montoNuevo(double montoInicial, double montoMovimiento) {
      return montoInicial - montoMovimiento;
    }
  };

  public abstract double montoNuevo(double montoInicial, double montoMovimiento);
}
