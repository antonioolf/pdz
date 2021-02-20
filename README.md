## PDZ - Testes unitários

### Ferramentas utilizadas

### Testes unitários

* JUnit4

* Robolectric
    - Permite a execução rápida e confiável de testes no Android sem a necessidade de utilizar o emulador ou um dispositivo real. Os testes são executados dentro da JVM.


* Biblioteca Truth para declarações de assertividade mais legíveis


    Ex:
```kotlin
assertThat(obj).hasFlags(FLAGS)
assertThat(obj).doesNotHaveFlags(FLAGS)
assertThat(intent).hasData(URI)
assertThat(extras).string(string_key).equals(EXPECTED)
```

### Testes de UI - Framework Espresso

```kotlin
@Test
fun greeterSaysHello() {
    onView(withId(R.id.name_field)).perform(typeText("Steve"))
    onView(withId(R.id.greet_button)).perform(click())
    onView(withText("Hello Steve!")).check(matches(isDisplayed()))
}
```

- Conceitos chave
    * Espresso: ponto de entrada para interações com visualizações (via onView() e onData()). Também expõe APIs que não estão necessariamente vinculadas a nenhuma visualização, como pressBack().
    * ViewMatchers: um conjunto de objetos que implementam a interface Matcher<? super View>. Você pode transmitir um ou mais deles ao método onView() para localizar uma visualização na hierarquia atual.
    * ViewActions: um conjunto de objetos ViewAction que podem ser transmitidos para o método ViewInteraction.perform(), como click().
    * ViewAssertions: um conjunto de objetos ViewAssertion que podem ser transmitidos ao método ViewInteraction.check(). Na maioria das vezes, você usará a declaração de correspondências, que usa um matcher de visualização para declarar o estado da visualização selecionada no momento.
