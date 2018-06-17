package com.pkproject.internetcourse.application.datebase;

/**
 * Created by dominikzaq on 22.01.2017.
 */
public class Text {
    public static String networkBasic = "Podstawowe Pojęcia\n" +
            "Definicja sieci komputerowej mówi, że jest to połączenie przynajmniej dwóch komputerów w celu wymiany danych poprzez media transmisyjne wykorzystując odpowiednie protokoły komunikacyjne.\n" +
            "Obecnie definicję tą trzeba rozszerzyć, ponieważ zwiększyła się liczba urządzeń podłączanych do sieci i korzystających z niej. Dziś do sieci komputerowej podłączamy nie tylko komputery czy serwery, ale również telefony komórkowe, tablety, telewizory, a nawet pralki, lodówki oraz inne urządzenia AGD. Oczywiście podstawa działania sieci komputerowych się nie zmieniła, dalej tworzy się sieci aby udostępniać dane (pliki, strony WWW, usługi sieciowe) oraz zasoby sprzętowe (drukarki, dyski, skanery, napędy optyczne), natomiast różnica polega na tym, że korzysta z niej o wiele urządzeń niż kiedyś.\n" +
            "Pojęcia związane z siecią komputerową:\n" +
            "Host - urządzenie końcowe sieci komputerowej, stanowiące źródło lub celu przesyłania danych w sieci.\n" +
            "Serwer – komputer, na którym zainstalowane jest specjalistyczne oprogramowanie, oferujący usługi innym komputerom (WWW, poczta elektroniczna, zasoby plikowe)\n" +
            "Klient – komputer korzystający z usług udostępnianych przez serwer\n" +
            "Klient – Serwer – architektura sieci komputerowej, w której występuje komputer udostępniający usługi (serwer) oraz komputery korzystające z tych usług (klienci)\n" +
            "Peer to Peer (P2P) – architektura sieci komputerowej, w której nie występuje jeden komputer udostępniający usługi, wszystkie komputery w tej sieci działają na równorzędnych prawach\n" +
            "Medium transmisyjne – element sieci komputerowej, za pomocą którego komunikują się urządzenia w sieci, może nim być kabel miedziany, światłowodowy, jak również fale radiowe (WiFi)\n" +
            "Protokół komunikacyjny – określony sposób (język) komunikacji, dzięki któremu możliwa jest wymiana danych pomiędzy urządzeniami w sieci\n" +
            "LAN (ang. Local Area Netwok) – lokalna sieć komputerowa, obejmująca swoim zasięgiem pomieszczenie, piętro, budynek lub zbiór budynków\n" +
            "MAN (ang. Metropolitan Area Netwok) – miejska sieć komputerowa, obejmująca swoim zasięgiem miasto lub aglomerację miejską\n" +
            "WAN (ang. Wide Area Netwok) – rozległa sieć komputerowa, komunikująca ze sobą odległe sieci LAN.\n" +
            "Topologia fizyczna sieci – określa sposób połączenia ze sobą komputerów w sieci\n" +
            "Topologia logiczna sieci – określa sposób komunikowania ze sobą komputerów w sieci\n" +
            "Karta sieciowa (ang. NIC – Network Interface Card) – adapter instalowany w urządzaniach (komputerach, telefonach, itp.), które podłączane są do sieci komputerowej, dzięki któremu możliwy jest przesyła danych\n" +
            "Ruter (ang. Router) – urządzenie sieciowe, którego głównych zadaniem jest łączenie ze sobą różnych sieci, w celu umożliwienia im wzajemnej komunikacji oraz określanie ścieżki przepływu danych pomiędzy sieciami na podstawie adresu IP (ang. Routing)\n" +
            "Przełącznik (ang. Switch) – urządzenie sieciowe, które łączy urządzenia w sieci oraz decyduje o przesyle danych pomiędzy urządzaniami na podstawie adresu MAC\n" +
            "Adres IP – logiczny adres interfejsu urządzenia w sieci komputerowej, na podstawie którego rutery dokonują przesyłu danych\n" +
            "Adres MAC – fizyczny (sprzętowy) adres karty sieciowej urządzenia, nadawany przez producenta na etapie produkcji, na podstawie którego przełączniki przesyłają dane w sieci LAN\n" +
            "Internet (Intersieć)– siatka połączonych ze sobą sieci rozległych, umożliwiająca komunikację pomiędzy nimi\n" +
            "Intranet – prywatna sieć, wykorzystująca w komunikacji standardy sieci Internet, takie jak WWW, FTP czy POP3 i SMTP, do której dostęp mają tylko upoważnieni użytkownicy (np. do Intranetu w firmie XYZ mają dostęp tylko pracownicy tej firmy)\n" +
            "Extranet – rozszerzenie sieci prywatnej (Intranet), umożliwiające dostęp do jej zasobów również dla innych użytkowników\n" +
            "VPN – prywatna sieć, do zasobów której możemy się dostać przez sieć Internet, wykorzystując tzw. tunelowy kanał transmisji danych.\n" +
            "DNS (ang. Domain Name System) – system zmieniający nazwę mnemoniczną (zrozumiałą dla człowieka), np. onet.pl na odpowiadający jej adres IP w sieci.\n" +
            "\n" +
            "\n" +
            "\n" +
            "Komunikacja pomiędzy urządzeniami w sieciach komputerowych, jak każdy inny typ komunikacji rządzi się określonymi prawami i regułami. Reguły te określa się mianem protokołów komunikacyjnych. Różnią się one pomiędzy sobą w zależności od rodzaju komunikacji. Dokładnie tak samo jak w życiu codziennym, inny rodzaj zasad rządzi przy połączeniach telefonicznych, inny przy przekazywaniu informacji poprzez popularne komunikatory.\n" +
            "Aby w sieciach komputerowych komunikacja przebiegała we właściwy sposób wymagane jest współdziałanie wielu protokołów, które określa się mianem zestawu protokołów. Na zestaw taki składają się zarówno składniki programowe, jak również urządzenia sieciowe. Najbardziej efektywną metodą wizualizacji współdziałania protokołów jest postrzeganie ich jako nałożone na siebie warstwy stanowiące swojego rodzaju stos. Stosowanie takiej metody pozwala na rozbicie skomplikowanego zagadnienia jakim jest komunikacja w sieci na mniejsze elementy, w celu prostszego ich opisania i zrozumienia. Ponadto stosowanie modelu warstwowego niesie za sobą szereg innych korzyści, takich jak łatwiejsze projektowanie protokołów czy możliwość współdziałania urządzeń sieciowych rożnych producentów.\n" +
            "Istnieją dwa podstawowe modele odniesienia: model OSI oraz model TCP/IP.\n" +
            "\n" +
            "Model TCP/IP powstał w latach siedemdziesiątych XX wieku wieku i określany jest mianem modelu sieci Internet. Składa się z czterech warstw:\n" +
            "warstwy aplikacji, zajmującej się reprezentacją danych dla użytkownika oraz ich kodowaniem,\n" +
            "warstwy transportowej, zapewniającej komunikację pomiędzy różnymi urządzeniami w sieci,\n" +
            "internet, zapewniający najlepszą trasę dla przepływu pakietów,\n" +
            "warstwy dostępu do sieci kontrolującej urządzenia fizyczne i media.\n" +
            "Model OSI jest modelem przygotowanym z myślą o tworzeniu tzw. „otwartego systemu”, który nie będzie należał do żadnej zamkniętej sieci. Szybki rozwój sieci Internet opartej na modelu TCP/IP spowolnił jednak znacznie jego wdrażanie. Mimo tego udało się stosując model OSI wdrożyć kilka istotnych protokołów. W przeciwieństwie do modelu TCP/IP, składa się on z siedmiu warstw:\n" +
            "warstwy aplikacji, umożliwiającej komunikację z użytkownikiem (strony WWW, poczta elektroniczna),\n" +
            "warstwy prezentacji, zajmującej się konwersją danych i definiowaniem formatu (dzięki temu dane pochodzące z urządzenia źródłowego mogą być interpretowane przez odpowiednie aplikacje na urządzeniu docelowym)\n" +
            "warstwy sesji, zarządzającej przebiegiem komunikacji,\n" +
            "warstwy transportowej, odpowiedzialnej za integralność (czyli spójność) transmisji danych,\n" +
            "warstwy sieci, zajmującej się określaniem trasy przepływu danych,\n" +
            "warstwy łącza danych, opisującej metody wymiany ramek pomiędzy urządzeniami podłączonymi do tego samego medium (np. do przełącznika),\n" +
            "warstwy fizycznej, sterującej przepływem bitów poprzez media sieciowe.\n" +
            "Protokoły aplikacji\n" +
            "Protokoły aplikacji – obejmują warstwy aplikacji, prezentacji i sesji. Służą do wzajemnego oddziaływania aplikacji i wymiany danych. Najpopularniejsze protokoły aplikacji to:\n" +
            "APPC (Advanced Program-to-program Communication) lub inaczej LU 6.2 firmy IBM;\n" +
            "Virtalny Terminal OSI: FTAM (File Transfer Access and Mangement), DTP (Distrubuted Transaction Procesing, X.400 (Message Handling System) oraz X.500 (Directory Services);\n" +
            "Internetowe i Unixowe sieciowe systemy plików: SMTP (Simple Mail Transfer Protocol), FTP (File Transfer Protocol), Telnet oraz SNMP (Simple Network Management Protocol);\n" +
            "NCP (Novell NetWare Network Core Protocol);\n" +
            "Microsoft Server Message Blocks: NetBios;\n" +
            "AppleTalk Apple Share: AFP (Apple Talk Filing Protocol), ADSP (Apple Talk Data Stream Protocol), ASP (Apple Talk Session Protocol), PAP (Printer Acess Protocol), oraz ZIP (Zone Information Protocol).\n" +
            "Protokoły transportowe\n" +
            "Protokoły transportowe – służą do przesyłania danych w sieciach, zapewniają wymianę danych pomiędzy systemami końcowymi, w których systemy te utrzymują sesję lub połączenia zapewniając stałą, sekwencyjną wymianę danych. Przykładowe protokoły to:\n" +
            "APPN (Advanced Peer-to-Peer Networking) firmy IBM;\n" +
            "TCP (Transmission Control Protocol), część zestawu protokołów TCP/IP;\n" +
            "SPX, część zestawu protokołów SPX/IPX;\n" +
            "Interfejsy Microsoft NetBIOS i NetBEUI;\n" +
            "OSI COTS (Connection-Oriented Transport Service), CLTS (Connectionless Transport Service);\n" +
            "AppleTalk RTMP (Rounting Table Maintenance Protocol), AEP (AppleTalk Echo Protocol), ATP (AppleTalk Transaction Protocol), NBP (Name Binding Protocol).\n" +
            "Protokoły sieciowe\n" +
            "Protokoły sieciowe – zapewniają usługi łączy systemów komunikacyjnych, obsługują adresowanie, informacje routingu, weryfikację błędów oraz żądania retransmisji. Obejmują także procedury dostępu do sieci, określone przez wykorzystywany rodzaj sieci. Przykładowe protokoły to:\n" +
            "TCP/IP Protokół sieciowy – a ściślej zestaw protokołów – stosowany w sieci Internet; najczęściej posługują się nim systemy uniksowe, choć można go również stosować z Novell NetWare, Windows NT itp. TCP/IP jest bardziej podatny na naruszenia systemu bezpieczeństwa, z powodu swojej otwartej, „ufnej natury”. Jego zadanie polega na podzieleniu informacji na odpowiedniej wielkości pakiety, ponumerowaniu ich, tak aby u odbiorcy można było sprawdzić, czy wszystkie pakiety nadeszły, i ustawić je w odpowiedniej kolejności.\n" +
            "\n" +
            "Adresacja IP\n" +
            "Adresacja stanowi niezwykle ważny element w działaniu sieci komputerowych ponieważ na jej podstawie odbywa się dostarczanie informacji. Kiedy listonosz dostarcza nam list, dokonuje tego na podstawie naszego adresu zamieszania, w sieciach komputerowych natomiast, aby dostarczyć pakiety do odpowiedniego hosta potrzebny jest inny adres, nazywamy adresem IP. Stanowi on kluczowy element w funkcjonowaniu protokołów warstwy sieciowej. W tym artykule omówimy sobie, w jaki sposób działa adresowanie IP w wersji 4.\n" +
            "Adres IP jest adresem logicznym interfejsu sieciowego (hosta) to znaczy, że nie musi to być tylko adres konkretnego komputera, może to być również adres interfejsu (interfejsów) rutera czy access pointa.\n" +
            "Każdy adres IPv4 składa się z 32-bitowego ciągu zer i jedynek. Wynika to z faktu, że działanie urządzeń w sieciach komputerowych oparte jest na logice cyfrowej, co oznacza, że adresy te interpretowane są jako liczby binarne. Dla prostszego stosowania adresacji i zapamiętywania adresów, na co dzień stosuje się jednak zapis dziesiętny. Zapis ten składa się z 4 części (każda z nich to 1 bajt czyli 8 bitów), zwanych oktetami.\n" +
            "Przykład:\n" +
            "adres IP w zapisie dziesiętnym 192.112.20.101 będzie miał postać binarną równą 11000000.01110000.00010100.01100101\n" +
            "W każdym adresie IP, pewna część bitów (liczona od lewej strony), reprezentuje adres sieci, reszta natomiast stanowi adres konkretnego hosta. Jest to logiczne ponieważ pakiet najpierw musi trafić do właściwej sieci, dopiero potem trafia do konkretnego hosta. Jak w życiu, list najpierw trafia do miasta, dopiero potem pod wskazany numer domu na konkretnej ulicy. Jaka część bitów przeznaczona jest na adres sieci, a jaka na adres hosta określone jest przez tzw. maskę podsieci, która to określaja wielkość sieci oraz liczbę hostów w niej funkcjonujących.\n" +
            "Zanim przejdziemy do omówienia maski, musimy jeszcze określić typy adresów, które związane są z każdą siecią, są nimi:\n" +
            "adres sieciowy (ang. network address) -adres, który określa całą sieć,\n" +
            "adres rozgłoszeniowy (ang. broadcast address) – specjalny adres używany w celu wysyłania danych do wszystkich hostów w określonej sieci,\n" +
            "adres hosta (interfejsu urządzenia końcowego) – adres przyporządkowany urządzeniu końcowemu pracującemu w sieci.\n" +
            "Podział ten będzie nam potrzebny aby we właściwy sposób móc obliczać adresy IP.\n" +
            "Wróćmy teraz do naszej maski podsieci, która to określa, jaka część adresu IP określa sieć, a jaka hosty. Podobnie jak sam adres IP, również maska zapisana jest w postaci 32-bitowej i przez urządzenia sieciowe interpretowana jest jako liczba binarna, natomiast w zapisie oraz konfiguracji urządzeń stosuje się zapis dziesiętny, podzielony na 4 oktety. Przykładowa maska podsieci może mieć postać 255.255.255.0 dziesiętne czyli 11111111111111111111111100000000 binarnie. Czasami też możemy spotkać się z tzw. skróconym zapisem maski  (np. /24), określającym, ile jest jedynek w binarnym zapisie maski. My będziemy stosować oba te zapisy.\n" +
            "W jaki sposób interpretować zapis binarny, a co za tym idzie jak określić, która część adresu jest adresem sieci, a która hosta? Bardzo prosto: jedynki określają sieć, a zera hosty należące do tej sieci.\n" +
            "\n" +
            "Adres rozgłoszeniowy Broadcast:\n" +
            "Adres do transmisji danych polegający na wysyłaniu przez jeden port pakietów, które mają zostać odebrane przez wszystkie pozostałe porty w danej sieci.\n" +
            "W celu jego wyliczenia potrzebujemy adresu IP oraz maski. Musimy wykonać działanie OR między adresem IP, a zanegowaną maską.\n" +
            "Adres IP\n" +
            "Maska            OR\n" +
            "Adres rozgłoszeniowy\n" +
            " \n" +
            "Pierwszy dostępny adres:\n" +
            "Identyfikuje pierwszy dostępny adres w podsieci.\n" +
            "W celu jego obliczenia potrzebujemy adres podsieci, a następnie dodajemy do niego wartość 1 .\n" +
            "Adres podsieci\n" +
            "0.0.0.1               +\n" +
            "Pierwszy dostępny adres\n" +
            " \n" +
            "Ostatni dostępny adres:\n" +
            "Identyfikuje pierwszy dostępny adres w podsieci.\n" +
            "W celu jego obliczenia potrzebujemy adres rozgłoszeniowy broadcast, a następnie odejmujemy od niego wartość 1 .\n" +
            "Adres rozgłoszeniowy\n" +
            "0.0.0.1               -\n" +
            "Ostatni dostępny adres\n" +
            " \n" +
            " \n" +
            "Liczność podsieci:\n" +
            "Wskazuje na ilość podsieci w danej sieci.\n" +
            "W celu jego obliczenia potrzebujemy znać klasę sieci, adres IP oraz maskę. Wyliczamy liczbę „pożyczonych” bitów (LPB). Liczność podsieci otrzymujemy poprzez podniesienie liczby 2 do potęgi liczby pożyczonych bitów(LPB).\n" +
            "2LPB = Liczność podsieci\n" +
            " \n" +
            "Ilość hostów w podsieci:\n" +
            "Wskazuje na ilość dostępnych adresów dla hostów w sieci.\n" +
            "W celu jego obliczenia potrzebujemy znać liczność sektora(LS) lub pierwszy(P) i ostatni(O) adres w podsieci.\n" +
            "Pierwszym sposobem na otrzymanie ilości hostów w podsieci jest pomniejszenie wartości liczności sektora o 2.\n" +
            "Drugim sposobem jest policzenie adresów między pierwszym i ostatnim adresem w podsieci, wliczając w to je same.\n" +
            "1: LS-2 = ilość hostów\n" +
            "2: O-P+1 = ilość hostów\n" +
            "\n" +
            "Pakiet Tracer\n" +
            "Aplikacja Cisco pozwala zająć się dość szerokim wachlarzem urządzeń sieciowych, takich jak routery (dostępne są: 1841, 1941, 2620XM, 2621XM, 2811, 2901, 2911, 819HGW) czy przełączniki (2950-24, 2950T-24, 2960-24TT). Mamy dostępną dużą ilość urządzeń klienckich (m.in. PC, laptopy, serwery, drukarki, telefony VoIP/analogowe, tablety, telefony itd.). Możemy symulować działanie Internetu za pomocą emulacji WAN. Można także tworzyć sieci bezprzewodowe (mamy dostępny jeden router WI-FI – WRT300N). Możliwe jest tworzenie własnych konfiguracji urządzeń. Do tego służą tzw. urządzenia „Generic” Generic AccessPoint-PT, Generic-Router-PT itd. Możemy projektować sieć zarówno logicznie, jak i fizycznie (ten drugi aspekt jest wg mnie trochę zubożony, ale ta opcja się czasem przydaje). Program posiada także wiele innych elementów, o których nie będę w tej chwili wspominał, ale być może napiszę o nich w którymś z moich przyszłych wpisów. \n" +
            "Packet Tracer tworzy w pełni wirtualne środowisko. Ma to swoje zalety, ale posiada także wady. Przede wszystkim, dzięki temu Packet Tracer nie obciąża zbyt bardzo naszego, czasem już trochę leciwego komputera. Możemy ustawić nawet kilkanaście routerów i kilkaset stacji roboczych, i nie odbije się to na wydajności całego systemu. Jednakże, jak podałem wcześniej, program był tworzony głównie pod studentów CCNA. Routery zawarte w Packet Tracerze nie udostępniają pełni swoich możliwości konfiguracyjnych. Aby to jakoś zobrazować, podam prosty przykład (nie bój się, że do końca nie rozumiesz o co w nim chodzi, to zagadnienie omówię dokładnie później) – każdy router Cisco może pracować jako serwer DHCP. Jak wiemy, przydziela on adresy IP na pewien okres czasu. Proces ten to dzierżawa adresu IP. Podczas konfiguracji podajemy polecenie lease 5 12 45. Co się dzieje? Błąd? Dlaczego? Po prostu Packet Tracer nie wspiera tego, jak i kilkunastu innych poleceń i trzeba się z tym pogodzić. \n" +
            "\n" +
            "Pierwsza prosta sieć – dodawanie urządzeń i wymiana modułów\n" +
            "Powoli zbliżamy się do końca niniejszego wpisu. Stworzymy prostą sieć, złożoną z dwóch komputerów (wiem, to banał ale każdy kurs należy zacząć od podstaw ?). Więc zaczynamy:\n" +
            "Aby dodać komputer, klikamy kategorię End Devices w lewym dolnym rogu gdzie mamy routers. Potem przeciągamy komputer (PC-PT) na obszar roboczy znajdujący się pośrodku. Tak samo dodajemy drugi komputer. Jak sobie poradziłeś, możemy je połączyć kablem. Urządzenia tego samego typu (komputer-komputer, switch-switch, router-router) łączy się tzw. kablem krosowym (więcej o tym w następnej części). Przechodzimy więc do kategorii Connections i wybieramy Copper Cross-Over. Najpierw klikamy pierwszy komputer, wybieramy FastEthernet0, potem klikamy na drugi komputer i też wybieramy gniazdo FastEthernet0.\n" +
            "Połączenie powinno zaświecić się na zielono, ale to jeszcze nie znaczy, że odnieśliśmy sukces!. Należy jeszcze skonfigurować adresy IP. \n" +
            "Klikamy dwukrotnie na komputer PC0. Powinno otworzyć się okno konfiguracji. Mamy tutaj listę modułów, które możemy dołączyć do danego urządzenia. Po wybraniu modułu, jego opis pojawia się w miejscu. Zakładka Config pozwala nam m.in. na konfigurację adresów IP czy bramy domyślnej. Możemy także zmienić np.: MAC adres karty sieciowej obecnej w danym komputerze. \n" +
            "Zakładka Desktop to istny system operacyjny. Mamy tam dostępne różne „aplikacje” które pozwalają wykonywać różne operacje. Możemy między innymi przeprowadzić konfigurację IP, VPN, przeglądać wirtualny Internet za pomocą przeglądarki internetowej, pobawić się w wirtualnym cmd (konsoli znakowej) czy wysyłać/odbierać e-maile. \n" +
            "Wykonajmy więc konfigurację sieci na obydwóch komputerach. Przejdźmy na zakładkę Desktop, a potem uruchommy IP Configuration. Wybieramy statyczną konfigurację sieci (Static). Jako adres IP podaj 192.168.1.1. Kliknij pole Subnet Mask, które powinno się teraz uzupełnić samo. Pozostałych parametrów na razie nie ruszamy. Ich znaczenie opiszę następnym razem.\n" +
            "Tak samo robimy na PC1, z tym, że tam ustalamy adres 192.168.1.2. Po zakończonej konfiguracji przydałoby się sprawdzić, czy nasza nowa „sieć” działa poprawnie. W tym celu kliknij ikonę „zamkniętej koperty” z menu po prawej stronie. Potem kliknij na PC0, a następnie na PC1. Powinien zostać wysłany pakiet ICMP. (czyli po prostu ping). W menu scenariuszy pojawi się wynik naszych działań. Jeśli w polu Status widzimy „Successful” to znaczy, że wszystko się udało i możesz być z siebie dumny. Jeśli nie, to znaczy, że popełniłeś gdzieś błąd. Dla 100% pewności sprawdźmy też ruch w drugą stronę. Wybieramy ikonę „zamkniętej koperty”, potem klikamy na PC1 a następnie na PC0. W tym wypadku również wynikiem powinno być słynne Successful.\n" +
            "To by było na tyle jeśli chodzi o Kurs\n";


