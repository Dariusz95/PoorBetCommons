# CLAUDE.md

Ten plik zawiera wskazówki dla Claude Code podczas pracy z tym repozytorium.

## Cel

To jest biblioteka autokonfiguracji Spring Boot używana przez główną aplikację (`PoorBetApplication`).

Nie jest to samodzielny serwis — nie posiada klasy `main`, nie uruchamia własnego serwera (brak embedded serwera).

## Jak testować lokalnie
mvn install
Następnie w (`PoorBetApplication`) zależność jest automatycznie pobierana z lokalnego repozytorium Maven (~/.m2)


## Język komunikacji

Zawsze odpowiadaj w języku polskim.

Cała dokumentacja, komentarze do kodu, opisy zmian, propozycje architektoniczne, komunikaty commitów oraz przykłady kodu powinny być przygotowywane w języku polskim, chyba że użytkownik wyraźnie poprosi o użycie innego języka.

## Przegląd projektu

`poorbet-commons` jest współdzieloną biblioteką Java (Spring Boot 3.4.1, Java 21) wykorzystywaną przez mikroserwisy platformy PoorBet.

Biblioteka publikowana jest do GitHub Packages i stanowi centralne miejsce przechowywania:

* zdarzeń RabbitMQ,
* kontraktów komunikacyjnych między serwisami,
* współdzielonych DTO,
* kodów błędów,
* komponentów infrastrukturalnych wykorzystywanych przez wiele mikroserwisów.

To repozytorium nie zawiera aplikacji uruchamialnej.

Artefakt publikowany jest jako zwykły plik JAR.

## Powiązane repozytoria

Projekt PoorBet składa się z kilku repozytoriów:

* poorbet-app – główna aplikacja mikroserwisowa
* poorbet-auth-starter – współdzielona autokonfiguracja Spring Security
* poorbet-commons – współdzielone kontrakty i infrastruktura komunikacyjna (to repozytorium)

Zmiany w tym repozytorium mogą wymagać aktualizacji wielu mikroserwisów jednocześnie.

Szczególną ostrożność należy zachować podczas modyfikowania:

* eventów RabbitMQ,
* DTO wykorzystywanych między serwisami,
* ErrorCode,
* EventRegistry.

## Komendy

```bash
# Budowanie i instalacja w lokalnym repozytorium Maven
mvn install

# Budowanie bez uruchamiania testów
mvn install -DskipTests

# Uruchomienie testów
mvn test

# Publikacja do GitHub Packages
# Wymaga ustawionej zmiennej środowiskowej GITHUB_TOKEN
mvn --batch-mode deploy

# Czyszczenie katalogu build
mvn clean
```

## Architektura

Biblioteka składa się z dwóch niezależnych modułów logicznych.

---

## 1. com.poorbet.commons.rabbit

Infrastruktura komunikacji RabbitMQ.

### EventDefinition<T>

Rekord opisujący zdarzenie.

Przechowuje:

* exchange
* eventType
* version

Routing key wyliczany jest automatycznie według schematu:

```text
eventType.version
```

### EventEnvelope<T>

Wspólny format przesyłania zdarzeń między mikroserwisami.

Zawiera:

* eventId
* eventType
* version
* source
* payload

`eventId` to unikalny identyfikator konkretnej publikacji zdarzenia — wykorzystywany przez konsumentów do idempotencji (tabela `processed_event`, sprawdzana przed przetworzeniem, żeby ta sama dostawa RabbitMQ nie została przetworzona dwa razy). Dla zdarzeń publikowanych przez Outbox Pattern jako `eventId` używane jest `id` rekordu `outbox_event`; dla publikacji bezpośrednich (`RabbitDomainEventPublisher`) generowany jest nowy losowy UUID przy każdym wywołaniu `publish()`.

### EventKey

Enum zawierający identyfikatory wszystkich zdarzeń dostępnych w systemie.

Każde nowe zdarzenie musi posiadać odpowiadającą mu wartość w EventKey.

### EventRegistry

Centralny rejestr mapujący:

```text
EventKey → EventDefinition
```

