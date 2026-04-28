<?php 
    for ($i=1; $i <= 10; $i++) { 
        if ($i % 2 == 0) {
            echo "$i é par <br>";
        }
    }

    echo "<hr>";

    echo "<table border='1';>";
        $numero = 5;
        echo "<tr>
                <th colspan='2'>Tabuada do: $numero</th>
              </tr>";
        echo "<tr>
                <th>Operação</th>
                <th>Resultado</th>
              </tr>";
        for ($i=0; $i < 11; $i++) { 
            $resultado = $numero * $i;
            echo "<tr>
                    <td>$numero x $i</td>
                    <td>$resultado</td>
                  </tr>";
        }
    echo "</table>";
?>