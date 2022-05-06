Movimiento:

- agregateA:
Creo que no deberia ser un metodo en Movimiento,
ya que son todas responsabilidades de la cuenta en general
(cambiar su saldo y agregar un elemento a una lista interna)
Por lo cual deberia ser implementado desde la cuenta misma

- calcularValor: Es raro que un movimiento calcule el valor
de una cuenta, a lo sumo la cuenta deberia tomar un movimiento
y a partir de eso actualizarse a si misma.

Por otro lado, hay un intento de polimorfismo segun el tipo de Movimiento revisando un bool. 
Una mejor solucion es delegar la implementacion en una interfaz compuesta por Movimiento donde cada clase provea su diferente tipo de funcionalidad.
Esto se podria lograr aun mas simplemente con un enum
que lleve comportamiento.

Cuenta:

- multiples constructores, hacer uno solo
con todos los elementos

- sacar y poner tienen mucha logica repetida
ademas son metodos muy largos que pueden ser delegados

- hay mucha logica de manipulacion de la lista de movimientos, por lo cual convendria delegarlo a una clase
Movimientos por separado.

- el saldo no deberia tener setters.

- setMovimientos no sirve

- agregarMovimiento() es basicamente un constructor que
- es llamado medio ciclicamente desde movimiento 

Tests: 

- Poner() no testea nada
- TresDepositos() no testea nada
- Todos los tests llevan nombres de acciones 
en lugar de nombres de condiciones a revisar