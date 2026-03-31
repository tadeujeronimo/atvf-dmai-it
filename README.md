# DeliveryApp - Atividade Final (DMAI)

Aplicativo Android nativo desenvolvido como projeto final do modulo Desenvolvimento Mobile Android Intermediario (iTalents).

O app cobre o fluxo de delivery de comida com autenticacao, catalogo de produtos, carrinho e finalizacao de pedido, alem de funcionalidades extras como favoritos, historico de pedidos, gerenciamento de usuarios e controle de endereco local.

## Requisitos da Atividade Final

- Entregar um aplicativo completo de delivery de comida desde o login ate adicionar ao carrinho.
- Enviar dados para API e armazenar parte localmente no celular com Room.
- Seguir arquitetura MVVM.
- Conter testes unitarios.

### Como este projeto atende aos requisitos

- Fluxo principal implementado: login -> home (produtos) -> detalhes -> carrinho.
- Integracao remota com API via Retrofit/OkHttp (login, produtos, carrinho e usuarios).
- Persistencia local com Room para favoritos, enderecos e pedidos.
- Arquitetura MVVM com ViewModels, Repositories, DataSources e camada UI separadas.
- Suite de testes unitarios para repositories, data sources remotos e locais.

## Funcionalidades implementadas

- Login com autenticacao e uso de token em requests autenticadas.
- Cadastro e atualizacao de usuario (incluindo endereco).
- Listagem de produtos.
- Cadastro, edicao e exclusao de produtos.
- Adicao de produto ao carrinho e atualizacao de quantidades.
- Finalizacao da compra com gravacao de pedido local.
- Lista de pedidos salvos.
- Favoritos com persistencia local.
- Cadastro e selecao de enderecos locais.
- Listagem, edicao e remocao de usuarios (perfil admin).

## Arquitetura e stack tecnica

- Kotlin + Android Views (ViewBinding e DataBinding).
- MVVM com ViewModel e LiveData.
- Repositories para orquestrar fontes de dados remotas e locais.
- Injecao de dependencia com Hilt.
- Persistencia local com Room.
- Comunicacao HTTP com Retrofit + OkHttp + Gson.

## Tecnologias e versoes

- compileSdk 35, targetSdk 35, minSdk 29.
- Kotlin 2.0.21.
- AGP 8.7.3.
- Java 11 (source/target).
- Hilt 2.52.
- Room 2.6.1.
- Retrofit 2.11.0.
- OkHttp 4.12.0.
- Coroutines 1.8.1.
- JUnit 4.13.2 e MockK 1.13.12.

## Estrutura resumida

```text
app/src/main/java/com/tadeujeronimo/deliveryapp
|- data
|  |- local        # Room (database, dao, entity, datasource)
|  |- remote       # data sources remotos
|  |- repositories # regra de negocio e acesso a dados
|  |- service      # interfaces Retrofit
|  |- util         # interceptor, shared preferences, converter
|- di              # modulos Hilt
|- ui              # Activities, adapters, menu e viewmodels
|- DeliveryApplication.kt
```

## Configuracao da API

A base URL atual esta definida em:

`app/src/main/java/com/tadeujeronimo/deliveryapp/di/ApiModule.kt`

Valor configurado no projeto:

```kotlin
http://10.0.2.2:3000/
```

Se o backend estiver em outro host/porta, altere esse valor antes de executar.

## Como executar

1. Abra o projeto no Android Studio.
2. Aguarde o sync do Gradle.
3. Ajuste a base URL da API, se necessario.
4. Execute em emulador ou dispositivo fisico.

### Comandos uteis

Build debug:

```bash
./gradlew assembleDebug
```

Executar testes unitarios:

```bash
./gradlew test
```

Testes instrumentados (quando aplicavel):

```bash
./gradlew connectedAndroidTest
```

## APK para entrega

Gerar APK de debug:

```bash
./gradlew assembleDebug
```

Arquivo gerado:

- app/build/outputs/apk/debug/app-debug.apk

Opcional: gerar APK de release:

```bash
./gradlew assembleRelease
```

Arquivo gerado (sem assinatura):

- app/build/outputs/apk/release/app-release-unsigned.apk

Versao atual do app (definida no Gradle):

- versionName: 1.0
- versionCode: 1

## Testes unitarios

Existem testes em `app/src/test` cobrindo:

- Data sources remotos (login, produto, carrinho e usuario).
- Data sources locais (address, order e favoritos).
- Repositories (login, produto, carrinho, usuario, address e order).

## Permissoes Android

- android.permission.INTERNET
- android.permission.ACCESS_NETWORK_STATE

## Licenca

[GNU](LICENSE)