    public static String networkAdvanced = "OSPF\n"+
            "OSPF to typu łącze – stan (link-state). Tworzeniem protokołu zajęła się w 1987 roku grupa robocza organizacji IETF. W 1989 roku opublikowano specyfikację protokołu OSPFv1 w dokumencie RFC 1131. Zapisano dwie implementacje: jedna miała działać na routerach, a druga na uniksowych stacjach roboczych. Ta druga stała się później powszechnym znanym procesem uniksowym o nazwie GATED OSFPv1.  W 1991 roku John Moy wprowadził w dokumencie RFC 1247 OSFPv2. Pojawiły się tam istotne ulepszenia techniczne niż u poprzednika. W 1998 roku w dokumencie RFC 2323 pojawiła się specyfikacja OSPFv2, która obowiązuje do dzisiaj. W 1999 roku opublikowano dokument RFC 2749, w którym opisano protokół OSPFv3 przeznaczony dla sieci IPv6.\n"+
            "TYPY PAKIETÓW OSPF:\n"+
            "(1) Hello – pakiety hello służą do tworzenia i podtrzymywania przyległości z innymi routerami OSPF.\n"+
            "(2) DBD (database description) – pakiet zawiera skróconą liste bazdy danych łącze stan routera wysyłającego i jest używany przez odbierające routery do sprawdzenia lokalnej bazy danych łącze-stan (link-state).\n"+
            "(3) LSR  (link stare request) – żądanie routera o udzielenie dodatkowych informacji o dowolnym wpisie z DBD.\n"+
            "(4) LSU (link state update) – pakiet aktualizacji, który jest odpowiedzią na żądanie LSR.\n"+
            "(5) LSack – potwierdzenie odebrania pakietu LSU.\n"+
            "Typy pakietów LSU:\n"+
            "(1) LSA routera,\n"+
            "(2) LSA sieci,\n"+
            "(3) lub (4) LSA z podsumowaniem,\n"+
            "(5) Zewnętrzne LSA systemu autonomicznego,\n"+
            "(6) Grupowe pakiety LSA OSPF,\n"+
            "(7) Zdefiniowanie dla obszarów niezbyt szczątkowych,\n"+
            "(8) LSA z zewnętrznymi atrybutami dla prokołu BGP,\n"+
            "(9), (10), (11) LSA nieprzeźroczyste.\n"+
            "Każdy z wysyłanych przez protokół OSPF komunikatów posiada nagłówek OSPF składający się z następujących pól:\n"+
            "- typ pakietu OSPF,\n"+
            "- identyfikator routera: identyfikator początkowego routera.\n"+
            "- identyfikator obszaru: obszar, z którego pakiet wyruszył w podróż.\n"+
            "SPOSÓB DZIAŁANIA PROTOKOŁU OSPF:\n"+
            "Dowiadywanie się o sieciach połączonych bezpośrednio (adjency):\n"+
            "Gdy zostaną prawidłowo skonfigurowane i aktywowane inferfejsy, router dowiaduję się o własnych sieciach połączonych bezpośrednio.  Po skonfigurowaniu interfejsu jednym z poleceń network router rozpoczyna brać udział w działaniu protokołu, którego działanie zostało opisane poniżej:\n"+
            "ETAP (I)\n"+
            "PAKIETY HELLO\n"+
            "Router wysyła pakiety hello w celu nawiązania sąsiedztwa z przyległymi bezpośrednio do niego routerami. Pakiety te wysyłane są domyślnie co 10 sekund na specjalnie zarezerwowany do tego celu adres multicastowy 224.0.0.5 (wszystkie routery OSPF). Ustanowienie 2 – kierunkowej komunikacji jest pierwszym etapem nawiązania relacji sąsiedztwa. Po jej nawiązaniu komunikaty Hello pełnią rolę „keepalive” co pozwala na stwierdzenie czy komunikacja w dalszym ciągu jest utrzymywana. Zanim dwa routery będą mogły ustanowić przyległość OSPF, oba interfejsy na dwóch routerach muzą być częścią tej samej sieci, muszą mieć również taką samą maskę podsieci.\n"+
            "Etapy nawiązywania sąsiedztwa\n"+
            "1) Down – relacja sąsiedztwa po włączeniu protokołu\n"+
            "2) Init – odebranie pakietu Hello od sąsiada. (bez potwierdzenia dwukierunkowej komunikacji)\n"+
            "3) 2-Way – otrzymanie od sąsiada pakietu wraz ze swoim ID co oznacza, że dwukierunkowa komunikacja została nawiązana. W tym stanie dokonany zostaje wybór na DR I BDR. Po osiągnięciu tego stanu Routery mogą rozpocząć synchronizację pakietów LSA\n"+
            "4) ExStart – Router z większym ID zostaje wybrany jako master, a drugi jako slave. Ten pierwszy rozpocznie synchronizację pakietów LSA\n"+
            "Każdy pakiet hello składa się z następujących pól:\n"+
            "-nagłówek komunikatu OSPF,\n"+
            "- maska podsieci interfejsu wysyłającego,\n"+
            "- interwał hello: liczba sekund pomiędzy pakietami hello routera wysyłającego.\n"+
            "- priorytet routera: używany przy wybieraniu Designated Router (DR) oraz Backup Designated Router (BDR). Desygnowanym routerem zostaje wybrany ten, który ma wyższe ID. Zostaje ono przydzielone na podstawie najwyższego adresu IP w interfejsie.\n"+
            "- router DR: identyfikator routera desygnowanego, jeśli takowy istnieje\n"+
            "- router BDR: identyfikator zapasowego routera desygnowanego, jeśli taki istnieje.\n"+
            "- lista sąsiadów: lista identyfikatorów sąsiednich routerów OSPF.\n"+
            "(II) PAKIETY  LSA\n"+
            "Po nawiązaniu sąsiedztwa router, który został wybrany jako master rozpoczyna synchronizację baz danych. Oba routery wysyłają do siebie streszczenie swojej bazy danych, po czym rozpoczyna się synchronizacja do stanu, gdy obie bazy będą identyczne. Następuję to dzięki komunikatom LSR (link-state request) oraz LSU (link-state update). Każdorazowo po odebraniu pakietu LSU router wysyła potwierdzenie zwrotne LSack. Każdy router rozsyła zalewowo swoje informacje łącze-stan do pozostałych routerów łacze-stan na obszarze routingu. Kiedy router odbierze pakiet LSA od sąsiedniego routera, natychmiast wysyła go ze wszystkich swoich interfejsów, oprócz tego na którym odebrał pakiet.\n"+
            "Pakiety LSA w przeciwieństwie do pakietów hello, nie muszą być wysyłane regularnie . Pakiet LSA  musi zostać wysłany tylko:\n"+
            "- podczas pierwszego uruchomienia routera albo po włączeniu procesu protokołu routingu na tym routerze,\n"+
            "- kiedy zmieni się topologia, co obejmuje także wyłączenie lub włączenie łącza, albo przyległość z sąsiadem zostanie ustanowiona albo zerwana.\n"+
            "Typy LSA:\n"+
            "Informacja o łączu na który składa się:\n"+
            "- ID routera,\n"+
            "- ID sąsiada,\n"+
            "- koszt.\n"+
            "Informacja o sieci na którą składa się:\n"+
            "- ID routera\n"+
            "- ID sieci (najczęściej adres podsieci),\n"+
            "- koszt.\n"+
            "ETAP III\n"+
            "BUDOWANIE BAZY DANYCH.\n"+
            "Każdy router używa bazy danych, aby skonstruować kompletną mapę topologii i obliczyć najlepszą trasę do każdej sieci docelowej. Po tym jak każdy router roześle swoje pakiety LSA w procesie zalewania, każdy router będzie miał pakiet LSA od każdego routera łącze-stan na obszarze routingu. Pakiety te składowane są w bazie danych łącze-stan dzięki czemu router może użyć algorytmu SPF w celu zbudowania drzewa SPF.\n"+
            "Drzewo SPF – algorytm ten sumuje koszt każdej drogi od źródła do celu. Jest to mapa przedstawiająca topologię sieci. Używając drzewa SPF, każdy router może niezależnie ustalić najkrótszą drogę do każdej sieci.\n"+
            "Koszt:\n"+
            "Koszt wysłania pakietu algorytm OSPF oblicza wzorem 108/ szerokość pasma wyrażoną w bitach. Jest on związany ze stroną wyjściową każdego interfejsu routera. Koszt może zostać skonfigurowany przez administratora systemu. Im mniejszy koszt, tym większe prawdopodobieństwo, że interfejs zostanie wykorzystany do wysyłania danych.\n"+
            "ETAP IV\n"+
            "BUDOWANIE TABLICY ROUTINGU.\n"+
            "Używając informacji o najkrótszej drodze ustalonej przez algorytm SPF najkrótsze drogi są dodawane do tablicy routingu. Tablica routingu będzie również zawierała wszystkie sieci połączone bezpośrednio i trasy z innych źródeł, na przykład trasy statyczne. Pakiety będą teraz przesyłane zgodnie z wpisami tablicy routingu.\n"+
            "OSPF A SIECI WIELODOSTĘPOWE.\n"+
            "Sieć wielodostępowa to sieć z więcej niż dwoma urządzeniami na tym samym współdzielonym nośniku. Przykładami sieci wielodostępowych są Ethernet, Token Ring i Frame Relay. Zalewanie LSA ww wielodostępowych sieciach OSPF może powodować dwa problemy:\n"+
            "- tworzenie wielu przyległości, po jednej dla każdej pary routerów, co doprowadziłoby do nadmiernej liczby LSA przesyłanych pomiędzy routerami znajdującymi się w tej samej sieci.\n"+
            "- zalewowe rozsyłanie zbyt wielu LSA, co może doprowadzić do przeciążenia sieci\n"+
            "Routery DR i BDR.\n"+
            "Aby zredukować ilość ruchu OSPF w sieciach wielodostępowych OSPF wybiera DR (designated router) oraz BDR (backup designated router). Router desygnowany odpowiedzialny jest za aktualizowanie wszystkich pozostałych routerów OSPF (zwanych DROtherami).DROthery pełne przyległości tworzą jedynie z routerami DR i BDR. Oznacza to, że zamiast zalewowo rozsyłać aktualizację LSA do wszystkich routerów w sieci, DROthery wysyają swoje LSA jedynie do routerów DR i BDR na grupowy adres 224.0.0.6 (wszystkie routery DR)\n"+
            "\n"+
            "Proces wyboru routerów DR i BDR.\n"+
            "Wybór routerów DR i BDR zachodzi w sieciach wielodostępowych. Nie odbywa się on w sieciach punkt-punkt. Router z najwyższym priorytetem interfejsu OSPF zostaje wybrany jako desygnowany (DR), a router z drugim co do wysokości priorytetem, zostaje jego zastępcom (BDR). W przypadku, gdy w sieci nastąpi awaria DR, to jego rolę przejmuje BDR stając się automatycznie DR. Jeśli nastąpiłaby taka sytuacja to zostałby wybrany nowy BDR, który pełniłby rolę zastępczą nowowybranego DR.\n"+
            "Identyfikatory i priorytet.\n"+
            "Domyślny priorytet routera w OSPF to 1. W przypadku, gdy priorytety interfejsów OSPF są równie to wybrany zostaje router z najwyższym identyfikatorem, który jest  ustalany na podstawie najwyższego adresu IP na interfejsie. Zamiast pozwalać wybierać routery DR i BDR w trybie konfiguracji interfejsu należy użyć polecenia ip ospf priority (0 – 255).\n"+
            "Priorytety są przypisywane do poszczególnych interfejsów co daje większą kontrolę nad wielodostępowymi sieciami OSPF. Pozwala to też routerowi być routerem desygnowanym w jednej sieci, a zwykłym DRotherem w innej.\n"+
            "Router brzegowy systemu autonomicznego.\n"+
            "Router, który znajduję się pomiędzy domeną routingu OSPF, a siecią bez OSPF nazywa się routerem brzegowym systemu autonomicznego (ang. Autonomus System Boundary Router, ASBR).\n"+
            "\n";
}
