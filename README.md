# BowlingGame
Simple program to keep the score a game of bowling

La prueba técnica trata de implementar en cualquier lenguaje una calculadora de puntos de un juego de bolos.

Las reglas para puntuar una partida son las siguientes:

    * Un jugador tiene 10 turnos
    * Por cada turno el jugador tiene 2 tiradas
    * Cada bolo derribado cuenta por 1 punto
    * Cuando el jugador derriba los 10 bolos en la primera tirada de un turno consigue un "strike"
    * La puntuación de un "strike" es 10 más el número de bolos derribados en las próximas 2 tiradas
    * Cuando un jugador consigue un "strike" no realiza la segunda tirada del turno y pasa directamente al siguiente turno
    * Cuando un jugador hace un "strike" en el último turno obtiene 2 tiradas adicionales para calcular los puntos del último turno
    * Cuando el jugador derriba los 10 bolos al final de un turno (usando las 2 tiradas) consigue un "spare"
    * La puntuación de un "spare" es 10 más el número de bolos derribados en la próxima tirada
    * Cuando un jugador hace un "spare" en el último turno obtiene 1 tirada adicional para calcular los puntos del último turno
    * Los puntos de las tiradas adicionales no cuentan por sí solos ya que solo se utilizan para calcular la bonificación del último turno
    * La puntuación total de una partida es la suma de los puntos de todos los turnos
    * Una partida perfecta donde cada tirada es un "strike" suma un total de 300 puntos

Se pide implementar una clase `BowlingGame` con los siguientes métodos:

    * void roll(int) utilizado para registrar cada tirada de un jugador
    * int score() devuelve la puntuación total de una partida

No es necesario controlar el número de turnos ni tiradas, la API siempre será utilizada de la misma manera, se registra cada tirada usando `roll` y al final de la partida se llama al método `score` para obtener el total.