Rejestr stanowi źródło prawdy dla wszystkich zdarzeń obsługiwanych przez platformę.

### MessagingConfigurationValidator

Uruchamiany podczas:

```java
ApplicationReadyEvent
```

Waliduje zgodność konfiguracji:

```yaml
messaging.consumers
```

z definicjami znajdującymi się w EventRegistry.

Jeżeli konfiguracja nie jest zgodna, aplikacja kończy uruchamianie z wyjątkiem:

```java
IllegalStateException
```

Mechanizm ten wymusza jawne deklarowanie przez mikroserwisy, które zdarzenia chcą konsumować.

### Autokonfiguracje

Biblioteka dostarcza:

* MessagingAutoConfiguration
* EventRegistryAutoConfiguration

Zarejestrowane w:

```text
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

### Zdarzenia domenowe

Zdarzenia pogrupowane są według domen.

#### AuthEvents

Exchange:

```text
auth.events
```

#### WalletEvents

Exchange:

```text
wallet.events
```

#### CouponEvents

Exchange:

```text
coupon.events
```

#### MatchEvents

Definicja exchange znajduje się w:

```text
MatchEvents.java
```

---

## 2. com.poorbet.commons.commons

Współdzielone kontrakty HTTP i API.

### ErrorCode

Enum zawierający standardowe kody błędów używane przez wszystkie mikroserwisy.

Nowe błędy powinny być dodawane wyłącznie wtedy, gdy mogą zostać wykorzystane przez więcej niż jeden serwis.

### ErrorResponse

Standardowa odpowiedź błędu.

Zawiera pola:

```text
code
message
timestamp
```

### ReserveRequest

DTO wykorzystywane do rezerwacji środków w portfelu.

Wykorzystuje walidację Jakarta Validation.

## Dodawanie nowego zdarzenia

W przypadku dodawania nowego eventu należy wykonać wszystkie poniższe kroki:

### Krok 1

Dodaj nową wartość do:

```java
EventKey
```

### Krok 2

Utwórz payload zdarzenia w odpowiednim pakiecie:

```text
events/<domena>/
```

### Krok 3

Dodaj nową stałą:

```java
EventDefinition<YourEvent>
```

do odpowiedniej klasy:

```text
AuthEvents
WalletEvents
CouponEvents
MatchEvents
```

### Krok 4

Zarejestruj event w:

```java
EventRegistry
```

### Krok 5

Każdy mikroserwis konsumujący zdarzenie musi dodać odpowiednią konfigurację:

```yaml
messaging:
  consumers:
```

W przeciwnym razie aplikacja nie uruchomi się poprawnie.

## Publikacja

Workflow:

```text
workflows/publish-poorbet-commons.yml
```

uruchamia:

```bash
mvn deploy
```

po każdym pushu do gałęzi:

```text
main
```

Należy zweryfikować lokalizację pliku workflow.

Jeżeli CI/CD nie uruchamia się automatycznie, sprawdź czy plik znajduje się w:

```text
.github/workflows/
```

a nie w:

```text
workflows/
```

## Kluczowe zasady projektowe

### Stabilność kontraktów

Ta biblioteka definiuje kontrakty pomiędzy mikroserwisami.

Zmiany w publicznych DTO, eventach lub kodach błędów mogą wymagać jednoczesnych zmian w wielu serwisach.

Należy unikać niekompatybilnych zmian.

### Kompatybilność wsteczna

Jeżeli to możliwe:

* nie usuwaj istniejących pól eventów,
* nie zmieniaj nazw eventType,
* nie usuwaj wartości EventKey,
* nie usuwaj używanych ErrorCode.

W przypadku większych zmian preferowane jest wersjonowanie eventów.

### Brak logiki biznesowej

Repozytorium powinno zawierać wyłącznie:

* kontrakty komunikacyjne,
* DTO,
* eventy,
* konfigurację infrastrukturalną,
* komponenty współdzielone.

Nie należy dodawać:

* logiki domenowej,
* serwisów biznesowych,
* endpointów REST,
* encji JPA,
* migracji Flyway.

```
```
