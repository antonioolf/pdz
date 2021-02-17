## PDZ - Testes unitários

### Ferramentas utilizadas

* JUnit4

* Robolectric
    - Permite a execução rápida e confiável de testes no Android sem a necessidade de utilizar o emulador ou um dispositivo real. Os testes são executados dentro da JVM.


* Biblioteca Truth para declarações de assertividade mais legíveis


    Ex:
    ```java
    assertThat(object).hasFlags(FLAGS)
    assertThat(object).doesNotHaveFlags(FLAGS)
    assertThat(intent).hasData(URI)
    assertThat(extras).string(string_key).equals(EXPECTED)
    ```

- Testes de UI

    Framework Espresso


